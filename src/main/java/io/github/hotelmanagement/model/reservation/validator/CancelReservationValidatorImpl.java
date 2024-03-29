package io.github.hotelmanagement.model.reservation.validator;

import io.github.hotelmanagement.model.date.DateTimeProvider;
import io.github.hotelmanagement.model.reservation.Reservation;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@AllArgsConstructor
public class CancelReservationValidatorImpl implements CancelReservationValidator {

    private final DateTimeProvider dateTimeProvider;

    public void validate(Reservation reservation){
         checkDateCancel(reservation.getStartReservation());
    }

    private void checkDateCancel(LocalDateTime startReservation){
        LocalDateTime toDay = dateTimeProvider.now();
        if(toDay.isAfter(startReservation.minusDays(3))){
            throw new RuntimeException("You cannot cancel your reservation 2 days before the start time");
        }
    }
}
