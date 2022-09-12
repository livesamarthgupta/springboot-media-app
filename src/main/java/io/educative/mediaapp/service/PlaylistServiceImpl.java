package io.educative.mediaapp.service;

import io.educative.mediaapp.database.PlaylistRepository;
import io.educative.mediaapp.database.SongsRepository;
import io.educative.mediaapp.exception.PlaylistNotFoundException;
import io.educative.mediaapp.exception.SongNotFoundException;
import io.educative.mediaapp.model.Playlist;
import io.educative.mediaapp.model.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Date;
import java.util.Optional;

@Service
public class PlaylistServiceImpl implements PlaylistService {

    @Autowired
    PlaylistRepository playlistRepository;

    @Autowired
    SongsRepository songsRepository;

    @Override
    public Iterable<Playlist> getAllPlaylists() {
        return playlistRepository.findAll();
    }

    @Override
    public Playlist getPlaylistById(BigInteger playlistId) {
        return playlistRepository.findById(playlistId)
                .orElseThrow(() -> new PlaylistNotFoundException(playlistId));
    }

    @Override
    public Optional<Playlist> createPlaylist(String name) {
        Playlist playlist = new Playlist();
        playlist.setName(name);
        playlist.setCreatedOn(new Date());
        return Optional.of(playlistRepository.save(playlist));
    }

    @Override
    public void deletePlaylist(BigInteger playlistId) {
        Playlist playlist = getPlaylistById(playlistId);
        playlistRepository.delete(playlist);
    }

    @Override
    public Iterable<Song> getSongs(BigInteger playlistId) {
        Playlist playlist = getPlaylistById(playlistId);
        if(playlist != null)
            return playlistRepository.getSongs(playlistId);
        else
            return null;
    }

    @Override
    public Iterable<Song> getSongs() {
        return songsRepository.findAll();
    }

    @Override
    public void deleteSongs(BigInteger playlistId) {
        Playlist playlist = getPlaylistById(playlistId);
        if(playlist != null)
            songsRepository.deleteByPlaylistId(playlistId);
    }

    @Override
    public Song addSong(BigInteger playlistId, Song song) {
        Playlist playlist = getPlaylistById(playlistId);
        if(playlist == null)
            return null;

        song.setPlaylistId(playlist.getId());
        song.setCreatedOn(new Date());
        return songsRepository.save(song);
    }

    @Override
    public boolean moveSong(BigInteger songId, BigInteger toPlaylistId) {
        Song song = getSong(songId);
        Playlist playlist = getPlaylistById(toPlaylistId);
        if(song == null || playlist == null)
            return false;
        return songsRepository.updatePlaylist(toPlaylistId, songId) == 1;
    }

    @Override
    public void deleteSong(BigInteger playlistId, BigInteger songId) {
        Song song = getSong(songId);
        songsRepository.delete(song);
    }

    private Song getSong(BigInteger songId) {
        return songsRepository.findById(songId)
                .orElseThrow(() -> new SongNotFoundException(songId));
    }
}
