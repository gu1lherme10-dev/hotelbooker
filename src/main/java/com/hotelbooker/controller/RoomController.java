package com.hotelbooker.controller;

import com.hotelbooker.entity.Hotel;
import com.hotelbooker.entity.Room;
import com.hotelbooker.service.RoomsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = "/hotels/{hotelId}/rooms", produces = MediaType.APPLICATION_JSON_VALUE)
public class RoomController {
    @Autowired
    RoomsService roomsService;

    @PostMapping
    @ResponseBody()
    public ResponseEntity<Room> saveRoom(@Valid @RequestBody Room data, @PathVariable long hotelId){
        return roomsService.createRoom(data, hotelId);
    }

    @GetMapping
    @ResponseBody()
    public ResponseEntity<?> listRoomsByHotelId(@PathVariable long hotelId, @RequestParam(required = false, defaultValue = "1") int page, @RequestParam(required = false, defaultValue = "50") int size){
        return roomsService.listRooms(hotelId, page, size);
    }

    @GetMapping(value = "/{roomId}")
    @ResponseBody()
    public ResponseEntity<?> findRoomById(@PathVariable long hotelId, @PathVariable long roomId){
        return roomsService.findRoomById(hotelId,roomId);
    }

    @DeleteMapping("/{roomId}")
    @ResponseBody()
    public ResponseEntity<String> deleteHotel(@PathVariable long hotelId, @PathVariable Integer roomId) {
        roomsService.deleteRoomById(hotelId, roomId);
        return ResponseEntity.ok("{\"message\": \"success\"}");

    }

    @PutMapping("/{id}")
    @ResponseBody()
    public ResponseEntity<Room> updateRoom(@PathVariable Integer hotelId, @PathVariable Integer id, @RequestBody Room data) {
        return roomsService.updateRoomById(hotelId, id, data);
    }
}
