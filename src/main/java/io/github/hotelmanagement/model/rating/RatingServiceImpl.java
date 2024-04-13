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

    public RatingRoomDTO rateRoom(RatingRoomDTO ratingRoomDTO, Long roomId){

        User user = userService.getUser(ratingRoomDTO.userId());
        Room room = roomService.getRoomById(roomId);

        RatingRoom rating = RatingRoom.builder()
                .userId(ratingRoomDTO.userId())
                .stars(ratingRoomDTO.stars())
                .comment(ratingRoomDTO.comment())
                .build();

        room.getRatings().add(rating);
        user.setDidUserRate(true);
        return RatingMapper.entityToDTO(ratingRepository.save(rating));
    }
}
