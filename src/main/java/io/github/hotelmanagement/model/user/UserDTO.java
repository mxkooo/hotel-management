package io.github.hotelmanagement.model.user;


import io.github.hotelmanagement.model.rating.RatingEditCounter;
import io.github.hotelmanagement.model.rating.RatingRoomDTO;
import io.github.hotelmanagement.model.reservation.ReservationDTO;
import lombok.Builder;

import java.util.List;

@Builder
public record UserDTO(
        Long id,
        String name,
        String lastName,
        List<ReservationDTO> reservationDTOS,
        List<RatingRoomDTO> ratingsDTOS,
        List<RatingEditCounter> ratingEditCounter
) {
}
