package ru.kvesko.tik_tak_toe.web.configs.redis;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.stereotype.Component;
import ru.kvesko.tik_tak_toe.api.model.gameplay.GameSession;

@Component
@WritingConverter
@RequiredArgsConstructor
public class GameSessionWritingConverter implements Converter<GameSession, byte[]> {
    private final ObjectMapper objectMapper;

    @Override
    public byte[] convert(final GameSession gameSession) {
        try {
            return objectMapper.writeValueAsString(gameSession).getBytes();
        } catch (final JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}