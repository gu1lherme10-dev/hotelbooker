package com.hotelbooker.repository;

import com.hotelbooker.entity.Hotel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
    Page<Hotel> findByUserId(Long userId, Pageable pageable);

    @Query("SELECT COUNT(h) FROM hotels h WHERE h.user.id = :userId")
    long countHotelsByUserId(@Param("userId") long userId);

    @Query("SELECT COUNT(r) FROM rooms r WHERE r.hotel.id IN (SELECT h.id FROM hotels h WHERE h.user.id = :userId)")
    long countRoomsByUserId(@Param("userId") long userId);

    @Query("SELECT COUNT(rs) FROM reserve rs WHERE rs.hotel.id IN (SELECT h.id FROM hotels h WHERE h.user.id = :userId)")
    long countReservationsByUserId(@Param("userId") long userId);

    @Query("SELECT EXTRACT(MONTH FROM r.checkInDate) AS month, COUNT(r) AS count " +
            "FROM reserve r " +
            "WHERE r.checkInDate >= :fiveMonthsAgo " +
            "GROUP BY EXTRACT(MONTH FROM r.checkInDate) " +
            "ORDER BY EXTRACT(MONTH FROM r.checkInDate)")
    List<Object[]> countReservationsPerMonth(@Param("fiveMonthsAgo") LocalDate fiveMonthsAgo);




    ;
}
