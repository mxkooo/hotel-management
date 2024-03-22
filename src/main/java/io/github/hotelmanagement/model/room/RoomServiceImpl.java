package io.github.hotelmanagement.model.room;

import io.github.hotelmanagement.model.price.Price;
import io.github.hotelmanagement.model.reservation.Reservation;
import io.github.hotelmanagement.model.room.exception.GetAvailableRoomException;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private static final Logger logger = LoggerFactory.getLogger(RoomServiceImpl.class);

    public RoomDTO createRoom(RoomDTO roomDTO) {

        int price = Price.countPrice(roomDTO.bedAmount());
        Room room = new Room();
        room.setBedAmount(roomDTO.bedAmount());
        room.setMaxPeopleInside(roomDTO.maxPeopleInside());
        room.setPricePerNight(price);
        return RoomMapper.entityToDTO(roomRepository.save(room));
    }

    public Room getAvailableRoom(final LocalDateTime startDate, final LocalDateTime endDate, final int bedAmount) {

        if (startDate == null || endDate == null) {
            throw new IllegalArgumentException("date cannot be null");
        }

        try {
            Room availableRoom = roomRepository.findRoomsByBedAmount(bedAmount)
                    .stream()
                    .filter(room -> isAvailable(room, startDate, endDate))
                    .findFirst()
                    .orElseThrow(() -> new NoSuchElementException("No room available on the day: " + startDate + " to:" + endDate));

            return availableRoom;
        } catch (Exception e) {
            logger.error("An error occurred while retrieving the available room", e);
            throw new GetAvailableRoomException(e);
        }
    }

    public boolean isAvailable(Room room, LocalDateTime startDate, LocalDateTime endDate) {

        List<Reservation> reservations = room.getReservations();
        if (reservations == null || reservations.isEmpty()) {
            return true;
        }
        final boolean isStartDayAvailable = reservations
                .stream()
                .noneMatch(r -> r.getStartReservation().equals(startDate));

        if(!isStartDayAvailable){
            return false;
        }

        return reservations
                .stream()
                .noneMatch(reservation ->
                        reservation.getStartReservation().isAfter(startDate) &&
                                reservation.getEndReservation().isBefore(endDate));
    }
    public void updateRoom(Room room){
        room.setId(room.getId());
        room.setReserved(room.isReserved());
        room.setReservations(room.getReservations());
        room.setBedAmount(room.getBedAmount());
        room.setPricePerNight(room.getPricePerNight());
        room.setMaxPeopleInside(room.getMaxPeopleInside());
        roomRepository.save(room);
    }
}
