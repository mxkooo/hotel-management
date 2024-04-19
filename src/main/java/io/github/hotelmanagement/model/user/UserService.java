package io.github.hotelmanagement.model.user;


public interface UserService {
    UserDTO createUser(UserDTO userDTO);
    User getUser(Long userId);
    boolean wasUserGuest(Long roomId, User user) throws Exception;
}
