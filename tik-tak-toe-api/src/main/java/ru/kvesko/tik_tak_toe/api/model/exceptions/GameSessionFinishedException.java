package ru.kvesko.tik_tak_toe.api.model.exceptions;

import jakarta.annotation.Nonnull;

import java.io.Serial;
import java.util.UUID;

public class GameSessionFinishedException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = -5351925817952628371L;

    public GameSessionFinishedException(@Nonnull final UUID sessionId, @Nonnull final String gameResult) {
        super(String.format(
                "Game with id <%s> is over! Game result - '%s' !",
                sessionId,
                gameResult
        ));
    }
}
