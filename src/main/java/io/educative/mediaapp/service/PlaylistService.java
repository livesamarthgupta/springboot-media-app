package io.educative.mediaapp.service;

import io.educative.mediaapp.model.Playlist;
import io.educative.mediaapp.model.Song;

import java.math.BigInteger;
import java.util.Optional;

public interface PlaylistService {
    Iterable<Playlist> getAllPlaylists();

    Playlist getPlaylistById(BigInteger playlistId);

    Optional<Playlist> createPlaylist(String name);

    void deletePlaylist(BigInteger playlistId);

    Iterable<Song> getSongs(BigInteger playlistId);

    Iterable<Song> getSongs();

    void deleteSongs(BigInteger playlistId);

    Song addSong(BigInteger playlistId, Song song);

    boolean moveSong(BigInteger songId, BigInteger toPlaylistId);

    void deleteSong(BigInteger playlistId, BigInteger songId);
}
