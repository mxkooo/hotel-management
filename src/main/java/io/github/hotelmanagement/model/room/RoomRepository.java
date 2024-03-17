package io.github.hotelmanagement.model.room;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.function.Function;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    @Override
    <S extends Room> S save(S entity);

    @Override
    void deleteAll();

    @Override
    Optional<Room> findById(Long id);

    @Override
    <S extends Room, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction);
}
