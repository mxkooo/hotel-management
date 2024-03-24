package io.github.hotelmanagement.model.room;

import io.github.hotelmanagement.model.user.UserDTO;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping(RoutesRoom.ROOT)
public class RoomController {

    RoomService roomService;

    @PostMapping(RoutesRoom.POST)
    RoomDTO crateRoom(@RequestBody RoomDTO roomDTO){
       return roomService.createRoom(roomDTO);
    }
}
