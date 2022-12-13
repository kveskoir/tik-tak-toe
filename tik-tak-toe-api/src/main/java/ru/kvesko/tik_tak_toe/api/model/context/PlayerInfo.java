package ru.kvesko.tik_tak_toe.api.model.context;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class PlayerInfo {
    @Nonnull
    private final int playerNumber;
    @Nullable
    private final UUID sessionId;
}
