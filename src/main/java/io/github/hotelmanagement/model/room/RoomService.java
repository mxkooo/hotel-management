package io.github.hotelmanagement.model.room;

import java.time.LocalDateTime;

public interface RoomService {
    RoomDTO getAvailableRoom(final LocalDateTime startDate, final int bedAmount);
}
