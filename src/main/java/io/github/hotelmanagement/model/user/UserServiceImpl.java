package io.github.hotelmanagement.model.user;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl {

    private UserRepository userRepository;
    private final static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    public UserDTO createUser(UserDTO userDTO){
        User user = new User();
        user.setId(userDTO.id());
        user.setName(userDTO.name());
        user.setLastName(userDTO.lastName());

        return UserMapper.mapToDTO(userRepository.save(user));
    }
}
