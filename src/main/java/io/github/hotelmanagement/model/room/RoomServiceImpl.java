package io.github.hotelmanagement.model.room;

import io.github.hotelmanagement.model.price.Price;
import io.github.hotelmanagement.model.room.exception.GetAvailableRoomException;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private static final Logger logger = LoggerFactory.getLogger(RoomServiceImpl.class);

    public RoomDTO createRoom(RoomDTO roomDTO, int beds) {

        int price = Price.countPrice(beds);
        Room room = new Room();
        room.setBedAmount(roomDTO.bedAmount());
        room.setMaxPeopleInside(roomDTO.maxPeopleInside());
        room.setPricePerNight(price);
        return RoomMapper.entityToDTO(roomRepository.save(room));
    }

    public RoomDTO getAvailableRoom(final LocalDateTime startDate, final int bedAmount) {

        if (startDate == null) {
            throw new IllegalArgumentException("startDate cannot be null");
        }

        try {
            Room availableRoom = roomRepository.findRoomsByBedAmount(bedAmount)
                    .stream()
                    .filter(room -> isRoomAvailableOnDay(room, startDate))
                    .findFirst()
                    .orElseThrow(() -> new NoSuchElementException("No room available on the day: " + startDate));
            return RoomMapper.entityToDTO(availableRoom);
        } catch (Exception e) {
            logger.error("An error occurred while retrieving the available room", e);
            throw new GetAvailableRoomException(e);
        }
    }

    private boolean isRoomAvailableOnDay(Room room, LocalDateTime startDate) {
        if (room.getReservations() == null) {
            return true;
        }
        return room.getReservations()
                .stream()
                .noneMatch(r -> r.getStartReservation().equals(startDate));
    }
}
