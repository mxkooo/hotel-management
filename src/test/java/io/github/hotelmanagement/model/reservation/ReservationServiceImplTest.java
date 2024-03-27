package io.github.hotelmanagement.model.reservation;


import io.github.hotelmanagement.model.room.Room;
import io.github.hotelmanagement.model.room.RoomService;
import io.github.hotelmanagement.model.user.User;
import io.github.hotelmanagement.model.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ReservationServiceImplTest {
    private ReservationRequest request;
    private ReservationRepository reservationRepository;
    private ReservationService reservationService;
    private UserService userService;
    private RoomService roomService;
    private final static LocalDateTime START_RESERVATION = LocalDateTime.of(2024, 10, 10, 0, 0);
    private final static LocalDateTime END_RESERVATION = LocalDateTime.of(2024, 10, 13, 0, 0);
    private final static int BED_AMOUNT = 2;
    @BeforeEach
    void prepare(){
        reservationRepository = mock(ReservationRepository.class);
        reservationService = new ReservationServiceImpl(reservationRepository, roomService, userService);
    }

    @Test
    void createReservation_whenUserIdIsNull() {
        //given
        ReservationRequest reservationRequest = new ReservationRequest(START_RESERVATION, END_RESERVATION, BED_AMOUNT);
        Long userId = null;
        Room room = new Room(1L, 260, BED_AMOUNT, 4, false, null);
        User user = new User(userId, "Jan", "Nowak", null);
        Reservation reservation = new Reservation(anyLong(), START_RESERVATION, END_RESERVATION, false, room, user);

       //when
        when(roomService.getAvailableRoom(any(), any(), any())).thenReturn(room);
        when(userService.getUser(any())).thenReturn(user);
        when(reservationRepository.save(any())).thenReturn(reservation);
        ReservationDTO result = reservationService.createReservation(request, userId);
        //then
        assertNotNull(result);
    }
}