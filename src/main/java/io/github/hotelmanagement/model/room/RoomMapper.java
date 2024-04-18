package io.github.hotelmanagement.model.room;

import io.github.hotelmanagement.model.rating.RatingRoom;
import io.github.hotelmanagement.model.rating.RatingRoomDTO;
import io.github.hotelmanagement.model.reservation.Reservation;
import io.github.hotelmanagement.model.reservation.ReservationDTO;
import io.github.hotelmanagement.model.user.User;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class RoomMapper {

    public static Room mapToEntity(RoomDTO roomDTO){

        List<Reservation> reservations = Optional.ofNullable(roomDTO.reservationDTOS())
                .orElse(Collections.emptyList())
                .stream()
                .map(dto -> new Reservation(
                        dto.id(),
                        dto.startReservation(),
                        dto.endReservation(),
                        dto.isReserved(),
                        null,
                        null))
                .toList();

        List<RatingRoom> rating = Optional.ofNullable(roomDTO.ratingsDTOS())
                .orElse(Collections.emptyList())
                .stream()
                .map(dto -> RatingRoom.builder()
                        .id(dto.id())
                        .stars(dto.stars())
                        .comment(dto.comment())
                        .room(null)
                        .user(null).build())
                .toList();



        return Room.builder()
                .id(roomDTO.id())
                .maxPeopleInside(roomDTO.maxPeopleInside())
                .pricePerNight(roomDTO.pricePerNight())
                .bedAmount(roomDTO.bedAmount())
                .reservations(reservations)
                .ratings(rating)
                .build();
    }

    public static RoomDTO entityToDTO(Room room) {

        List<ReservationDTO> reservationDTOS = Optional.ofNullable(room.getReservations())
                .orElse(Collections.emptyList())
                .stream()
                .map(reservation -> new ReservationDTO(
                        reservation.getId(),
                        reservation.getStartReservation(),
                        reservation.getEndReservation(),
                        reservation.isReserved(),
                        null,
                        null))
                .toList();

        List<RatingRoomDTO> ratingDTOS = Optional.ofNullable(room.getRatings())
                .orElse(Collections.emptyList())
                .stream()
                .map(rating -> RatingRoomDTO.builder()
                        .id(rating.getId())
                        .comment(rating.getComment())
                        .stars(rating.getStars())
                        .build())
                .toList();

        return RoomDTO.builder()
                .id(room.getId())
                .maxPeopleInside(room.getMaxPeopleInside())
                .pricePerNight(room.getPricePerNight())
                .bedAmount(room.getBedAmount())
                .reservationDTOS(reservationDTOS)
                .ratingsDTOS(ratingDTOS)
                .build();
    }

}

