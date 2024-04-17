package io.github.hotelmanagement.model.rating;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(RoutesRating.ROOT)
@AllArgsConstructor
public class RatingController {
    private final RatingService ratingService;

    @PostMapping(RoutesRating.POST)
    public RatingRoomDTO rateRoom(RatingRoomDTO ratingRoomDTO, Long roomId, Long userId) throws Exception{
        return ratingService.rateRoom(ratingRoomDTO, roomId, userId);
    }
}
