package ru.kvesko.tik_tak_toe.impl.service.gameplay;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.kvesko.tik_tak_toe.api.GameplayService;
import ru.kvesko.tik_tak_toe.api.model.context.PlayerInfoHolder;
import ru.kvesko.tik_tak_toe.api.model.exceptions.GameSessionFinishedException;
import ru.kvesko.tik_tak_toe.api.model.exceptions.GameSessionNotFoundException;
import ru.kvesko.tik_tak_toe.api.model.exceptions.NotYourTurnException;
import ru.kvesko.tik_tak_toe.api.model.exceptions.SpaceIsNotEmptyException;
import ru.kvesko.tik_tak_toe.api.model.gameplay.Figure;
import ru.kvesko.tik_tak_toe.api.model.gameplay.GameSession;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GamePlayServiceImpl implements GameplayService {
    private static final int PAGE_SIZE = 10;
    private final GameSessionRepository gameSessionRepository;

    @Nonnull
    @Override
    public UUID createSession() {
        final Figure[][] emptyBoard = new Figure[3][3];
        for (final Figure[] row : emptyBoard) {
            Arrays.fill(row, Figure.EMPTY);
        }
        final var gameSession = GameSession.of(
                UUID.randomUUID(),
                emptyBoard,
                0,
                null
        );
        return gameSessionRepository.save(gameSession).getId();
    }

    @Override
    public void deleteSession() {
        final UUID sessionId = PlayerInfoHolder.get().getSessionId();
        gameSessionRepository.findById(sessionId)
                             .orElseThrow(() -> new GameSessionNotFoundException(sessionId));
        gameSessionRepository.deleteById(PlayerInfoHolder.get().getSessionId());
    }

    @Override
    public void deleteAllSessions() {
        gameSessionRepository.deleteAll();
    }

    @Nonnull
    @Override
    public GameSession getGameSession() {
        final var sessionId = PlayerInfoHolder.get().getSessionId();
        return gameSessionRepository
                .findById(sessionId)
                .orElseThrow(() -> new GameSessionNotFoundException(sessionId));
    }

    @Nonnull
    @Override
    public List<GameSession> getAllGameSessionsByPage(final int page) {
        return gameSessionRepository.findAll(Pageable.ofSize(PAGE_SIZE).withPage(page));
    }

    @Override
    public String makeAMove(final int x, final int y) {
        final UUID sessionId = PlayerInfoHolder.get().getSessionId();
        final int playerNumber = PlayerInfoHolder.get().getPlayerNumber();

        final var gameSession = gameSessionRepository
                .findById(sessionId)
                .orElseThrow(() -> new GameSessionNotFoundException(sessionId));
        if (gameSession.getGameResult() != null) {
            throw new GameSessionFinishedException(sessionId, gameSession.getGameResult());
        }
        if (gameSession.getLastMovePlayerNumber() == playerNumber) {
            throw new NotYourTurnException(sessionId);
        }
        if (gameSession.getPlayBoard()[x][y] != Figure.EMPTY) {
            throw new SpaceIsNotEmptyException(x, y);
        }

        gameSession.getPlayBoard()[x][y] = Figure.byPlayerNumber(playerNumber);
        gameSession.setPlayBoard(gameSession.getPlayBoard());
        final String gameResult = checkForGameWinner(gameSession.getPlayBoard());
        gameSession.setGameResult(gameResult);
        gameSession.setLastMovePlayerNumber(playerNumber);
        gameSessionRepository.save(gameSession);
        return gameResult;
    }

    @Nullable
    public String checkForGameWinner(@Nonnull final Figure[][] gameBoard) {
        //Check rows for a winner
        for (int i = 0; i < 2; i++) {
            if (gameBoard[0][i] != Figure.EMPTY &&
                gameBoard[0][i] == gameBoard[1][i] &&
                gameBoard[0][i] == gameBoard[2][i]) {
                return String.format("Player '%s' won", gameBoard[0][i].getPlayerNumber());
            }
        }
        //Check columns for a winner
        for (int i = 0; i < 2; i++) {
            if (gameBoard[i][0] != Figure.EMPTY &&
                gameBoard[i][0] == gameBoard[0][1] &&
                gameBoard[i][0] == gameBoard[0][2]) {
                return String.format("Player '%s' won", gameBoard[i][0].getPlayerNumber());
            }
        }
        //Check diagonals for a winner
        if (gameBoard[0][0] != Figure.EMPTY &&
            gameBoard[0][0] == gameBoard[1][1] &&
            gameBoard[0][0] == gameBoard[2][2]) {
            return String.format("Player '%s' won", gameBoard[0][0].getPlayerNumber());
        }
        if (gameBoard[0][2] != Figure.EMPTY &&
            gameBoard[0][2] == gameBoard[1][1] &&
            gameBoard[0][2] == gameBoard[2][0]) {
            return String.format("Player '%s' won", gameBoard[0][2].getPlayerNumber());
        }

        //Check for draw
        if (Arrays.stream(gameBoard).flatMap(Arrays::stream).noneMatch(o -> o != Figure.EMPTY)) {
            return "Draw";
        }

        return null;
    }
}
