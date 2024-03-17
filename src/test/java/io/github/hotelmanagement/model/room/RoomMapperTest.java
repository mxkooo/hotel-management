package io.github.hotelmanagement.model.room;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class RoomMapperTest {


    @ParameterizedTest
    @MethodSource("roomWithReservationsProvider")
    public void mapToDTO_roomWithReservations(Room room) {
        //when
        RoomDTO roomDTO = RoomMapper.entityToDTO(room);
        //then
        assertNotNull(roomDTO);
        assertEquals(room.getId(), roomDTO.id());
        assertEquals(room.getPricePerNight(), roomDTO.pricePerNight());
        assertEquals(room.getBedAmount(), roomDTO.bedAmount());
        assertEquals(room.getMaxPeopleInside(), roomDTO.maxPeopleInside());
    }

    private static Stream<Room> roomWithReservationsProvider() {
        return Stream.of(
                new Room(1L, 3, 6, 240),
                new Room(2L, 2, 4, 160)
        );
    }
}