package io.github.hotelmanagement.model.room;

import java.time.LocalDateTime;

public interface RoomService {
    Room getAvailableRoom(final LocalDateTime startDate, final LocalDateTime endDate, final int bedAmount);
    boolean isAvailable(Room room, LocalDateTime startDate, LocalDateTime endDate);
    void updateRoom(Room room);
    RoomDTO createRoom(RoomDTO roomDTO);
}
