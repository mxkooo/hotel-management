package io.github.hotelmanagement.model.rating;

import io.github.hotelmanagement.model.reservation.Reservation;
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

        List<RatingRoom> userRatings = user.getRatings();
        List<RatingRoom> roomRatings = room.getRatings();

        checkIfUserRated(roomId, user);

        boolean isUserGuest = isUserGuest(roomId, user);

        if (!isUserGuest) {
            throw new Exception("You haven't been a guest of this hotel.");
        }

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

    private static boolean isUserGuest(Long roomId, User user) {
        boolean isUserGuest = false;
        for (Reservation reservation : user.getReservations()) {
            if (reservation.getRoom().getId().equals(roomId)) {
                isUserGuest = true;
                break;
            }
        }
        return isUserGuest;
    }

    private static void checkIfUserRated(Long roomId, User user) throws Exception {
        for (RatingRoom rating : user.getRatings()) {
            if (rating.getRoom().getId().equals(roomId)) {
                throw new Exception("You have already rated this room.");
            }
        }
    }

    private static void checkIfCorrectRate(RatingRoom rating) throws Exception {
        if (rating.getStars()> 5 || rating.getStars() <1 && rating.getComment().length() > 250){
            throw new Exception("Ratings are 1-5 and comments are max 250 signs");
        }
    }
}
