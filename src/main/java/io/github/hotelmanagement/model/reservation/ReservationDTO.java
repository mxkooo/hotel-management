package io.github.hotelmanagement.model.reservation;

import io.github.hotelmanagement.model.room.RoomDTO;
import io.github.hotelmanagement.model.user.User;
import io.github.hotelmanagement.model.user.UserDTO;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ReservationDTO(
        Long id,
        LocalDateTime startReservation,
        LocalDateTime endReservation,
        boolean isReserved,
        UserDTO userDTO,

        RoomDTO roomDTO
) {
}
