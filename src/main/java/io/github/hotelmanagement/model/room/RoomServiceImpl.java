package io.github.hotelmanagement.model.room;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import io.github.hotelmanagement.model.price.Price;
import io.github.hotelmanagement.model.reservation.Reservation;
import io.github.hotelmanagement.model.exception.NotFoundException;
import io.github.hotelmanagement.model.room.exception.GetAvailableRoomException;

import java.util.List;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Optional;

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

        if (startDate == null && endDate == null) {
            throw new IllegalArgumentException("date cannot be null");
        }

        try {
            Room availableRoom = roomRepository.findRoomsByBedAmount(bedAmount)
                    .stream()
                    .filter(room -> isAvailable(room, startDate, endDate))
                    .findFirst()
                    .orElseThrow(() -> new NoSuchElementException("No room available on the day: " + startDate + " to: " + endDate));

            return availableRoom;
        } catch (Exception e) {
            logger.error("An error occurred while retrieving the available room", e);
            throw new GetAvailableRoomException(e);
        }
    }

    private boolean isAvailable(Room room, LocalDateTime startDate, LocalDateTime endDate) {

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

    public RoomDTO updateRoom(Long roomId, RoomDTO toUpdate){

        if (!roomRepository.existsById(roomId)){
            throw new NotFoundException("Room doesn't exist");
        }
        Room room = getRoomById(roomId);
        room.setBedAmount(toUpdate.bedAmount());
        room.setMaxPeopleInside(toUpdate.maxPeopleInside());
        room.setReserved(toUpdate.isReserved());
        room.setPricePerNight(Price.countPrice(toUpdate.bedAmount()));

        return RoomMapper.entityToDTO(room);
    }

    public Room getRoomById(Long roomId){
        return roomRepository.findById(roomId).orElseThrow(() -> new NotFoundException("room not found"));
    }
    public void deleteById(Long roomId) throws Exception{
        if (!roomRepository.existsById(roomId)){
            throw new Exception("Room doesn't exist");
        }
        roomRepository.deleteById(roomId);
    }

}
