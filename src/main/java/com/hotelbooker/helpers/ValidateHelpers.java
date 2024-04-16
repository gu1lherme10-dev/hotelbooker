package com.hotelbooker.helpers;

import com.hotelbooker.entity.Hotel;
import com.hotelbooker.entity.Room;

public class ValidateHelpers {


    public static void validateHotel(Hotel hotel, long roomId) {
        if (!hotel.getRooms().stream().anyMatch(r -> r.getId() == roomId)) {
            throw new IllegalArgumentException("Room does not belong to the specified hotel");
        }
    }


    public static void validateRoom(Room room, long reserveId) {
        if (!room.getReserves().stream().anyMatch(r -> r.getId() == reserveId)) {
            throw new IllegalArgumentException("Reserve does not belong to the specified Room");
        }
    }

    public static void validateHotelAndRoom(Hotel hotel, Room room, Integer reserveId) {
        validateHotel(hotel, room.getId());
        validateRoom(room, reserveId);
    }
}
