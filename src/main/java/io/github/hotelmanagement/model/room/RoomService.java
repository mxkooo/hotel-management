package io.github.hotelmanagement.model.room;

import java.time.LocalDateTime;
import java.util.List;

public interface RoomService {
    Room getRoomById(Long roomId);
    Room getAvailableRoom(final LocalDateTime startDate, final LocalDateTime endDate, final int bedAmount);
    RoomDTO updateRoom(Long id, RoomDTO toUpdate);
    RoomDTO createRoom(RoomDTO roomDTO);
    void deleteById(Long roomId) throws Exception;
    List<RoomDTO> getAllRooms();
    RoomDTO findById(Long roomId);

    RoomDTO getRoomByAverageStars(double minAverage);
}
