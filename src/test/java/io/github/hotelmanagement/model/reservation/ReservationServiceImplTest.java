package io.github.hotelmanagement.model.reservation;


import io.github.hotelmanagement.model.reservation.validator.CancelReservationValidator;
import io.github.hotelmanagement.model.room.Room;
import io.github.hotelmanagement.model.room.RoomService;
import io.github.hotelmanagement.model.user.User;
import io.github.hotelmanagement.model.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Optional;
import org.mockito.ArgumentCaptor;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ReservationServiceImplTest {
    private ReservationRepository reservationRepository;
    private ReservationService reservationService;
    private CancelReservationValidator cancelReservationValidator;
    private RoomService roomService;
    private UserService userService;
    private final static LocalDateTime START_RESERVATION = LocalDateTime.of(2024, 10, 10, 0, 0);
    private final static LocalDateTime END_RESERVATION = LocalDateTime.of(2024, 10, 15, 0, 0);
    private final static int BED_AMOUNT = 2;
    static ReservationRequest reservationRequest = new ReservationRequest(START_RESERVATION, END_RESERVATION, BED_AMOUNT);
    static Room room = new Room(1L, 260, BED_AMOUNT, 4, false, new ArrayList<>(),new ArrayList<>());
    static User user = new User(1L, "Jan", "Nowak", new ArrayList<>(),new ArrayList<>());


    @BeforeEach
    void prepare() {
        cancelReservationValidator = mock(CancelReservationValidator.class);
        roomService = mock(RoomService.class);
        userService = mock(UserService.class);
        reservationRepository = mock(ReservationRepository.class);
        reservationService = new ReservationServiceImpl(reservationRepository, cancelReservationValidator, roomService, userService);
    }

    @Test
    void createReservation() {
        //given
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

    @Test
    void getAllUserReservation() {
        Reservation reservation1 = new Reservation(1L, START_RESERVATION, END_RESERVATION, false, room, user);
        Reservation reservation2 = new Reservation(2L, LocalDateTime.of(2024, 1, 12, 0, 0), LocalDateTime.of(2024, 1, 17, 0, 0), false, room, user);
        //given
        List<Reservation> userReservations = new ArrayList<>();
        userReservations.add(reservation1);
        userReservations.add(reservation2);
        //when
        when(reservationRepository.getReservationByUserId(1L)).thenReturn(userReservations);
        List<ReservationDTO> result = reservationService.getAllUserReservation(1L);

        //then
        assertNotNull(result);
        assertEquals(userReservations.size(), result.size());

        for (int i = 0; i < userReservations.size(); i++) {
            assertEquals(userReservations.get(i).getId(), result.get(i).id());
        }
    }

    @Test
void cancelReservation() {
        Reservation reservation = new Reservation(1L, START_RESERVATION, END_RESERVATION, false, room, user);

        when(reservationRepository.findById(1L)).thenReturn(Optional.of(reservation));

        reservationService.cancelReservation(1L);

        verify(reservationRepository, times(1)).findById(1L);


        verify(cancelReservationValidator, times(1)).validate(reservation);

        ArgumentCaptor<Long> argumentCaptor = ArgumentCaptor.forClass(Long.class);
        verify(reservationRepository, times(1)).
                deleteById(argumentCaptor.capture());
        Long capturedId = argumentCaptor.getValue();
        assertEquals(1L, capturedId);
    }
}