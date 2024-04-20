package io.github.hotelmanagement.model.room;

import io.github.hotelmanagement.model.rating.RatingRoomDTO;
import io.github.hotelmanagement.model.reservation.ReservationDTO;
import lombok.Builder;

import java.util.List;

@Builder
public record RoomDTO(
        Long id,
        double pricePerNight,
        int bedAmount,
        int maxPeopleInside,
        boolean isReserved,
        List<ReservationDTO> reservationDTOS,
        List<RatingRoomDTO> ratingsDTOS
)
{
}
