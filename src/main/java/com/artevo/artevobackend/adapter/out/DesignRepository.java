package com.artevo.artevobackend.adapter.out;

import com.artevo.artevobackend.application.domain.Artist;
import com.artevo.artevobackend.application.domain.Design;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DesignRepository  extends JpaRepository<Design,Long> {

    List<Design> findAllByArtist(Artist artist);
}
