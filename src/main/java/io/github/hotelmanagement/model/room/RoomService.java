package io.github.hotelmanagement.model.room;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class RoomService {

    public ResponseEntity<Room> createRoom(){



        return ResponseEntity.ok(Room.builder().build());
    }


}
