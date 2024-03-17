package io.github.hotelmanagement.model.user;

import io.github.hotelmanagement.model.reservation.Reservation;
import io.github.hotelmanagement.model.reservation.ReservationDTO;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserMapper {

    public static UserDTO mapToDTO(User user) {

        List<ReservationDTO> reservationDTOS = Optional.ofNullable(user.getReservations())
                .orElse(Collections.emptyList())
                .stream()
                .map(reservation -> new ReservationDTO(
                        reservation.getId(),
                        reservation.getStartReservation(),
                        reservation.getEndReservation(),
                        reservation.isReserved(),
                        null))
                .collect(Collectors.toList());

        return UserDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .lastName(user.getLastName())
                .reservationDTOS(reservationDTOS)
                .build();

    }

    public static User mapToEntity(UserDTO userDTO) {

        List<Reservation> reservations = Optional.ofNullable(userDTO.reservationDTOS())
                .orElse(Collections.emptyList())
                .stream()
                .map(dto -> new Reservation(
                        dto.id(),
                        dto.startReservation(),
                        dto.endReservation(),
                        dto.isReserved(),
                        null))
                .collect(Collectors.toList());

        return User.builder()
                .id(userDTO.id())
                .name(userDTO.name())
                .lastName(userDTO.lastName())
                .reservations(reservations)
                .build();
    }
}
