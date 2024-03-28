package io.github.hotelmanagement.model.reservation;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> getReservationByUserId(Long userId);

    @Override
    void deleteById(Long aLong);

    @Override
    <S extends Reservation> S save(S entity);

    @Override
    boolean existsById(Long aLong);
}
