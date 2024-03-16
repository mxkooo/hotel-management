package io.github.hotelmanagement.model.reservation;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ReservationDTO(
        Long id,
        LocalDateTime startReservation,
        LocalDateTime endReservation,
        boolean isReserved
) {
}
