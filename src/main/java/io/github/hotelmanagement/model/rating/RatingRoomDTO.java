package io.github.hotelmanagement.model.rating;

import io.github.hotelmanagement.model.room.RoomDTO;
import io.github.hotelmanagement.model.user.UserDTO;
import lombok.Builder;

@Builder
public record RatingRoomDTO(
        Long id,
        int stars,
        String comment,
        int editRateCounter,
        RoomDTO roomDTO,
        UserDTO userDTO
) {
}
