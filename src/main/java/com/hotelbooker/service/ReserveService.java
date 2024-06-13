package com.hotelbooker.service;

import com.hotelbooker.entity.Hotel;
import com.hotelbooker.entity.Reserve;
import com.hotelbooker.entity.Room;
import com.hotelbooker.helpers.ResponseHelpers;
import com.hotelbooker.helpers.ValidateHelpers;
import com.hotelbooker.repository.HotelRepository;
import com.hotelbooker.repository.ReserveRepository;
import com.hotelbooker.repository.RoomsRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ReserveService {

    @Autowired
    ReserveRepository reserveRepository;

    @Autowired
    RoomsRepository roomsRepository;

    @Autowired
    HotelRepository hotelRepository;

    public ResponseEntity<?> createReserve(Reserve data, Integer hotelId, Integer roomId) {
        Hotel hotel = hotelRepository.findById((long) hotelId)
                .orElseThrow(() -> new EntityNotFoundException("Hotel not found"));

        Room room = roomsRepository.findById((long) roomId)
                .orElseThrow(() -> new EntityNotFoundException("Room not found"));

        ValidateHelpers.validateHotel(hotel, roomId);

        if (isRoomUnavailable(roomId, data)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    "{\"message\": \"This room is already reserved for the selected date.\", \"status\": \"not created\"}"
            );
        }

        data.setHotel(hotel);
        data.setRoom(room);
        Reserve response = reserveRepository.save(data);
        return ResponseEntity.ok().body(response);
    }

    public ResponseEntity<?> updateReserve(Reserve data, Integer hotelId, int roomId, int reserveId) {
        Hotel hotel = hotelRepository.findById((long) hotelId)
                .orElseThrow(() -> new EntityNotFoundException("Hotel not found"));

        Room room = roomsRepository.findById((long) roomId)
                .orElseThrow(() -> new EntityNotFoundException("Room not found"));

        ValidateHelpers.validateHotelAndRoom(hotel, room, reserveId);

        Reserve reserveActual = reserveRepository.findById((long) reserveId)
                .orElseThrow(() -> new EntityNotFoundException("Reserve not found"));

        if (isDifferentDate(reserveActual, data) && isRoomUnavailable(roomId, data)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    "{\"message\": \"This room is already reserved for the selected date.\", \"status\": \"not created\"}"
            );
        }

        data.setId(reserveId);
        data.setCreatedAt(reserveActual.getCreatedAt());
        data.setUpdatedAt(LocalDateTime.now());
        data.setRoom(reserveActual.getRoom());
        data.setHotel(reserveActual.getHotel());
        Reserve response = reserveRepository.save(data);

        return ResponseEntity.ok().body(response);
    }

    public ResponseEntity<?> listReservesFromRoom(Integer hotelId, Integer roomId, int page, int size) {
        Hotel hotel = hotelRepository.findById((long) hotelId)
                .orElseThrow(() -> new EntityNotFoundException("Hotel not found"));

        Room room = roomsRepository.findById((long) roomId)
                .orElseThrow(() -> new EntityNotFoundException("Room not found"));

        ValidateHelpers.validateHotel(hotel, roomId);

        Sort sortByID = Sort.by(Sort.Direction.ASC, "id");
        PageRequest paginationRequested = PageRequest.of(page - 1, size, sortByID);
        Page<Reserve> reservesPage = reserveRepository.findByRoomId(roomId, paginationRequested);
        return ResponseEntity.ok().body(ResponseHelpers.formatListResponse(reservesPage));
    }

    public ResponseEntity<?> findRoomById(long hotelId, long roomId, Integer id) {
        Hotel hotel = hotelRepository.findById((long) hotelId)
                .orElseThrow(() -> new EntityNotFoundException("Hotel not found"));

        Room room = roomsRepository.findById((long) roomId)
                .orElseThrow(() -> new EntityNotFoundException("Room not found"));

        ValidateHelpers.validateHotelAndRoom(hotel, room, id);

        Reserve reserve = reserveRepository.findById((long) id)
                .orElseThrow(() -> new EntityNotFoundException("Reserve not found"));

        return ResponseEntity.ok().body(reserve);
    }

    public void deleteReserveById(long hotelId, long roomId, Integer id) {
        Hotel hotel = hotelRepository.findById((long) hotelId)
                .orElseThrow(() -> new EntityNotFoundException("Hotel not found"));

        Room room = roomsRepository.findById((long) roomId)
                .orElseThrow(() -> new EntityNotFoundException("Room not found"));

        Reserve reserve = reserveRepository.findById((long) id)
                .orElseThrow(() -> new EntityNotFoundException("Reserve not found"));

        ValidateHelpers.validateHotelAndRoom(hotel, room, id);

        room.getReserves().remove(reserve);


        reserveRepository.delete(reserve);
    }

    private boolean isRoomUnavailable(Integer roomId, Reserve payload) {
        return reserveRepository.isRoomUnavailable(roomId, payload.getCheckInDate(), payload.getCheckOutDate());
    }

    private Boolean isDifferentDate(Reserve reserveActual, Reserve payload) {
        return !reserveActual.getCheckInDate().equals(payload.getCheckInDate()) ||
                !reserveActual.getCheckOutDate().equals(payload.getCheckOutDate());
    }
}
