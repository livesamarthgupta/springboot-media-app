package io.educative.mediaapp.exception;

import java.math.BigInteger;

public class PlaylistNotFoundException extends RuntimeException {
    public PlaylistNotFoundException(final BigInteger playlistId) {
        super(String.format("No playlist found with id: %s", playlistId));
    }
}
