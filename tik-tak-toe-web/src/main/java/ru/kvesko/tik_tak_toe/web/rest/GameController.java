package ru.kvesko.tik_tak_toe.web.rest;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.kvesko.tik_tak_toe.api.GameplayService;
import ru.kvesko.tik_tak_toe.api.model.gameplay.GameSession;
import ru.kvesko.tik_tak_toe.web.dtos.CoordinatesDTO;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("game")
@RequiredArgsConstructor
public class GameController {
    private final GameplayService gameplayService;

    @PostMapping("/create-game-session")
    public ResponseEntity<UUID> createGameSession() {
        final UUID sessionId = gameplayService.createSession();
        return ResponseEntity.ok(sessionId);
    }

    @GetMapping("/get-all-game-session")
    public ResponseEntity<List<GameSession>> createGameSession(@RequestParam("page") final int page) {
        return ResponseEntity.ok(gameplayService.getAllGameSessionsByPage(page));
    }

    @DeleteMapping("/delete-all-game-sessions")
    @Transactional
    public ResponseEntity<Void> deleteAllGameSessions() {
        gameplayService.deleteAllSessions();
        return ResponseEntity.ok(null);
    }

    @GetMapping("/get-board-view")
    public ResponseEntity<GameSession> getBoardView() {
        final GameSession gameSession = gameplayService.getGameSession();
        return ResponseEntity.ok(gameSession);
    }

    @PostMapping("/make-a-move")
    @Transactional
    public ResponseEntity<String> makeAMove(@RequestBody @Nonnull final CoordinatesDTO coordinatesDTO) {
        final String winner = gameplayService.makeAMove(coordinatesDTO.getX(), coordinatesDTO.getY());
        return ResponseEntity.ok(winner);
    }

    @DeleteMapping("/reset")
    @Transactional
    public ResponseEntity<Void> reset() {
        gameplayService.deleteSession();
        return ResponseEntity.ok(null);
    }

}
