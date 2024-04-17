package io.github.hotelmanagement.model.rating;

import io.github.hotelmanagement.model.room.Room;
import jakarta.persistence.*;
import lombok.*;

import org.springframework.lang.NonNull;


@Table(name = "RATING")
@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RatingRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private int stars;
    private String comment;

    @ManyToOne
    private Room room = new Room();
}
