package io.github.hotelmanagement.model.reservation;


import io.github.hotelmanagement.model.room.Room;
import io.github.hotelmanagement.model.room.RoomService;
import io.github.hotelmanagement.model.user.User;
import io.github.hotelmanagement.model.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ReservationServiceImplTest {
    private ReservationRepository reservationRepository;
    private ReservationService reservationService;
    private RoomService roomService;
    private UserService userService;
    private final static LocalDateTime START_RESERVATION = LocalDateTime.of(2024, 10, 10, 0, 0);
    private final static LocalDateTime END_RESERVATION = LocalDateTime.of(2024, 10, 15, 0, 0);
    private final static int BED_AMOUNT = 2;
    @BeforeEach
    void prepare(){
        roomService = mock(RoomService.class);
        userService = mock(UserService.class);
        reservationRepository = mock(ReservationRepository.class);
        reservationService = new ReservationServiceImpl(reservationRepository, roomService, userService);
    }

    @Test
    void createReservation() {
        //given
        ReservationRequest reservationRequest = new ReservationRequest(START_RESERVATION, END_RESERVATION, BED_AMOUNT);
        Room room = new Room(1L, 260, BED_AMOUNT, 4, false, new ArrayList<>());
        User user = new User(1L, "Jan", "Nowak", new ArrayList<>());
        Reservation reservation = new Reservation(1L, START_RESERVATION, END_RESERVATION, false, room, user);

       //when
        when(roomService.getAvailableRoom(START_RESERVATION, END_RESERVATION, BED_AMOUNT)).thenReturn(room);
        when(userService.getUser(1L)).thenReturn(user);
        when(reservationRepository.save(any())).thenReturn(reservation);

        ReservationDTO result = reservationService.createReservation(reservationRequest, 1L);
        //then
        assertEquals(reservation.getEndReservation(), result.endReservation());
        assertEquals(reservation.getStartReservation(), result.startReservation());
        assertEquals(reservation.getEndReservation(), result.endReservation());
        assertNotNull(result.roomDTO());
        assertNotNull(result.userDTO());
        assertNotNull(result);
    }
}