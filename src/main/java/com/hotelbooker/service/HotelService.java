package com.hotelbooker.service;

import com.hotelbooker.helpers.ResponseHelpers;
import com.hotelbooker.repository.HotelRepository;
import com.hotelbooker.entity.Hotel;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HotelService {

    @Autowired
    HotelRepository hotelRepository;

    public ResponseEntity<Hotel> createHotel(Hotel hotel) {
        return ResponseEntity.ok(hotelRepository.save(hotel));
    }

    public ResponseEntity<?> listHotels(int page, int size) {
        Sort sortByID = Sort.by(Sort.Direction.ASC, "id");
        PageRequest paginationRequested = PageRequest.of(page - 1, size, sortByID);
        Page<Hotel> hotelsPage = hotelRepository.findAll(paginationRequested);
        return ResponseEntity.ok(ResponseHelpers.formatListResponse(hotelsPage));
    }

    public ResponseEntity<?> listHotelsByIdUser(Long userId, int page, int size) {
        Sort sortByID = Sort.by(Sort.Direction.ASC, "id");
        PageRequest paginationRequested = PageRequest.of(page - 1, size, sortByID);
        Page<Hotel> hotels = hotelRepository.findByUserId(userId, paginationRequested);
        return ResponseEntity.ok(ResponseHelpers.formatListResponse(hotels));
    }


    public ResponseEntity<Hotel> findHotelById(long id){
        return ResponseEntity.ok(hotelRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Hotel not found")));
    }

    public void deleteHotel(long id) {
        hotelRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Hotel not found"));

        hotelRepository.deleteById(id);
    }

    public Map<String, Object> getUserStatistics(long userId) {
        long totalHotels = hotelRepository.countHotelsByUserId(userId);
        long totalRooms = hotelRepository.countRoomsByUserId(userId);
        long totalReservations = hotelRepository.countReservationsByUserId(userId);

        LocalDate fiveMonthsAgo = LocalDate.now().minusMonths(5);
        List<Object[]> monthsReservations = hotelRepository.countReservationsPerMonth(fiveMonthsAgo);

        Map<String, Object> stats = new HashMap<>();
        List<Map<String, Long>> monthsReserves = new ArrayList<>();

        for (Object[] result : monthsReservations) {
            Map<String, Long> monthReservations = new HashMap<>();
            String month = String.valueOf(result[0]);
            Long count = (Long) result[1];
            monthReservations.put(month, count);
            monthsReserves.add(monthReservations);
        }


        stats.put("monthsReserves", monthsReserves);

        stats.put("totalHotels", totalHotels);
        stats.put("totalRooms", totalRooms);
        stats.put("totalReservations", totalReservations);

        return stats;
    }

    public ResponseEntity<Hotel> updateHotel(int id, Hotel payload) {
        Hotel hotelActual = hotelRepository.findById((long) id)
                .orElseThrow(() -> new EntityNotFoundException("Hotel not found"));
        payload.setId(id);
        payload.setCreatedAt(hotelActual.getCreatedAt());
        payload.setUpdated_at(LocalDateTime.now());
        payload.setUser(hotelActual.getUser());
        return ResponseEntity.ok(hotelRepository.save(payload));
    }
}
