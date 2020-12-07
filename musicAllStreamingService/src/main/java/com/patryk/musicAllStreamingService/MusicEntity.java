package com.patryk.musicAllStreamingService;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MusicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idSong;
    private String songName;
    private String songURL;

    public MusicEntity() {
    }

    public MusicEntity(String songName, String songURL) {
        this.songName = songName;
        this.songURL = songURL;
    }

    public String getSongName() {
        return songName;
    }

    public String getSongURL() {
        return songURL;
    }
}
