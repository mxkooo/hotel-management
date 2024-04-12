package io.github.hotelmanagement.model.rating;

public class RatingMapper {
    public static RatingRoom mapToEntity(RatingRoomDTO ratingRoomDTO){
        return RatingRoom.builder()
                .id(ratingRoomDTO.id())
                .comment(ratingRoomDTO.comment())
                .build();
    }

    public static RatingRoomDTO mapToDTO(RatingRoom ratingRoom){
        return RatingRoomDTO.builder()
                .id(ratingRoom.getId())
                .comment(ratingRoom.getComment())
                .build();
    }
}
