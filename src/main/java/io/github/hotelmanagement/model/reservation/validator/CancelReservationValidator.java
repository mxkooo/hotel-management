package io.github.hotelmanagement.model.reservation.validator;

import io.github.hotelmanagement.model.reservation.Reservation;

public interface CancelReservationValidator {

    void validate(Reservation reservation);
}
