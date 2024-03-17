package io.github.hotelmanagement.model.room;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.lang.NonNull;

@Table(name = "ROOM")
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NonNull
    private double pricePerNight;
    @NonNull
    private int bedAmount;
    private int maxPeopleInside;

}
