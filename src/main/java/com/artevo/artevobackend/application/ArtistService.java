package com.artevo.artevobackend.application;

import com.artevo.artevobackend.application.domain.Artist;
import com.artevo.artevobackend.application.in.LoginArtistUseCase;
import com.artevo.artevobackend.application.out.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
public class ArtistService implements LoginArtistUseCase {

    @Autowired
    ArtistRepository artistRepository;

    @Override
    public boolean loginArtist(String username, String password) {
       try {
           return validatePassword(artistRepository.findArtistByUsername(username),password);
       }catch (Exception e){
           throw e;
       }
    }

    private boolean validatePassword(Artist artist,String password) {
        return artist.getPassword().equals(password);
    }
}
