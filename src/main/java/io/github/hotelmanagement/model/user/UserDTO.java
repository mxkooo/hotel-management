package io.github.hotelmanagement.model.user;

import io.github.hotelmanagement.model.reservation.ReservationDTO;
import lombok.Builder;
import lombok.Setter;

import java.util.List;

@Builder
public record UserDTO(
        Long id,
        String name,
        String lastName,
        List<ReservationDTO> reservationDTOS
) {
}
