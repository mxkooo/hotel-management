package io.github.hotelmanagement.model.rating;

import org.springframework.stereotype.Service;
import io.github.hotelmanagement.model.user.User;
import io.github.hotelmanagement.model.room.Room;
import io.github.hotelmanagement.model.user.UserService;
import io.github.hotelmanagement.model.room.RoomService;

import java.util.List;

@Service
public class RatingServiceImpl {
    private UserService userService;
    private RoomService roomService;
    private RatingRepository ratingRepository;

    public RatingRoomDTO rateRoom(RatingRoomDTO ratingRoomDTO, Long roomId) throws Exception {
        User user = userService.getUser(ratingRoomDTO.userId());
        Room room = roomService.getRoomById(roomId);

        if (user.getRatings().contains(roomId) && user.getReservations().isEmpty()){
            throw new Exception("You have already rated room or you haven't been a guest of this hotel");
        }

        List<RatingRoom> userRatings = user.getRatings();
        List<RatingRoom> roomRatings = room.getRatings();

        RatingRoom rating = RatingRoom.builder()
                .userId(ratingRoomDTO.userId())
                .stars(ratingRoomDTO.stars())
                .comment(ratingRoomDTO.comment())
                .build();

        checkIfCorrectRate(rating);
        roomRatings.add(rating);
        userRatings.add(rating);
        return RatingMapper.entityToDTO(ratingRepository.save(rating));
    }

    private static void checkIfCorrectRate(RatingRoom rating) throws Exception {
        if (rating.getStars()> 5 || rating.getStars() <1 && rating.getComment().length() > 250){
            throw new Exception("Ratings are 1-5 and comments are max 250 signs");
        }
    }
}
