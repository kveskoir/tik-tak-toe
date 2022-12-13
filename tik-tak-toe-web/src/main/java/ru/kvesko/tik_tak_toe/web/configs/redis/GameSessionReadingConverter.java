package ru.kvesko.tik_tak_toe.web.configs.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.stereotype.Component;
import ru.kvesko.tik_tak_toe.api.model.gameplay.GameSession;

import java.io.IOException;

@Component
@ReadingConverter
@RequiredArgsConstructor
public class GameSessionReadingConverter implements Converter<byte[], GameSession> {
    private final ObjectMapper objectMapper;

    @Override
    public GameSession convert(final byte[] gameSessionBytes) {
        try {
            return objectMapper.readValue(gameSessionBytes, GameSession.class);
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }

}