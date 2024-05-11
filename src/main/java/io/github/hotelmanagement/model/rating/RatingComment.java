package io.github.hotelmanagement.model.rating;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class RatingComment {
    private String comment;
    protected RatingComment(){}

    public RatingComment(String comment){
        this.comment = validate(comment);
    }

    private String validate(String comment){
        if (comment.length() > 250){
            throw new IllegalArgumentException("Too much signs");
        }
        return comment;
    }
}
