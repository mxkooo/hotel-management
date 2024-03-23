package io.github.hotelmanagement.model.reservation;

import io.github.hotelmanagement.model.exception.NotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import io.github.hotelmanagement.model.room.*;
import io.github.hotelmanagement.model.user.*;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.beans.Transient;
import java.util.List;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private final ReservationRepository reservationRepository;
    private final RoomService roomService;
    private final UserRepository userRepository;
    @Autowired
    private final RoomRepository roomRepository;
    @Override
    public List<ReservationDTO> getAllUserReservation(Long userId) {
        return reservationRepository.getReservationByUserId(userId)
                .stream()
                .map(ReservationMapper::entityToResponse)
                .toList();
    }

    @Transactional
    public ReservationDTO createReservation(ReservationRequest request, Long userId){
        RoomDTO room = roomService.getAvailableRoom(
            request.startReservation(),
            request.endReservation(),
            request.bedAmount());

        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("user not found"));

        Reservation reservation = Reservation.builder()
                .user(user)
                .room(RoomMapper.mapToEntity(room))
                .startReservation(request.startReservation())
                .endReservation(request.endReservation())
                .build();

        RoomMapper.mapToEntity(room).getReservations().add(reservation);
        user.getReservations().add(reservation);

        return ReservationMapper.entityToDTO(reservationRepository.save(reservation));
    }
}