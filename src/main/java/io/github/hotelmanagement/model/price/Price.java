package io.github.hotelmanagement.model.price;

import lombok.ToString;

@ToString
public class Price {
    public int countPrice(int beds){
        // 1 bed - 80PLN
        int price = beds * 80;
        return price;
    }
}
