package com.artevo.artevobackend.application.out;

import com.artevo.artevobackend.application.domain.Artist;

public interface ArtistReader {

   Artist findArtistByUsername(String username);
}
