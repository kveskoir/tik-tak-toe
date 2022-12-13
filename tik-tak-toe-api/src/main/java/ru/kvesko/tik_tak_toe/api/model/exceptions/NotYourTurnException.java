package ru.kvesko.tik_tak_toe.api.model.exceptions;

import jakarta.annotation.Nonnull;

import java.io.Serial;
import java.util.UUID;

public class NotYourTurnException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = -7417230157036237597L;

    public NotYourTurnException(@Nonnull final UUID sessionId) {
        super(String.format("It's not your turn for the game with id <%s>!", sessionId));
    }
}
