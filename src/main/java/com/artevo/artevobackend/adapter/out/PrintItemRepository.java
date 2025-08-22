package com.artevo.artevobackend.adapter.out;

import com.artevo.artevobackend.application.domain.Artist;
import com.artevo.artevobackend.application.domain.PrintItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrintItemRepository extends JpaRepository<PrintItem,Long> {

}
