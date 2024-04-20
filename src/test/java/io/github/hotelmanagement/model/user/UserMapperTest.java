package io.github.hotelmanagement.model.user;

import io.github.hotelmanagement.model.reservation.Reservation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class UserMapperTest {

    @ParameterizedTest
    @MethodSource("userWithReservationsProvider")
    public void mapToDTO_UserWithReservations(User user) {
        // when
        UserDTO userDTO = UserMapper.mapToDTO(user);

        // then
        assertNotNull(userDTO);
        assertEquals(user.getId(), userDTO.id());
        assertEquals(user.getName(), userDTO.name());
        assertEquals(user.getLastName(), userDTO.lastName());
        assertNotNull(userDTO.reservationDTOS());
    }

    private static Stream<User> userWithReservationsProvider() {
        return Stream.of(
                new User(1L, "Jan", "Paweł",
                        Collections.emptyList(),
                        new ArrayList<>()),

                new User(2L, "Adam", "Nowak",
                        Arrays.asList(
                                Reservation.builder().id(1L).build(),
                                Reservation.builder().id(2L).build()
                        ),
                        new ArrayList<>()),

                new User(3L, "Anna", "Kowalska",
                        null,
                        null
                ));
    }
}