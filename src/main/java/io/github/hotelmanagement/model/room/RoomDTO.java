package io.github.hotelmanagement.model.room;

import io.github.hotelmanagement.model.reservation.ReservationDTO;
import lombok.Builder;
import lombok.Setter;

import java.util.List;

@Builder
@Setter
public record RoomDTO(
        Long id,
        double pricePerNight,
        int bedAmount,
        int maxPeopleInside,
        boolean isReserved,
        List<ReservationDTO> reservationDTOS
)
{
}
