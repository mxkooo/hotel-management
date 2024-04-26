package io.github.hotelmanagement.model.room;

import io.github.hotelmanagement.model.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    @PatchMapping(RoutesRoom.UPDATE + "/{roomId}")
    RoomDTO updateRoom(@PathVariable Long roomId, @RequestBody @Valid RoomDTO toUpdate) throws NotFoundException{
        return roomService.updateRoom(roomId, toUpdate);
    }
}
