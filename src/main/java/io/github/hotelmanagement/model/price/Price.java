package io.github.hotelmanagement.model.price;


public class Price {

    public static final int BED_PRICE = 80;
    public static int countPrice(int beds){
        int price = beds * BED_PRICE;
        return price;
    }
}
