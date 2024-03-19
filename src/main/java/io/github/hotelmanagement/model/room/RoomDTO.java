package io.github.hotelmanagement.model.room;

import io.github.hotelmanagement.model.reservation.ReservationDTO;
import lombok.Builder;

import java.util.List;

@Builder
public record RoomDTO(
        Long id,
        int bedAmount,
        int maxPeopleInside,
        int pricePerNight,

        List<ReservationDTO> reservationDTOS
)
{
}
