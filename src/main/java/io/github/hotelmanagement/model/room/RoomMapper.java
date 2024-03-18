package io.github.hotelmanagement.model.room;

import io.github.hotelmanagement.model.reservation.Reservation;
import io.github.hotelmanagement.model.reservation.ReservationDTO;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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


        return Room.builder()
                .id(roomDTO.id())
                .maxPeopleInside(roomDTO.maxPeopleInside())
                .pricePerNight(roomDTO.pricePerNight())
                .bedAmount(roomDTO.bedAmount())
                .reservations(reservations)
                .build();
    }

    public static RoomDTO mapToDTO(Room room) {

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

        return RoomDTO.builder()
                .id(room.getId())
                .maxPeopleInside(room.getMaxPeopleInside())
                .pricePerNight(room.getPricePerNight())
                .bedAmount(room.getBedAmount())
                .reservationDTOS(reservationDTOS)
                .build();
    }

}

