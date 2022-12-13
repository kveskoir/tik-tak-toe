package ru.kvesko.tik_tak_toe.api.model.gameplay;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
public enum Figure {
    EMPTY(0),
    CROSS(1),
    CIRCLE(2),
    ;

    private static final Map<Integer, Figure> LOOKUP = Arrays
            .stream(values())
            .collect(Collectors.toMap(Figure::getPlayerNumber, Function.identity()));
    private final int playerNumber;

    public static Figure byPlayerNumber(final int playerNumber) {
        return LOOKUP.get(playerNumber);
    }
}
