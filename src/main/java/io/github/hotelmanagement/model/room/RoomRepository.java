package io.github.hotelmanagement.model.room;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, Long> {
    List<Room> findRoomsByBedAmount(int bedAmount);
    @Override
    <S extends Room> S save(S entity);
    @Override
    void deleteById(Long aLong);

    @Override
    boolean existsById(Long aLong);

    @Override
    void delete(Room entity);

    @Override
    List<Room> findAll();

    @Override
    Optional<Room> findById(Long aLong);
}
