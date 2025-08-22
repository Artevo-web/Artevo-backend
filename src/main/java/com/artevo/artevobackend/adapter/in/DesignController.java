package com.artevo.artevobackend.adapter.in;

import com.artevo.artevobackend.adapter.out.ArtistRepository;
import com.artevo.artevobackend.adapter.out.DesignRepository;
import com.artevo.artevobackend.application.domain.Artist;
import com.artevo.artevobackend.application.domain.Design;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class DesignController {

    @Autowired
    public  DesignRepository designRepository;
    @Autowired
    public ArtistRepository artistRepository;


    @PostMapping("/designs")
    public ResponseEntity<Design> uploadDesign(@RequestParam("file") MultipartFile file,
                                               @RequestParam("artistId") Long artistId) throws IOException {
        Artist artist = artistRepository.findById(artistId).orElseThrow();

        Design design = new Design();
        design.setArtist(artist);
        design.setFileName(file.getOriginalFilename());
        design.setData(file.getBytes()); // Hier wird der Blob gesetzt

        designRepository.save(design);

        return ResponseEntity.ok(design);
    }

    @GetMapping("/designs/artist/{artistId}")
    public List<DesignDto> getDesignsByArtist(@PathVariable Long artistId) {
        Artist artist = artistRepository.findById(artistId).orElseThrow();
        return designRepository.findAllByArtist(artist)
                .stream()
                .map(DesignDto::fromEntity)
                .toList();
    }




}
