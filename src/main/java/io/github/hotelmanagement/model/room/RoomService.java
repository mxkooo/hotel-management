package io.github.hotelmanagement.model.room;

import java.time.LocalDateTime;

public interface RoomService {
    RoomDTO getAvailableRoom(final LocalDateTime startDate, final LocalDateTime endDate, final int bedAmount);
    RoomDTO updateRoom(Long id, Room toUpdate) throws Exception;
    RoomDTO createRoom(RoomDTO roomDTO);
}
