package io.github.hotelmanagement.model.room;

import io.github.hotelmanagement.model.reservation.Reservation;
import io.github.hotelmanagement.model.room.exception.GetAvailableRoomException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RoomServiceImplTest {

    private RoomRepository roomRepository;
    private RoomService roomService;

    @BeforeEach
    void prepare() {
        roomRepository = mock(RoomRepository.class);
        roomService = new RoomServiceImpl(roomRepository);
    }

    private final static LocalDateTime START_RESERVATION = LocalDateTime.of(2024, 10, 10, 0, 0);
    private final static int BED_AMOUNT = 2;

    @Test
    void getAvailableRoom_whenReservationIsNull() {
        // given
        Room room = new Room();
        room.setReservations(null);

        when(roomRepository.findRoomsByBedAmount(BED_AMOUNT)).thenReturn(List.of(room));

        // when
        RoomDTO roomDTO = roomService.getAvailableRoom(START_RESERVATION, BED_AMOUNT);

        // then
        assertNotNull(roomDTO);
    }
    @Test
    void getAvailableRoom_whenReservationIsEmptyList() {
        // given
        Room room = new Room();
        room.setReservations(new ArrayList<>());

        when(roomRepository.findRoomsByBedAmount(BED_AMOUNT)).thenReturn(List.of(room));

        // when
        RoomDTO roomDTO = roomService.getAvailableRoom(START_RESERVATION, BED_AMOUNT);

        // then
        assertNotNull(roomDTO);
    }
    @Test
    void getAvailableRoom_whenReservationIsOtherDate() {
        // given
        Room room = new Room();
        Reservation reservation = new Reservation(
                1L,
                START_RESERVATION.plusYears(1),
                null,
                true,
                null,
                null);
        room.setReservations(List.of(reservation));

        when(roomRepository.findRoomsByBedAmount(BED_AMOUNT)).thenReturn(List.of(room));

        // when
        RoomDTO roomDTO = roomService.getAvailableRoom(START_RESERVATION, BED_AMOUNT);

        // then
        assertNotNull(roomDTO);
    }
    @Test
    void throwException_whenRoomIsNoAvailable() {
        // given
        Room room = new Room();
        Reservation reservation = new Reservation(
                1L,START_RESERVATION,
                null,
                true,
                null,
                null);
        room.setReservations(List.of(reservation));

        when(roomRepository.findRoomsByBedAmount(BED_AMOUNT)).thenReturn(List.of(room));

        // then
        assertThrows(GetAvailableRoomException.class, () -> roomService.getAvailableRoom(START_RESERVATION, BED_AMOUNT));
    }
}
