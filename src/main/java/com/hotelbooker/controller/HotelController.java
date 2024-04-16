package com.hotelbooker.controller;
import com.hotelbooker.entity.Hotel;
import com.hotelbooker.service.HotelService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping(value = "/hotels", produces = MediaType.APPLICATION_JSON_VALUE)
public class HotelController {
    @Autowired
    private HotelService hotelService;


    @PostMapping
    public ResponseEntity<Hotel> saveHotel(@Valid @RequestBody Hotel data){
        return hotelService.createHotel(data);
    }

    @GetMapping
    public ResponseEntity<?> listHotels(@RequestParam(required = false, defaultValue = "1") int page, @RequestParam(required = false, defaultValue = "50") int size){
        return hotelService.listHotels(page, size);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Hotel> findHotelById(@PathVariable long id){
        return hotelService.findHotelById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Hotel> updateHotel(@PathVariable int id, @RequestBody Hotel data) {
        return hotelService.updateHotel(id, data);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteHotel(@PathVariable long id) {
        hotelService.deleteHotel(id);
        return ResponseEntity.ok("{\"message\": \"success\"}");
    }

}

