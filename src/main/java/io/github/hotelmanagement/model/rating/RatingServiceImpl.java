package io.github.hotelmanagement.model.rating;

import io.github.hotelmanagement.model.reservation.Reservation;
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
        User user = userService.getUser(userId);
        Room room = roomService.getRoomById(roomId);

        List<RatingRoom> userRatings = user.getRatings();
        List<RatingRoom> roomRatings = room.getRatings();
        List<Reservation> userReservations = user.getReservations();

        boolean isRatedByUser  = isRatedByUser(roomId, user);
        if (isRatedByUser ){
            throw new Exception("You have already rated a room");
        }

        boolean isWasUserGuest = userService.isWasUserGuest(userReservations, user);
        if (!isWasUserGuest) {
            throw new Exception("You haven't been a guest of this hotel.");
        }

        RatingRoom rating = RatingRoom.builder()
                .id(ratingRoomDTO.id())
                .ratingStars(new RatingStars(ratingRoomDTO.stars()))
                .comment(ratingRoomDTO.comment())
                .room(room)
                .user(user)
                .build();


        roomRatings.add(rating);
        userRatings.add(rating);
        return RatingMapper.entityToDTO(ratingRepository.save(rating));
    }
    boolean isRatedByUser(Long roomId, User user){
        return user.getRatings()
                .stream()
                .anyMatch(rating -> rating.getRoom().getId().equals(roomId));

    }
}
