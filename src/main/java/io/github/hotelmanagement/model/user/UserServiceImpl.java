package io.github.hotelmanagement.model.user;

import io.github.hotelmanagement.model.exception.NotFoundException;
import io.github.hotelmanagement.model.reservation.Reservation;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;

    public UserDTO createUser(UserDTO userDTO){
        User user = new User();
        user.setName(userDTO.name());
        user.setLastName(userDTO.lastName());
        return UserMapper.mapToDTO(userRepository.save(user));
    }
    public User getUser(Long userId){
        return userRepository.findById(userId).orElseThrow(() -> new NotFoundException("user not found"));
    }
    public boolean isWasUserGuest(List<Reservation> reservation, User user){
        return reservation
                .stream()
                .anyMatch(r -> r.getUser().equals(user));
    }

}
