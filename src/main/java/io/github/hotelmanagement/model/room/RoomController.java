package io.github.hotelmanagement.model.room;

import io.github.hotelmanagement.model.user.UserDTO;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(RoutesRoom.ROOT)
public class RoomController {

    private RoomService roomService;

    @PostMapping(RoutesRoom.POST)
    RoomDTO crateRoom(@RequestBody RoomDTO roomDTO){
       return roomService.createRoom(roomDTO);
    }
    @DeleteMapping(RoutesRoom.DELETE + "/{roomId}")
    void deleteRoom(@PathVariable Long roomId) throws Exception{
        roomService.deleteById(roomId);
    }
}
