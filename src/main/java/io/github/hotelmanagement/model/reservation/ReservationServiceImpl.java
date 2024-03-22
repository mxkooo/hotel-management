package io.github.hotelmanagement.model.reservation;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    @Override
    public List<ReservationDTO> getAllUserReservation(Long userId) {
        return reservationRepository.getReservationByUserId(userId)
                .stream()
                .map(ReservationMapper::entityToResponse)
                .toList();
    }
}
