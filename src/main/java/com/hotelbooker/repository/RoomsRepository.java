package com.hotelbooker.repository;

import com.hotelbooker.entity.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.security.PublicKey;
import java.util.List;

public interface RoomsRepository extends JpaRepository<Room, Long> {

    Page<Room> findByHotelId(long hotelId, Pageable pageable);


}
