package io.github.hotelmanagement.model.room;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Integer> {
    List<Room> findRoomsByBedAmount(int bedAmount);

    @Override
    <S extends Room> S save(S entity);
}
