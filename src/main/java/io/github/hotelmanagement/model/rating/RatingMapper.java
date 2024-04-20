package io.github.hotelmanagement.model.rating;

import io.github.hotelmanagement.model.room.Room;
import io.github.hotelmanagement.model.room.RoomDTO;
import io.github.hotelmanagement.model.room.RoomMapper;
import io.github.hotelmanagement.model.user.User;
import io.github.hotelmanagement.model.user.UserDTO;
import io.github.hotelmanagement.model.user.UserMapper;

import java.util.Optional;

public class RatingMapper {
    public static RatingRoom DTOToEntity(RatingRoomDTO ratingRoomDTO){

        User user = Optional.ofNullable(ratingRoomDTO.userDTO())
                .map(UserMapper::mapToEntity)
                .orElse(null);

        Room room = Optional.ofNullable(ratingRoomDTO.roomDTO())
                .map(RoomMapper::mapToEntity)
                .orElse(null);

        return RatingRoom.builder()
                .id(ratingRoomDTO.id())
                .stars(ratingRoomDTO.stars())
                .comment(ratingRoomDTO.comment())
                .room(room)
                .user(user)
                .build();
    }

    public static RatingRoomDTO entityToDTO(RatingRoom ratingRoom){

        UserDTO userDTO = Optional.ofNullable(ratingRoom.getUser())
                .map(UserMapper::mapToDTO)
                .orElse(null);

        RoomDTO roomDTO = Optional.ofNullable(ratingRoom.getRoom())
                .map(RoomMapper::entityToDTO)
                .orElse(null);

        return RatingRoomDTO.builder()
                .id(ratingRoom.getId())
                .stars(ratingRoom.getStars())
                .comment(ratingRoom.getComment())
                .roomDTO(roomDTO)
                .userDTO(userDTO)
                .build();
    }
}
