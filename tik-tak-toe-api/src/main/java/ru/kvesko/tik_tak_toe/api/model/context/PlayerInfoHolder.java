package ru.kvesko.tik_tak_toe.api.model.context;


import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class PlayerInfoHolder {

    private static final ThreadLocal<PlayerInfo> REQUEST_INFO = new InheritableThreadLocal<>();

    private PlayerInfoHolder() {
    }

    @Nonnull
    public static PlayerInfo getWithoutSessionIdCheck() {
        final var playerInfo = REQUEST_INFO.get();
        if (playerInfo == null) {
            throw new IllegalStateException("PlayerInfo is not set");
        }
        return playerInfo;
    }

    @Nonnull
    public static PlayerInfo get() {
        final var playerInfo = getWithoutSessionIdCheck();
        if (playerInfo.getSessionId() == null) {
            throw new IllegalStateException("SessionId must not be null!");
        }
        return playerInfo;
    }

    public static void set(@Nullable final PlayerInfo playerInfo) {
        if (playerInfo == null) {
            REQUEST_INFO.remove();
        } else {
            log.trace("Player info set <{}>", playerInfo);
            REQUEST_INFO.set(playerInfo);
        }
    }

}
