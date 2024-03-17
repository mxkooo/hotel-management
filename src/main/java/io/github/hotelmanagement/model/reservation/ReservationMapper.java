package io.github.hotelmanagement.model.reservation;

import io.github.hotelmanagement.model.user.User;
import io.github.hotelmanagement.model.user.UserDTO;
import io.github.hotelmanagement.model.user.UserMapper;

import java.util.Optional;

public class ReservationMapper {

    static Reservation mapToDTO(ReservationDTO dto) {

        User user = Optional.ofNullable(dto.userDTO())
                .map(UserMapper::mapToEntity)
                .orElse(null);

        return Reservation.builder()
                .id(dto.id())
                .startReservation(dto.startReservation())
                .endReservation(dto.endReservation())
                .isReserved(dto.isReserved())
                .user(user)
                .build();
    }

    static ReservationDTO entityToDTO(Reservation reservation) {

        UserDTO userDTO = Optional.ofNullable(reservation.getUser())
                .map(UserMapper::mapToDTO)
                .orElse(null);

        return ReservationDTO.builder()
                .id(reservation.getId())
                .startReservation(reservation.getStartReservation())
                .endReservation(reservation.getEndReservation())
                .isReserved(reservation.isReserved())
                .userDTO(userDTO)
                .build();
    }
}
