package io.github.hotelmanagement.model.reservation;

import io.github.hotelmanagement.model.date.DateTimeProvider;
import io.github.hotelmanagement.model.reservation.validator.CancelReservationValidator;
import io.github.hotelmanagement.model.reservation.validator.CancelReservationValidatorImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CancelReservationValidatorImplTest {

    private CancelReservationValidator cancelReservationValidator;
    private DateTimeProvider dateTimeProvider;

    @BeforeEach
    void setUp(){
        dateTimeProvider = mock(DateTimeProvider.class);
        cancelReservationValidator = new CancelReservationValidatorImpl(dateTimeProvider);
    }

    @Test
    void throwException_whenCancelReservationIsTwoDayBeforeStart(){

        LocalDateTime startReservation = LocalDateTime.of(2024, 10, 10, 0, 0);
        LocalDateTime mockCancelReservationDateTime = LocalDateTime.of(2024, 10, 8, 0, 0);

        when(dateTimeProvider.now()).thenReturn(mockCancelReservationDateTime);

        Reservation reservation = Reservation.builder()
                .id(1L)
                .startReservation(startReservation)
                .build();

        assertThrows(RuntimeException.class, () -> cancelReservationValidator.validate(reservation));
    }

    @Test
    void validateCancelReservation(){

        LocalDateTime startReservation = LocalDateTime.of(2024, 10, 10, 0, 0);
        LocalDateTime mockCancelReservationDateTime = LocalDateTime.of(2024, 10, 7, 0, 0);

        when(dateTimeProvider.now()).thenReturn(mockCancelReservationDateTime);

        Reservation reservation = Reservation.builder()
                .id(1L)
                .startReservation(startReservation)
                .build();

        cancelReservationValidator.validate(reservation);
    }
}