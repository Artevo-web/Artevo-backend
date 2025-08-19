package com.artevo.artevobackend.application.out;

import com.artevo.artevobackend.application.domain.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistRepository  extends JpaRepository<Artist,Long> {

   Artist findArtistByUsername(String username);

}
