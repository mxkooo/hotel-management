package io.github.hotelmanagement.model.reservation;

import java.util.List;

public interface ReservationService {

    List<ReservationDTO> getAllUserReservation(Long userId);

}
