package io.github.hotelmanagement.model.rating;

import io.github.hotelmanagement.model.room.Room;
import io.github.hotelmanagement.model.user.User;
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
    private RatingStars ratingStars;
    private String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
