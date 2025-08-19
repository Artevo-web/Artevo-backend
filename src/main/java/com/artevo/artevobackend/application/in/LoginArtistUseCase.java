package com.artevo.artevobackend.application.in;

import com.artevo.artevobackend.adapter.in.LoginRequest;

public interface LoginArtistUseCase {

    boolean loginArtist(String username, String password);
}
