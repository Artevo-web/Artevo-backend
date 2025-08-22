package com.artevo.artevobackend.application.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Design {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName;        // Pfad zur PNG-Datei
    @Lob
    private byte[] data; // der Blob / byte array


    @ManyToOne
    @JoinColumn(name = "artist_id")
    private Artist artist;

    public Design() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }
}
