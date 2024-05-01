package io.github.hotelmanagement.model.rating;

import io.github.hotelmanagement.model.reservation.Reservation;
import io.github.hotelmanagement.model.room.RoomDTO;
import io.github.hotelmanagement.model.room.RoomMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import io.github.hotelmanagement.model.user.User;
import io.github.hotelmanagement.model.room.Room;
import io.github.hotelmanagement.model.user.UserService;
import io.github.hotelmanagement.model.room.RoomService;

import java.util.List;

@Service
@AllArgsConstructor
public class RatingServiceImpl implements RatingService{
    private UserService userService;
    private RoomService roomService;
    private RatingRepository ratingRepository;

    public RatingRoomDTO rateRoom(RatingRoomDTO ratingRoomDTO, Long roomId, Long userId) throws Exception {
        Result result = getResult(roomId, userId);

        boolean isRatedByUser  = isRatedByUser(roomId, result.user());
        if (isRatedByUser ){
            throw new Exception("You have already rated a room");
        }

        boolean isWasUserGuest = userService.isWasUserGuest(result.userReservations(), result.user());
        if (!isWasUserGuest) {
            throw new Exception("You haven't been a guest of this hotel.");
        }

        RatingRoom rating = RatingRoom.builder()
                .id(ratingRoomDTO.id())
                .ratingStars(new RatingStars(ratingRoomDTO.stars()))
                .comment(ratingRoomDTO.comment())
                .room(result.room())
                .user(result.user())
                .build();
      
        checkCorrectRate(RatingMapper.entityToDTO(rating));
        result.room().getRatings().add(rating);
        result.user().getRatings().add(rating);
        return RatingMapper.entityToDTO(ratingRepository.save(rating));
    }

    public RatingRoomDTO updateRate(RatingRoomDTO ratingRoomDTO, Long roomId, Long userId, Long rateId) throws Exception {
        Result result = getResult(roomId, userId);
        deleteById(rateId);

        boolean isRatedByUser  = isRatedByUser(roomId, result.user());
        if (isRatedByUser ){
            throw new Exception("You have already rated a room");
        }

        boolean isWasUserGuest = userService.isWasUserGuest(result.userReservations(), result.user());
        if (!isWasUserGuest) {
            throw new Exception("You haven't been a guest of this hotel.");
        }
        RatingRoom newRatingRoom = new RatingRoom();
        newRatingRoom.setId(rateId);
        newRatingRoom.setRatingStars(new RatingStars(ratingRoomDTO.stars()));
        newRatingRoom.setComment(ratingRoomDTO.comment());
        newRatingRoom.setRoom(result.room());
        newRatingRoom.setUser(result.user());

        return RatingMapper.entityToDTO(newRatingRoom);
    }

    private Result getResult(Long roomId, Long userId) {
        User user = userService.getUser(userId);
        Room room = roomService.getRoomById(roomId);

        List<RatingRoom> userRatings = user.getRatings();
        List<RatingRoom> roomRatings = room.getRatings();
        List<Reservation> userReservations = user.getReservations();
        Result result = new Result(user, room, userReservations);
        return result;
    }

    private record Result(User user, Room room, List<Reservation> userReservations) {
    }

    public RatingRoomDTO findById(Long ratingId){
        RatingRoom ratingRoom = ratingRepository.findById(ratingId).orElseThrow();
        return RatingMapper.entityToDTO(ratingRoom);
    }
    boolean isRatedByUser(Long roomId, User user){
        return user.getRatings()
                .stream()
                .anyMatch(rating -> rating.getRoom().getId().equals(roomId));

    }
    void checkCorrectRate(RatingRoomDTO rating) throws Exception {
        if (rating.stars()> 5 || rating.stars() <1 && rating.comment().length() > 250){
            throw new Exception("Ratings are 1-5 and comments are max 250 signs");
        }
    }

    public void deleteById(Long rateId) throws Exception{
        if (!ratingRepository.existsById(rateId)){
            throw new Exception("Rate doesn't exist");
        }
        ratingRepository.deleteById(rateId);
    }
}
