package io.github.hotelmanagement.model.rating;

import lombok.Builder;

@Builder
public record RatingRoomDTO(
        Long id,
        int stars,
        String comment
) {
}
