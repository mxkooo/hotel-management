package io.github.hotelmanagement.model.reservation;

import io.github.hotelmanagement.model.room.*;
import io.github.hotelmanagement.model.user.UserDTO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private final ReservationRepository reservationRepository;
    private final RoomService roomService;
    @Autowired
    private final RoomRepository roomRepository;
    private final RoomMapper roomMapper;
    @Override
    public List<ReservationDTO> getAllUserReservation(Long userId) {
        return reservationRepository.getReservationByUserId(userId)
                .stream()
                .map(ReservationMapper::entityToResponse)
                .toList();
    }

    public boolean createReservation(LocalDateTime startReservation, LocalDateTime endReservation, int beds, Long roomId, boolean isReserved) throws Exception{
        RoomDTO room = roomService.getAvailableRoom(startReservation, endReservation, beds);
        if (room != null) {

            Reservation reservation = new Reservation();
            reservation.setStartReservation(startReservation);
            reservation.setEndReservation(endReservation);
            reservation.setRoom(roomMapper.mapToEntity(room));

            room.builder()
                    .isReserved(true)
                    .build();

            roomService.updateRoom(roomId, roomMapper.mapToEntity(room));

            reservationRepository.save(reservation);
            return true;
        } else {
            return false;
        }
    }


}
