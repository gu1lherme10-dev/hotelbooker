package com.hotelbooker.repository;

import com.hotelbooker.entity.Hotel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
    Page<Hotel> findByUserId(Long userId, Pageable pageable);
}
