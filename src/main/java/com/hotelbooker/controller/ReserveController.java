package com.hotelbooker.controller;

import com.hotelbooker.entity.Reserve;
import com.hotelbooker.entity.User;
import com.hotelbooker.repository.UserRepository;
import com.hotelbooker.service.ReserveService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class ReserveController {

    @Autowired
    ReserveService reserveService;

    @Autowired
    UserRepository userRepository;

    @PostMapping("/hotels/{hotelId}/rooms/{roomId}/reserve/{userId}")
    @ResponseBody
    public ResponseEntity<?> createReserve(@Valid @RequestBody Reserve data, @PathVariable Integer hotelId, @PathVariable Integer roomId, @PathVariable Integer userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            data.setUser(user);
        }

        return reserveService.createReserve(data, hotelId, roomId);
    }

    @PutMapping("/hotels/{hotelId}/rooms/{roomId}/reserve/{id}")
    @ResponseBody
    public ResponseEntity<?> updateRoom(@Valid @RequestBody Reserve data, @PathVariable Integer hotelId, @PathVariable Integer roomId, @PathVariable Integer id) {
        return reserveService.updateReserve(data, hotelId, roomId, id);
    }

    @GetMapping("/hotels/{hotelId}/rooms/{roomId}/reserve")
    @ResponseBody
    public ResponseEntity<?> listReservesFromRoom(@PathVariable Integer roomId, @PathVariable Integer hotelId, @RequestParam(required = false, defaultValue = "1") int page, @RequestParam(required = false, defaultValue = "50") int size) {
        return reserveService.listReservesFromRoom(hotelId, roomId, page, size);
    }

    @GetMapping("/hotels/{hotelId}/rooms/{roomId}/reserve/{id}")
    @ResponseBody
    public ResponseEntity<?> getReserveById(@PathVariable Integer hotelId, @PathVariable Integer roomId, @PathVariable Integer id) {
        return reserveService.findRoomById(hotelId, roomId, id);
    }

    @DeleteMapping("/hotels/{hotelId}/rooms/{roomId}/reserve/{id}")
    @ResponseBody
    public ResponseEntity<String> deleteHotel(@PathVariable Integer hotelId, @PathVariable Integer roomId, @PathVariable Integer id) {
        reserveService.deleteReserveById(hotelId, roomId, id);
        return ResponseEntity.ok("{\"message\": \"success\"}");
    }

    @GetMapping("/reserve/userId/{userId}")
    @ResponseBody
    public ResponseEntity<?> listReservesByUser(@PathVariable Integer userId, @RequestParam(required = false, defaultValue = "1") int page, @RequestParam(required = false, defaultValue = "50") int size) {
        return reserveService.listReservesByUser(userId, page, size);
    }
}
