package io.educative.mediaapp.exception;

import java.math.BigInteger;

public class SongNotFoundException extends RuntimeException {
    public SongNotFoundException(BigInteger songId) {
        super(String.format("No song found with song id: %s", songId));
    }
}
