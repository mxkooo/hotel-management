package io.github.hotelmanagement.model.room;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;
@Builder
public record RoomDTO(
        Long id,
        int bedAmount,
        int maxPeopleInside,
        int pricePerNight
)
{
}
