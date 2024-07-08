package io.github.hotelmanagement.model.rating;



public interface RatingService {
    RatingRoomDTO rateRoom(RatingRoomDTO ratingRoomDTO, Long roomId, Long userId) throws Exception;
    RatingRoomDTO updateRate(RatingRoomDTO ratingRoomDTO, Long rateId, Long userId) throws Exception;
    void deleteById(Long rateId) throws Exception;
    RatingRoomDTO findById(Long ratingId);
}
