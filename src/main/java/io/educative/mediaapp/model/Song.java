package io.educative.mediaapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;

@Data
@Entity
@Table(name = "song")
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger id;

    @JsonProperty("song_name")
    private String name;

    @Column(name = "playlist_id")
    @JsonProperty("playlist_id")
    private BigInteger playlistId;

    @Column(name = "cover_url")
    @JsonProperty("cover_url")
    private String coverUrl;

    @Column(name = "created_on")
    @JsonProperty("created_on")
    private Date createdOn;
}
