package ru.kvesko.tik_tak_toe.web.configs.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.stereotype.Component;
import ru.kvesko.tik_tak_toe.api.model.gameplay.GameSession;

import java.io.IOException;

@Component
@ReadingConverter
@RequiredArgsConstructor
@Slf4j
public class GameSessionReadingConverter implements Converter<byte[], GameSession> {
    private final ObjectMapper objectMapper;

    @Override
    public GameSession convert(final byte[] gameSessionBytes) {
        try {
            return objectMapper.readValue(gameSessionBytes, GameSession.class);
        } catch (final IOException e) {
            log.error("Unable to parse GameSession object", e);
            throw new RuntimeException(e);
        }
    }

}