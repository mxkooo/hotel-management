package io.github.hotelmanagement.model.room;

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
    @PutMapping(RoutesRoom.UPDATE + "/{roomId}")
    RoomDTO updateRoom(@PathVariable Long roomId, @RequestBody @Valid RoomDTO toUpdate){
        return roomService.updateRoom(roomId, toUpdate);
    }
}
