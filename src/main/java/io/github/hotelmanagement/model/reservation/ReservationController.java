package io.github.hotelmanagement.model.reservation;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/reservation")
@AllArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @GetMapping("/all/{userId}")
    public List<ReservationDTO> getAllUserReservations(@PathVariable Long userId) {
        return reservationService.getAllUserReservation(userId);
    }
}
