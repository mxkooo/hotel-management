package io.github.hotelmanagement.model.rating;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class RatingStars {

    private final static int MIN_STAR_VALUE = 1;
    private final static int MAX_STAR_VALUE = 5;

    private int stars;

    protected RatingStars(){
    }

    public RatingStars(Integer stars) {
        this.stars = checkStarValue(stars);
    }

    private int checkStarValue(Integer starValue){
        if(starValue == null ){
            throw new RuntimeException("Rating star can not be null!");
        }
        if(!isInRange(starValue)){
            throw new RuntimeException("Rating must have to be in range 1-5!");
        }
        return starValue;
    }

    private boolean isInRange(int starValue){
        return MAX_STAR_VALUE >= starValue && MIN_STAR_VALUE <= starValue;
    }
}
