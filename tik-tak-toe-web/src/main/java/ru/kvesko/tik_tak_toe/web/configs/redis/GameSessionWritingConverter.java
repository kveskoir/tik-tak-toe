package ru.kvesko.tik_tak_toe.web.configs.redis;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.stereotype.Component;
import ru.kvesko.tik_tak_toe.api.model.gameplay.GameSession;

@Component
@WritingConverter
@RequiredArgsConstructor
@Slf4j
public class GameSessionWritingConverter implements Converter<GameSession, byte[]> {
    private final ObjectMapper objectMapper;

    @Override
    public byte[] convert(final GameSession gameSession) {
        try {
            return objectMapper.writeValueAsString(gameSession).getBytes();
        } catch (final JsonProcessingException e) {
            log.error("Unable to serialize GameSession", e);
            throw new RuntimeException(e);
        }
    }

}