package com.artevo.artevobackend.adapter.out;

import com.artevo.artevobackend.application.domain.Artist;
import com.artevo.artevobackend.application.out.ArtistReader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistRepository  extends JpaRepository<Artist,Long> , ArtistReader
{
    Artist findArtistByUsername(String username);
}
