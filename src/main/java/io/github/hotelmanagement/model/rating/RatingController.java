package io.github.hotelmanagement.model.rating;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(RoutesRating.ROOT)
@AllArgsConstructor
public class RatingController {
    private final RatingService ratingService;

    @PostMapping(RoutesRating.POST + "/{roomId}" + "/{userId}")
    public RatingRoomDTO rateRoom(@RequestBody RatingRoomDTO ratingRoomDTO, @PathVariable Long roomId, @PathVariable Long userId) throws Exception{
        return ratingService.rateRoom(ratingRoomDTO, roomId, userId);
    }

    @DeleteMapping(RoutesRating.DELETE + "/{rateId}")
    public void deleteRate(@PathVariable Long rateId) throws Exception{
        ratingService.deleteById(rateId);
    }
    @PutMapping(RoutesRating.UPDATE + "/{roomId}" + "/{userId}" +"/{rateId}")
    public RatingRoomDTO updateRate(@RequestBody RatingRoomDTO ratingRoomDTO,@PathVariable Long userId, @PathVariable Long rateId) throws Exception{
        return ratingService.updateRate(ratingRoomDTO, userId, rateId);
    }
}
