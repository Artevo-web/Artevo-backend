package com.artevo.artevobackend.application;

import com.artevo.artevobackend.application.domain.Artist;
import com.artevo.artevobackend.application.in.LoginArtistUseCase;
import com.artevo.artevobackend.application.out.ArtistReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArtistService implements LoginArtistUseCase {

    @Autowired
    ArtistReader artistReader;

    @Override
    public boolean loginArtist(String username, String password) {
       try {
           return validatePassword(artistReader.findArtistByUsername(username),password);
       }catch (Exception e){
           throw e;
       }
    }

    private boolean validatePassword(Artist artist,String password) {
        return artist.getPassword().equals(password);
    }
}
