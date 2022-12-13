package ru.kvesko.tik_tak_toe.api.model.exceptions;

import jakarta.annotation.Nonnull;

import java.io.Serial;

public class UnableToParseHeaderException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1537245441427485365L;

    public UnableToParseHeaderException(@Nonnull final String header, final Throwable t) {
        super(String.format("Unable to parse <%s> header", header), t);
    }
}
