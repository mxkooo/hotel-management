package io.github.hotelmanagement.model.user;

import io.github.hotelmanagement.model.reservation.ReservationMapper;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserMapper userMapper;
    private UserRepository userRepository;
    private ReservationMapper reservationMapper;
    public UserDTO createUser(UserDTO userDTO){
        User user = new User();
        user.setId(userDTO.id());
        user.setName(userDTO.name());
        user.setLastName(userDTO.lastName());

        return UserMapper.mapToDTO(userRepository.save(user));
    }
}
