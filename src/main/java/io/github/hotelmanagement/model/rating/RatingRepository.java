package io.github.hotelmanagement.model.rating;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<RatingRoom, Long> {
    @Override
    <S extends RatingRoom> S save(S entity);
}
