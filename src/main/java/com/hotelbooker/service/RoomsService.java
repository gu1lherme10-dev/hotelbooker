package com.hotelbooker.service;

import com.hotelbooker.entity.Hotel;
import com.hotelbooker.entity.Room;
import com.hotelbooker.helpers.ResponseHelpers;
import com.hotelbooker.helpers.ValidateHelpers;
import com.hotelbooker.repository.HotelRepository;
import com.hotelbooker.repository.RoomsRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class RoomsService {

    @Autowired
    RoomsRepository roomsRepository;
    @Autowired
    HotelRepository hotelRepository;

    public ResponseEntity<Room> createRoom(Room room, long hotelId) {
        Hotel hotel = hotelRepository.findById(hotelId)
                .orElseThrow(() -> new EntityNotFoundException("Hotel not found"));
        room.setHotel(hotel);
        return ResponseEntity.ok(roomsRepository.save(room));
    }

    public ResponseEntity<?> listRooms(long hotelId, int page, int size) {
        Sort sortByID = Sort.by(Sort.Direction.ASC, "id");
        PageRequest paginationRequested = PageRequest.of(page - 1, size, sortByID);
        Page<Room> roomsPage = roomsRepository.findByHotelId(hotelId, paginationRequested);
        return ResponseEntity.ok(ResponseHelpers.formatListResponse(roomsPage));
    }

    public ResponseEntity<?> findRoomById(long hotelId, long roomId) {
        Hotel hotel = hotelRepository.findById(hotelId)
                .orElseThrow(() -> new EntityNotFoundException("Hotel not found"));

        ValidateHelpers.validateHotel(hotel, roomId);

        return ResponseEntity.ok(roomsRepository.findById(roomId)
                .orElseThrow(() -> new EntityNotFoundException("Room not found")));
    }

    public void deleteRoomById(long hotelId, long id) {
        Hotel hotel = hotelRepository.findById(hotelId)
                .orElseThrow(() -> new EntityNotFoundException("Hotel not found"));

        ValidateHelpers.validateHotel(hotel, id);

        roomsRepository.deleteById(id);
    }

    public ResponseEntity<Room> updateRoomById(Integer hotelId, Integer id, Room payload) {
        Hotel hotelActual = hotelRepository.findById((long) hotelId)
                .orElseThrow(() -> new EntityNotFoundException("Hotel not found"));

        ValidateHelpers.validateHotel(hotelActual, id);

        Room roomActual = roomsRepository.findById((long) id)
                .orElseThrow(() -> new EntityNotFoundException("Room not found"));
        payload.setId(id);
        payload.setHotel(hotelActual);
        payload.setCreatedAt(roomActual.getCreatedAt());
        payload.setUpdatedAt(LocalDateTime.now());
        return ResponseEntity.ok(roomsRepository.save(payload));
    }
}
