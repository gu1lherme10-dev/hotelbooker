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
import java.time.LocalDateTime;

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

    public ResponseEntity<Hotel> updateHotel(int id, Hotel payload) {
        Hotel hotelActual = hotelRepository.findById((long) id)
                .orElseThrow(() -> new EntityNotFoundException("Hotel not found"));
        payload.setId(id);
        payload.setCreatedAt(hotelActual.getCreatedAt());
        payload.setUpdated_at(LocalDateTime.now());
        return ResponseEntity.ok(hotelRepository.save(payload));
    }
}
