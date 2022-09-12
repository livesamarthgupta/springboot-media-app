package io.educative.mediaapp.controller.data;

import io.educative.mediaapp.model.Playlist;
import io.educative.mediaapp.model.Song;
import io.educative.mediaapp.service.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.Optional;

@RestController
@RequestMapping("/playlist")
public class PlaylistRestController {

    private PlaylistService playlistService;

    @Autowired
    public void setPlaylistService(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @GetMapping("/")
    public String root() {
        return "Application is UP";
    }

    @GetMapping("/all")
    public Iterable<Playlist> getAll() {
        return playlistService.getAllPlaylists();
    }

    @GetMapping("/{id}")
    public Playlist getPlaylistById(final @PathVariable("id")BigInteger playlistId) {
        return playlistService.getPlaylistById(playlistId);
    }

    @PostMapping("/{name}")
    public Optional<Playlist> createPlaylist(final @PathVariable("name") String playlistName) {
        return playlistService.createPlaylist(playlistName);
    }

    @DeleteMapping("/{id}")
    public void deletePlaylist(final @PathVariable BigInteger id) {
        playlistService.deletePlaylist(id);
    }

    @GetMapping("/{id}/songs")
    public Iterable<Song> getSongsInPlaylist(final @PathVariable("id") BigInteger playlistId) {
        return playlistService.getSongs(playlistId);
    }

    @DeleteMapping("/{id}/songs")
    public void deleteAllSongsInPlaylist(final @PathVariable BigInteger id) {
        playlistService.deleteSongs(id);
    }

    @PostMapping("/{id}/add")
    public Song addSongToPlaylist(final @PathVariable("id") BigInteger playlistId, Song song) {
        return playlistService.addSong(playlistId, song);
    }

    @GetMapping("/songs")
    public Iterable<Song> getAllSongs() {
        return playlistService.getSongs();
    }

    @PutMapping("/songs/{id}/move")
    public boolean moveSongToDiffPlaylist(@PathVariable("id") BigInteger songId, @RequestParam("to_playlist") BigInteger playlistId) {
        return playlistService.moveSong(songId, playlistId);
    }

    @DeleteMapping("/{id}/songs/{song_id}")
    public void deleteSongFromPlaylist(final @PathVariable("id") BigInteger playlistId, final @PathVariable("song_id") BigInteger songId) {
        playlistService.deleteSong(playlistId, songId);
    }

}
