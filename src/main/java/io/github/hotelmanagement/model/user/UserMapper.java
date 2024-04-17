package io.github.hotelmanagement.model.user;

import io.github.hotelmanagement.model.rating.RatingRoom;
import io.github.hotelmanagement.model.rating.RatingRoomDTO;
import io.github.hotelmanagement.model.reservation.Reservation;
import io.github.hotelmanagement.model.reservation.ReservationDTO;
import io.github.hotelmanagement.model.room.Room;

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
                        null,
                        null))
                .collect(Collectors.toList());

        List<RatingRoomDTO> ratingsDTOS = Optional.ofNullable(user.getRatings())
                .orElse(Collections.emptyList())
                .stream()
                .map(dto -> new RatingRoomDTO(
                        dto.getId(),
                        dto.getStars(),
                        dto.getComment()))
                .toList();

        return UserDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .lastName(user.getLastName())
                .reservationDTOS(reservationDTOS)
                .ratingsDTOS(ratingsDTOS)
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
                        null,
                        null))
                .collect(Collectors.toList());

        List<RatingRoom> ratings = Optional.ofNullable(userDTO.ratingsDTOS())
                .orElse(Collections.emptyList())
                .stream()
                .map(dto -> new RatingRoom(
                        dto.id(),
                        dto.stars(),
                        dto.comment(),
                        Room.builder().build()))
                .toList();

        return User.builder()
                .id(userDTO.id())
                .name(userDTO.name())
                .lastName(userDTO.lastName())
                .reservations(reservations)
                .ratings(ratings)
                .build();
    }
}
