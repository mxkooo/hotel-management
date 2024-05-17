package io.github.hotelmanagement.model.rating;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class RatingEditCounter {
    private final static int MAX_EDIT_RATE_AMOUNT = 2;
    private int currentEditRateAmount = 0;

    protected RatingEditCounter(){}

    public RatingEditCounter(Integer currentEditRateAmount) {
        this.currentEditRateAmount = validate(currentEditRateAmount);
    }

    private int validate(Integer currentEditRateAmount){
        if (currentEditRateAmount > MAX_EDIT_RATE_AMOUNT){
            throw new IllegalArgumentException("You can rate only 2 times");
        }
        currentEditRateAmount =+ 1;
        return currentEditRateAmount;
    }

}
