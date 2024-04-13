package io.github.hotelmanagement.model.rating;

import lombok.Builder;

@Builder
public record RatingRoomDTO(
        Long userId,
        int stars,
        String comment
) {
}
