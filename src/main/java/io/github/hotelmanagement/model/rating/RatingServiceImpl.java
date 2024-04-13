package io.github.hotelmanagement.model.rating;

import org.springframework.stereotype.Service;
import io.github.hotelmanagement.model.user.User;
import io.github.hotelmanagement.model.room.Room;
import io.github.hotelmanagement.model.user.UserService;
import io.github.hotelmanagement.model.room.RoomService;
import io.github.hotelmanagement.model.room.RoomRepository;
import io.github.hotelmanagement.model.user.UserDTO;
import io.github.hotelmanagement.model.user.UserServiceImpl;

import java.time.LocalDateTime;

@Service
public class RatingServiceImpl {
    private UserService userService;
    private RoomService roomService;
    private RatingRepository ratingRepository;

    public RatingRoomDTO rateRoom(RatingRoomDTO ratingRoomDTO, Long roomId) throws Exception {

        User user = userService.getUser(ratingRoomDTO.userId());
        boolean empty = user.getReservations().isEmpty();
        if (user.isDidUserRate() && empty){
            throw new Exception("You have already rated room or you haven't been a guest of this hotel");
        }
        Room room = roomService.getRoomById(roomId);

        RatingRoom rating = RatingRoom.builder()
                .userId(ratingRoomDTO.userId())
                .stars(ratingRoomDTO.stars())
                .comment(ratingRoomDTO.comment())
                .build();

        if (rating.getStars()> 5 || rating.getStars() <1 && rating.getComment().length() > 250){
            throw new Exception("Ratings are 1-5 and comments are max 250 signs");
        }
        room.getRatings().add(rating);
        user.setDidUserRate(true);
        return RatingMapper.entityToDTO(ratingRepository.save(rating));
    }
}
