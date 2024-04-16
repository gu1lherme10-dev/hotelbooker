package com.hotelbooker.repository;

import com.hotelbooker.entity.Reserve;
import com.hotelbooker.entity.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface ReserveRepository extends JpaRepository <Reserve, Long> {
    @Query("SELECT COUNT(r) > 0 FROM reserve r " +
            "WHERE r.room.id = :roomId " +
            "AND (r.checkInDate <= :checkIn AND r.checkOutDate >= :checkOut)")
    boolean isRoomUnavailable(Integer roomId, LocalDate checkIn, LocalDate checkOut);

    Page<Reserve> findByRoomId(Integer roomId, Pageable pageable);
}
