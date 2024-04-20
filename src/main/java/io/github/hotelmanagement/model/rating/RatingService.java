package io.github.hotelmanagement.model.rating;



public interface RatingService {
    RatingRoomDTO rateRoom(RatingRoomDTO ratingRoomDTO, Long roomId, Long userId) throws Exception;
}
