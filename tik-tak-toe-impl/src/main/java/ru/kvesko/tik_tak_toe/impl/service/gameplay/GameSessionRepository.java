package ru.kvesko.tik_tak_toe.impl.service.gameplay;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.kvesko.tik_tak_toe.api.model.gameplay.GameSession;

import java.util.List;
import java.util.UUID;

@Repository
public interface GameSessionRepository extends CrudRepository<GameSession, UUID> {
    List<GameSession> findAll(Pageable pageable);
}
