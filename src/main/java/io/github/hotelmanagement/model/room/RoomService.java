package io.github.hotelmanagement.model.room;

import io.github.hotelmanagement.model.exception.NotFoundException;

import java.time.LocalDateTime;

public interface RoomService {
    Room getAvailableRoom(final LocalDateTime startDate, final LocalDateTime endDate, final int bedAmount);
    RoomDTO updateRoom(Long id, Room toUpdate) throws NotFoundException;
    RoomDTO createRoom(RoomDTO roomDTO);
}
