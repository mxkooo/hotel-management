package io.github.hotelmanagement.model.rating;

import io.github.hotelmanagement.model.exception.NotFoundException;
import io.github.hotelmanagement.model.room.Room;
import io.github.hotelmanagement.model.room.RoomService;
import io.github.hotelmanagement.model.user.User;
import io.github.hotelmanagement.model.user.UserService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RatingServiceImpl implements RatingService{
    private UserService userService;
    private RoomService roomService;
    private RatingRepository ratingRepository;
    public RatingRoomDTO rateRoom(RatingRoomDTO ratingRoomDTO, Long roomId, Long userId) throws Exception {
        User user = userService.getUser(userId);
        Room room = roomService.getRoomById(roomId);

        validateRating(user,room);
        RatingRoom ratingRoom = RatingRoom.builder()
                .ratingStars(new RatingStars(ratingRoomDTO.stars()))
                .ratingComment(new RatingComment(ratingRoomDTO.comment()))
                .room(room)
                .user(user)
                .build();

        room.getRatings().add(ratingRoom);
        user.getRatings().add(ratingRoom);
        return RatingMapper.entityToDTO(ratingRepository.save(ratingRoom));
    }

    @Transactional
    public RatingRoomDTO updateRate(RatingRoomDTO ratingRoomDTO, Long userId, Long rateId){
        RatingRoom ratingRoomToUpdate = ratingRepository.findByIdAndUserId(rateId, userId)
                .orElseThrow(() -> new NotFoundException("User " + userId + "hasn't got a rate with id" + rateId));
        ratingRoomToUpdate.setRatingStars(new RatingStars(ratingRoomDTO.stars()));
        ratingRoomToUpdate.setRatingComment(new RatingComment(ratingRoomDTO.comment()));
        return RatingMapper.entityToDTO(ratingRoomToUpdate);
    }

    public void validateRating(User user, Room room) throws Exception {
        Long roomId = room.getId();
        boolean isRatedByUser = isRatedByUser(roomId, user);
        if (isRatedByUser){
            throw new RuntimeException("It's already rated");
        }
        boolean isWasUserGuest = userService.isWasUserGuest(user.getReservations(), user);
        if (!isWasUserGuest){
            throw new RuntimeException("User wasn't a guest");
        }
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
