package io.github.hotelmanagement.model.reservation;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    boolean existsReservationByStartReservation(LocalDateTime startDate);
}
