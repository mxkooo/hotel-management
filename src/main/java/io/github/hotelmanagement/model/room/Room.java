package io.github.hotelmanagement.model.room;

import io.github.hotelmanagement.model.rating.RatingRoom;
import io.github.hotelmanagement.model.reservation.Reservation;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.lang.NonNull;

import java.util.ArrayList;
import java.util.List;

@Table(name = "ROOM")
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private double pricePerNight;
    @NonNull
    private int bedAmount;
    private int maxPeopleInside;
    @NonNull
    boolean isReserved;
    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private List<Reservation> reservations = new ArrayList<>();

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private RatingRoom ratingRoom;
}
