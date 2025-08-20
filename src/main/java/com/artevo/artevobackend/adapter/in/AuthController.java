package com.artevo.artevobackend.adapter.in;

import com.artevo.artevobackend.application.in.LoginArtistUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    LoginArtistUseCase loginArtistUseCase;

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest) {
        if(loginArtistUseCase.loginArtist(loginRequest.username(), loginRequest.password())){
            return "true";
        }
        return "false";
    }
}