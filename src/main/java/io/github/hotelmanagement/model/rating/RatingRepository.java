package io.github.hotelmanagement.model.rating;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RatingRepository extends JpaRepository<RatingRoom, Long> {
    @Override
    <S extends RatingRoom> S save(S entity);

    @Override
    void deleteById(Long aLong);

    @Override
    boolean existsById(Long aLong);

    @Override
    Optional<RatingRoom> findById(Long aLong);
    Optional<RatingRoom> findByIdAndUserId(Long rateId, Long userId);
}
