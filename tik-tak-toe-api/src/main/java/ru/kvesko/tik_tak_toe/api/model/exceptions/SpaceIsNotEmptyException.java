package ru.kvesko.tik_tak_toe.api.model.exceptions;

import java.io.Serial;

public class SpaceIsNotEmptyException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1382438078848558002L;

    public SpaceIsNotEmptyException(final int x, final int y) {
        super(String.format("(%s, %s) is not empty!", x, y));
    }
}
