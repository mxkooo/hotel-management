package io.github.hotelmanagement.model.reservation;

import lombok.AllArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(RoutesReservation.ROOT)
@AllArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @GetMapping("/home")
    public String get(){
        return "reservation";
    }
    @GetMapping(RoutesReservation.GET + "/{userId}")
    public List<ReservationDTO> getAllUserReservations(@PathVariable Long userId) {
        return reservationService.getAllUserReservation(userId);
    }

    @PostMapping(RoutesReservation.POST + "/{userId}")
    public ReservationDTO createReservations(@RequestBody ReservationRequest request, @PathVariable Long userId){
        return reservationService.createReservation(request,userId);
    }

    @DeleteMapping(RoutesReservation.CANCEL + "/{id}")
    public void cancelReservation(@PathVariable Long id){
        reservationService.cancelReservation(id);
    }
}
