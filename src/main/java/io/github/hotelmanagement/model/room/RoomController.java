package io.github.hotelmanagement.model.room;



import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import javax.validation.Valid;


@RestController
@AllArgsConstructor
@RequestMapping(RoutesRoom.ROOT)
public class RoomController {

    private RoomService roomService;

    @PostMapping(RoutesRoom.POST)
    public RoomDTO crateRoom(@RequestBody RoomDTO roomDTO){
       return roomService.createRoom(roomDTO);
    }
    @DeleteMapping(RoutesRoom.DELETE + "/{roomId}")
    public void deleteRoom(@PathVariable Long roomId) throws Exception{
        roomService.deleteById(roomId);
    }


    @GetMapping(RoutesRoom.GET + "/{roomId}")
    public RoomDTO findById(@PathVariable Long roomId){
        return roomService.findById(roomId);
    }

    @GetMapping(RoutesRoom.GET + "/all")
    public List<RoomDTO> getAllRooms(){
        return roomService.getAllRooms();
    }
  
    @PutMapping(RoutesRoom.UPDATE + "/{roomId}")
    RoomDTO updateRoom(@PathVariable Long roomId, @RequestBody @Valid RoomDTO toUpdate){
        return roomService.updateRoom(roomId, toUpdate);
    }

}
