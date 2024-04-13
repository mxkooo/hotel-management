package io.github.hotelmanagement.model.rating;

import jakarta.persistence.*;
import lombok.*;

import org.springframework.lang.NonNull;


@Table(name = "RATING")
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RatingRoom {

    private Long userId;
    @NonNull
    private int stars;
    private String comment;

}