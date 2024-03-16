package io.github.hotelmanagement.model.reservation;

public class ReservationMapper {

    static Reservation mapToDTO(ReservationDTO dto) {
        return Reservation.builder()
                .id(dto.id())
                .startReservation(dto.startReservation())
                .endReservation(dto.endReservation())
                .isReserved(dto.isReserved())
                .build();
    }

    static ReservationDTO entityToDTO(Reservation reservation) {
        return ReservationDTO.builder()
                .id(reservation.getId())
                .startReservation(reservation.getStartReservation())
                .endReservation(reservation.getEndReservation())
                .isReserved(reservation.isReserved())
                .build();
    }
}
