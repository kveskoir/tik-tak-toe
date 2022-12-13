package ru.kvesko.tik_tak_toe.web.configs.auth;

import jakarta.annotation.Nonnull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.kvesko.tik_tak_toe.api.model.context.PlayerInfo;
import ru.kvesko.tik_tak_toe.api.model.context.PlayerInfoHolder;

import java.io.IOException;
import java.util.UUID;

/**
 * PlayerNumber header filter
 */
@Slf4j
public class PlayerInfoFilter extends OncePerRequestFilter {

    @Override
    public void doFilterInternal(
            @Nonnull final HttpServletRequest request,
            @Nonnull final HttpServletResponse response,
            @Nonnull final FilterChain chain
    ) throws IOException, ServletException {
        final UUID sessionId = request.getHeader("sessionId") != null ?
                UUID.fromString(request.getHeader("sessionId")) : null;

        final int playerNumber = request.getHeader("playerNumber") !=
                                 null ? Integer.parseInt(request.getHeader("playerNumber")) : 0;

        if (playerNumber != 1 && playerNumber != 2) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            response.getWriter().write("playerNumber header does not equal '1' or '2'!");
            return;
        }

        final PlayerInfo playerInfo = createPlayerInfo(playerNumber, sessionId);
        PlayerInfoHolder.set(playerInfo);
        try {
            chain.doFilter(request, response);
        } finally {
            PlayerInfoHolder.set(null);
        }
    }

    private PlayerInfo createPlayerInfo(final int playerNumber, final UUID sessionId) {
        return new PlayerInfo(playerNumber, sessionId);
    }

}
