package com.artevo.artevobackend.adapter.in;

import com.artevo.artevobackend.application.in.LoginArtistUseCase;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AuthController {

    LoginArtistUseCase loginArtistUseCase;

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest) {
        if(loginArtistUseCase.loginArtist(loginRequest.username(), loginRequest.password())){
            return "true";
        }
        return "false";
    }
}