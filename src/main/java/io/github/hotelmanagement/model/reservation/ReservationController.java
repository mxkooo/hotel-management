package io.github.hotelmanagement.model.reservation;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(RoutesReservation.ROOT)
@AllArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @GetMapping(RoutesReservation.GET + "/{userId}")
    public List<ReservationDTO> getAllUserReservations(@PathVariable Long userId) {
        return reservationService.getAllUserReservation(userId);
    }

    @PostMapping(RoutesReservation.POST + "/{userId}")
    public ReservationDTO createReservation(@RequestBody ReservationRequest request, @PathVariable Long userId){
        return reservationService.createReservation(request,userId);
    }

}
