package io.github.hotelmanagement.model.rating;

import io.github.hotelmanagement.model.room.Room;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;
}
