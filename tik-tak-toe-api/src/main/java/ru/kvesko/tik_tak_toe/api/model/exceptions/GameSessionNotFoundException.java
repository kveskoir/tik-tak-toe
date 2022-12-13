package ru.kvesko.tik_tak_toe.api.model.exceptions;

import jakarta.annotation.Nonnull;

import java.io.Serial;
import java.util.UUID;

public class GameSessionNotFoundException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = -1695810324654387802L;

    public GameSessionNotFoundException(@Nonnull final UUID sessionId) {
        super(String.format("There's no game session with id: %s", sessionId));
    }
}
