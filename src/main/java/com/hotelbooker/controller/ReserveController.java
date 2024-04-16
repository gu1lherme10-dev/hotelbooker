package com.hotelbooker.controller;

import com.hotelbooker.entity.Hotel;
import com.hotelbooker.entity.Reserve;
import com.hotelbooker.entity.Room;
import com.hotelbooker.service.ReserveService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = "/hotels/{hotelId}/rooms/{roomId}/reserve", produces = MediaType.APPLICATION_JSON_VALUE)
public class ReserveController {

    @Autowired
    ReserveService reserveService;

    @PostMapping()
    @ResponseBody()
    public ResponseEntity<?> createReserve(@Valid @RequestBody Reserve data, @PathVariable Integer hotelId, @PathVariable Integer roomId){
        return reserveService.createReserve(data, hotelId, roomId);
    }

    @PutMapping("/{id}")
    @ResponseBody()
    public ResponseEntity<?> updateRoom(@Valid @RequestBody Reserve data, @PathVariable Integer hotelId, @PathVariable Integer roomId, @PathVariable Integer id) {
        return reserveService.updateReserve(data, hotelId, roomId, id);
    }

    @GetMapping
    @ResponseBody()
    public ResponseEntity<?> listReservesFromRoom(@PathVariable Integer roomId, @PathVariable Integer hotelId, @RequestParam(required = false, defaultValue = "1") int page, @RequestParam(required = false, defaultValue = "50") int size){
        return reserveService.listReservesFromRoom(hotelId, roomId, page, size);
    }

    @GetMapping("/{id}")
    @ResponseBody()
    public ResponseEntity<?> getReserveById(@PathVariable Integer hotelId, @PathVariable Integer roomId, @PathVariable Integer id) {
        return reserveService.findRoomById(hotelId, roomId, id);
    }

    @DeleteMapping("/{id}")
    @ResponseBody()
    public ResponseEntity<String> deleteHotel(@PathVariable Integer hotelId, @PathVariable Integer roomId, @PathVariable Integer id) {
        reserveService.deleteReserveById(hotelId, roomId, id);
        return ResponseEntity.ok("{\"message\": \"success\"}");

    }

}



