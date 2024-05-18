package io.github.hotelmanagement.model.reservation;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> getReservationByUserId(Long userId);

    @Override
    void deleteById(Long aLong);

    @Override
    <S extends Reservation> S save(S entity);

    @Override
    boolean existsById(Long aLong);

    @Override
    Optional<Reservation> findById(Long aLong);
}
