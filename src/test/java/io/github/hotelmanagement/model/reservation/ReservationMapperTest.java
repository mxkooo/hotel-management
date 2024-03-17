package io.github.hotelmanagement.model.reservation;

import io.github.hotelmanagement.model.user.User;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ReservationMapperTest {
    @ParameterizedTest
    @MethodSource("reservationWithUserDTOProvider")
    void mapToDTO_ReservationWithUserDTO(Reservation reservation) {
        // when
        ReservationDTO reservationDTO = ReservationMapper.entityToDTO(reservation);

        // then
        assertNotNull(reservationDTO);
        assertEquals(reservation.getId(), reservationDTO.id());
        assertEquals(reservation.getStartReservation(), reservationDTO.startReservation());
        assertEquals(reservation.getEndReservation(), reservationDTO.endReservation());
        assertEquals(reservation.isReserved(), reservationDTO.isReserved());

    }

    private static Stream<Reservation> reservationWithUserDTOProvider() {
        User user = new User(1L, "Jan", "Paweł", Collections.emptyList());

        return Stream.of(
                new Reservation(1L, LocalDateTime.now(), LocalDateTime.now().plusHours(1), true, user),
                new Reservation(2L, LocalDateTime.now(), LocalDateTime.now().plusHours(2), false, user),
                new Reservation(3L, LocalDateTime.now(), LocalDateTime.now().plusHours(3), true, null)
        );
    }
}