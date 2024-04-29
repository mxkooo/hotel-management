package io.github.hotelmanagement.model.room;

import io.github.hotelmanagement.model.exception.NotFoundException;
import io.github.hotelmanagement.model.price.Price;
import io.github.hotelmanagement.model.reservation.Reservation;
import io.github.hotelmanagement.model.room.exception.GetAvailableRoomException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatcher;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class RoomServiceImplTest {

    private RoomRepository roomRepository;
    private RoomService roomService;

    @BeforeEach
    void prepare() {
        roomRepository = mock(RoomRepository.class);
        roomService = new RoomServiceImpl(roomRepository);
    }

    private final static LocalDateTime START_RESERVATION = LocalDateTime.of(2024, 10, 10, 0, 0);
    private final static LocalDateTime END_RESERVATION = LocalDateTime.of(2024, 10, 13, 0, 0);
    private final static int BED_AMOUNT = 2;

    @Test
    void getAvailableRoom_whenReservationIsNull() {
        // given
        Room room = new Room();
        room.setReservations(null);

        when(roomRepository.findRoomsByBedAmount(BED_AMOUNT)).thenReturn(List.of(room));

        // when
        Room room2 = roomService.getAvailableRoom(START_RESERVATION, END_RESERVATION, BED_AMOUNT);

        // then
        assertNotNull(room2);
    }

    @Test
    void getAvailableRoom_whenReservationIsEmptyList() {
        // given
        Room room = new Room();
        room.setReservations(new ArrayList<>());

        when(roomRepository.findRoomsByBedAmount(BED_AMOUNT)).thenReturn(List.of(room));
        // when
        Room room2 = roomService.getAvailableRoom(START_RESERVATION, END_RESERVATION, BED_AMOUNT);

        // then
        assertNotNull(room2);
    }

    @Test
    void getAvailableRoom_whenReservationIsOtherDate() {
        // given
        Room room = new Room();
        Reservation reservation = new Reservation(
                1L,
                START_RESERVATION,
                END_RESERVATION,
                true,
                null,
                null);
        room.setReservations(List.of(reservation));

        LocalDateTime dateStartReservationByUser = END_RESERVATION.plusDays(1);
        LocalDateTime dateEndReservationByUser = dateStartReservationByUser.plusDays(2);

        when(roomRepository.findRoomsByBedAmount(BED_AMOUNT)).thenReturn(List.of(room));

        // when
        Room room2 = roomService.getAvailableRoom(dateStartReservationByUser, dateEndReservationByUser, BED_AMOUNT);

        // then
        assertNotNull(room2);
    }

    @Test
    void throwException_whenRoomIsNoAvailable1() {
        // given
        Room room = new Room();
        Reservation reservation = new Reservation(
                1L,
                START_RESERVATION,
                END_RESERVATION.minusDays(1),
                true,
                null,
                null);
        room.setReservations(List.of(reservation));

        when(roomRepository.findRoomsByBedAmount(BED_AMOUNT)).thenReturn(List.of(room));

        // then
        assertThrows(GetAvailableRoomException.class, () -> roomService.getAvailableRoom(START_RESERVATION, END_RESERVATION, BED_AMOUNT));
    }

    @Test
    void createRoom(){
        //given
        RoomDTO roomDTO = new RoomDTO(1L, Price.countPrice(BED_AMOUNT),BED_AMOUNT,6,false, new ArrayList<>(),new ArrayList<>());
        RoomDTO roomDTO = new RoomDTO(1L, Price.countPrice(BED_AMOUNT),BED_AMOUNT,6,false, new ArrayList<>());

        Room created = new Room();
        created.setId(1L);
        created.setPricePerNight(Price.countPrice(BED_AMOUNT));
        created.setBedAmount(BED_AMOUNT);
        created.setMaxPeopleInside(6);
        created.setReserved(false);
        created.setReservations(new ArrayList<>());

        //when
        when(roomRepository.save(any(Room.class))).thenReturn(created);

        RoomDTO createdDTO = roomService.createRoom(roomDTO);

        //then
        assertEquals(roomDTO.id(), createdDTO.id());
        assertEquals(roomDTO.pricePerNight(), createdDTO.pricePerNight());
        assertEquals(roomDTO.bedAmount(), createdDTO.bedAmount());
        assertEquals(roomDTO.maxPeopleInside(), createdDTO.maxPeopleInside());
        assertEquals(roomDTO.isReserved(), createdDTO.isReserved());

        verify(roomRepository, times(1)).save(any(Room.class));

    }

    @Test
    void updateRoom() throws NotFoundException{
        //given
        Room toUpdate = new Room(1L,Price.countPrice(BED_AMOUNT),BED_AMOUNT,4,false, new ArrayList<>(),new ArrayList<>());
        Room toUpdate1 = new Room(1L,Price.countPrice(BED_AMOUNT),BED_AMOUNT,4,false, new ArrayList<>(), new ArrayList<>());

        when(roomRepository.findById(1L)).thenReturn(Optional.of(toUpdate));
        when(roomRepository.save(any(Room.class))).thenAnswer(invocation -> invocation.getArgument(0));
        var room = RoomMapper.entityToDTO(toUpdate);
        RoomDTO updated = roomService.updateRoom(1L, room);
        assertEquals(toUpdate.getId(), updated.id());
        assertNotNull(toUpdate);
        assertNotNull(updated);


        verify(roomRepository).findById(1L);
        verify(roomRepository).save(any(Room.class));
    }
}
