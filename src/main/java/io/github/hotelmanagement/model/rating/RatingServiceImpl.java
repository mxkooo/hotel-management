package io.github.hotelmanagement.model.rating;

import io.github.hotelmanagement.model.reservation.Reservation;
import io.github.hotelmanagement.model.reservation.ReservationDTO;
import io.github.hotelmanagement.model.reservation.ReservationMapper;
import io.github.hotelmanagement.model.reservation.ReservationService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import io.github.hotelmanagement.model.user.User;
import io.github.hotelmanagement.model.room.Room;
import io.github.hotelmanagement.model.user.UserService;
import io.github.hotelmanagement.model.room.RoomService;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class RatingServiceImpl implements RatingService{
    private UserService userService;
    @Lazy
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
      
        checkCorrectRate(RatingMapper.entityToDTO(rating));
        roomRatings.add(rating);
        userRatings.add(rating);
        return RatingMapper.entityToDTO(ratingRepository.save(rating));
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

    public void deleteById(Long roomId) throws Exception{
        if (!ratingRepository.existsById(roomId)){
            throw new Exception("Room doesn't exist");
        }
        ratingRepository.deleteById(roomId);
    }
}
