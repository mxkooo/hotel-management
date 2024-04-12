package io.github.hotelmanagement.model.rating;

import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import org.springframework.lang.NonNull;


@Table(name = "RATING")
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RatingRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Min(1)
    @Max(5)
    private int stars;

    private String comment;

}
