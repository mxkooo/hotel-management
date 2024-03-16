package io.github.hotelmanagement.model.room;

public class RoomMapper {

    static Room mapToDTO(RoomDTO roomDTO){
        return Room.builder()
                .id(roomDTO.id())
                .maxPeopleInside(roomDTO.maxPeopleInside())
                .pricePerNight(roomDTO.pricePerNight())
                .bedAmount(roomDTO.bedAmount())
                .build();
    }

    static RoomDTO entityToDTO(Room room) {
        return RoomDTO.builder()
                .id(room.getId())
                .maxPeopleInside(room.getMaxPeopleInside())
                .pricePerNight(room.getPricePerNight())
                .bedAmount(room.getBedAmount())
                .build();
    }

}

