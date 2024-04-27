package io.github.hotelmanagement.model.user;


import io.github.hotelmanagement.model.reservation.Reservation;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserDTO createUser(UserDTO userDTO);
    User getUser(Long userId);
    boolean isWasUserGuest(List<Reservation> reservations, User user) throws Exception;
    List<UserDTO> getAllUsers();
    Optional<UserDTO> findById(Long userId);
}
