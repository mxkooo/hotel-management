package io.github.hotelmanagement.model.reservation;

import java.util.List;

public interface ReservationService {

    List<ReservationDTO> getAllUserReservation(Long userId);
    ReservationDTO findById(Long reservationId);
    public ReservationDTO createReservation(ReservationRequest request, Long userId);
    void cancelReservation(Long id);

    ReservationDTO deleteAndCreateNewReservation(Long reservationId, ReservationRequest request, Long userId);

}
