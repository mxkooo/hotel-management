package io.github.hotelmanagement.model.rating;

public class RatingMapper {
    public static RatingRoom DTOToEntity(RatingRoomDTO ratingRoomDTO){
        return RatingRoom.builder()
                .userId(ratingRoomDTO.userId())
                .comment(ratingRoomDTO.comment())
                .build();
    }

    public static RatingRoomDTO entityToDTO(RatingRoom ratingRoom){
        return RatingRoomDTO.builder()
                .userId(ratingRoom.getUserId())
                .comment(ratingRoom.getComment())
                .build();
    }
}
