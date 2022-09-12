package io.educative.mediaapp.database;

import io.educative.mediaapp.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Optional;

public interface SongsRepository extends JpaRepository<Song, BigInteger> {

    Optional<Song> findByName(String name);

    Collection<Song> findByPlaylistId(BigInteger playlistId);


    @Transactional
    @Modifying(clearAutomatically = true)
//    @Query("delete from Song s where s.playlistId = ?1")  // Hibernate Query Language
    @Query(value = "delete from song where playlist_id = ?", nativeQuery = true) // Native SQL
    void deleteByPlaylistId(BigInteger playlistId);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("delete from Song s where s.playlistId = ?1 and s.id = ?2")
    void delete(BigInteger playlistId, BigInteger songId);


    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update Song s set s.playlistId = ?1 where s.id = ?2")
    int updatePlaylist(BigInteger playlistId, BigInteger songId);
}
