package io.github.hotelmanagement.model.price;

import io.github.hotelmanagement.model.room.RoomMapper;
import io.github.hotelmanagement.model.room.RoomRepository;
import lombok.ToString;

@ToString
public class Price {
    private RoomMapper mapper;
    private RoomRepository roomRepository;

    public int countPrice(int beds){
        // 1 bed - 80PLN
        int price = beds * 80;
        return price;
    }
}
