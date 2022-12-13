package ru.kvesko.tik_tak_toe.api;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import ru.kvesko.tik_tak_toe.api.model.gameplay.Figure;
import ru.kvesko.tik_tak_toe.api.model.gameplay.GameSession;

import java.util.List;
import java.util.UUID;

public interface GameplayService {
    @Nonnull
    UUID createSession();

    void deleteSession();

    void deleteAllSessions();

    @Nonnull
    GameSession getGameSession();

    @Nonnull
    List<GameSession> getAllGameSessionsByPage(int page);

    /**
     *
     * @param x - horizontal coordinate of the game field
     * @param y - vertical coordinate of the game field
     * @return winner if the game is finished; null otherwise
     */
    @Nullable
    String makeAMove(int x, int y);
}
