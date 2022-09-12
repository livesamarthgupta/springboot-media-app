package io.educative.mediaapp.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
class ResponseStatusError {
    private int status;
    private String message;
}
