package ru.kvesko.tik_tak_toe.web.configs;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.kvesko.tik_tak_toe.api.model.exceptions.GameSessionFinishedException;
import ru.kvesko.tik_tak_toe.api.model.exceptions.GameSessionNotFoundException;
import ru.kvesko.tik_tak_toe.api.model.exceptions.NotYourTurnException;
import ru.kvesko.tik_tak_toe.api.model.exceptions.SpaceIsNotEmptyException;

@ControllerAdvice
@Slf4j
public class ExceptionHandlerAdvice {

    @ExceptionHandler(GameSessionFinishedException.class)
    public ResponseEntity<String> handleException(final GameSessionFinishedException e) {
        log.trace(e.getLocalizedMessage(), e);
        return ResponseEntity
                .status(HttpStatus.NOT_ACCEPTABLE)
                .body(e.getLocalizedMessage());
    }

    @ExceptionHandler(GameSessionNotFoundException.class)
    public ResponseEntity<String> handleException(final GameSessionNotFoundException e) {
        log.trace(e.getLocalizedMessage(), e);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(e.getLocalizedMessage());
    }

    @ExceptionHandler(NotYourTurnException.class)
    public ResponseEntity<String> handleException(final NotYourTurnException e) {
        log.trace(e.getLocalizedMessage(), e);
        return ResponseEntity
                .status(HttpStatus.NOT_ACCEPTABLE)
                .body(e.getLocalizedMessage());
    }

    @ExceptionHandler(SpaceIsNotEmptyException.class)
    public ResponseEntity<String> handleException(final SpaceIsNotEmptyException e) {
        log.trace(e.getLocalizedMessage(), e);
        return ResponseEntity
                .status(HttpStatus.NOT_ACCEPTABLE)
                .body(e.getLocalizedMessage());
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<String> handleException(final IllegalStateException e) {
        log.trace(e.getLocalizedMessage(), e);
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(e.getLocalizedMessage());
    }
}
