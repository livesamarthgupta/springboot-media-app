package io.educative.mediaapp.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler
    public ResponseEntity<?> playlistNotFound(PlaylistNotFoundException ex) {
        return ResponseEntity.badRequest()
                .body(new ResponseStatusError(ErrorCodes.PLAYLIST_NOT_FOUND.code(), ex.getMessage()));
    }

    @ExceptionHandler
    public ResponseEntity<?> songNotFound(SongNotFoundException ex) {
        return ResponseEntity.badRequest()
                .body(new ResponseStatusError(ErrorCodes.SONG_NOT_FOUND.code(), ex.getMessage()));
    }
}

