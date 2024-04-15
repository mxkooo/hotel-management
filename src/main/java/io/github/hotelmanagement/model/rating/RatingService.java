package io.github.hotelmanagement.model.rating;



public interface RatingService {
    RatingRoomDTO rateRoom(RatingRoomDTO ratingRoomDTO, Long roomId) throws Exception;
}
