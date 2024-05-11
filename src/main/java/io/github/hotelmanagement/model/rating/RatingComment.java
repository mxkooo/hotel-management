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
            try {
                throw new Exception("Too much signs");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return comment;
    }
}
