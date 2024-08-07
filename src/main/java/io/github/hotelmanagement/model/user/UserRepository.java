package io.github.hotelmanagement.model.user;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Override
    boolean existsById(Long aLong);

    @Override
    Optional<User> findById(Long aLong);

    @Override
    <S extends User> S save(S entity);

    @Override
    <S extends User> boolean exists(Example<S> example);

    @Override
    void deleteById(Long aLong);
}
