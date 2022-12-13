package ru.kvesko.tik_tak_toe.api.model.gameplay;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.util.UUID;

@RedisHash("GameSession")
@Data
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GameSession {
    @Nonnull
    @Id
    private UUID id;

    @Nonnull
    private Figure[][] playBoard;

    private int lastMovePlayerNumber;

    @Nullable
    private String gameResult;
}
