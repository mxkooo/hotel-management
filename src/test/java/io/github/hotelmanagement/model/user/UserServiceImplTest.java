package io.github.hotelmanagement.model.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserServiceImplTest {
    private UserRepository userRepository;
    private UserService userService;

    @BeforeEach
    void prepare() {
        userRepository = mock(UserRepository.class);
        userService = new UserServiceImpl(userRepository);
    }

    @Test
    void createUser() {
        //given
        UserDTO userDTO = new UserDTO(1L, "Jan", "Nowak", null);

        User saved = new User();
        saved.setId(1L);
        saved.setName("Jan");
        saved.setLastName("Nowak");
        saved.setReservations(null);

        //when
        when(userRepository.save(any(User.class))).thenReturn(saved);

        UserDTO createdDTO = userService.createUser(userDTO);

        assertEquals(userDTO.name(), createdDTO.name());
        assertEquals(userDTO.lastName(), createdDTO.lastName());

        //then
        verify(userRepository, times(1)).save(any(User.class));

    }



}