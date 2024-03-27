package io.github.hotelmanagement.model.reservation;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ReservationRequest(
        LocalDateTime startReservation,
        LocalDateTime endReservation,
        int bedAmount
)
{}
