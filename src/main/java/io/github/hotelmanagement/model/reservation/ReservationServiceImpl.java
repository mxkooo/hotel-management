package io.github.hotelmanagement.model.reservation;

import io.github.hotelmanagement.model.exception.NotFoundException;
import io.github.hotelmanagement.model.reservation.validator.CancelReservationValidator;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import io.github.hotelmanagement.model.room.*;
import io.github.hotelmanagement.model.user.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final CancelReservationValidator cancelReservationValidator;
    private final RoomService roomService;
    private final UserService userService;

    @Override
    public List<ReservationDTO> getAllUserReservation(Long userId) {
        return reservationRepository.getReservationByUserId(userId)
                .stream()
                .map(ReservationMapper::entityToResponse)
                .toList();
    }

    @Transactional
    public ReservationDTO createReservation(ReservationRequest request, Long userId) {

        if (request.startReservation().isBefore(LocalDateTime.now()) || request.endReservation().isBefore(request.startReservation())) {
            throw new IllegalArgumentException();
        }

        Room room = roomService.getAvailableRoom(
                request.startReservation(),
                request.endReservation(),
                request.bedAmount());

        User user = userService.getUser(userId);

        Reservation reservation = Reservation.builder()
                .user(user)
                .room(room)
                .startReservation(request.startReservation())
                .endReservation(request.endReservation())
                .build();

        room.getReservations().add(reservation);
        user.getReservations().add(reservation);

        return ReservationMapper.entityToDTO(reservationRepository.save(reservation));
    }
    public ReservationDTO findById(Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId).orElseThrow();
        return ReservationMapper.entityToDTO(reservation);
    }

    @Transactional
    public ReservationDTO deleteAndCreateNewReservation(Long reservationId, ReservationRequest request, Long userId) {

        cancelReservation(reservationId);
        ReservationDTO reservation = createReservation(request, userId);
        Reservation reservationToSave = ReservationMapper.mapToEntity(reservation);

        return ReservationMapper.entityToDTO(reservationRepository.save(reservationToSave));
    }


    public void cancelReservation(Long reservationId){
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new NotFoundException("Reservation with given id " + reservationId + " does not exist"));

        cancelReservationValidator.validate(reservation);
        reservationRepository.deleteById(reservationId);
    }
}