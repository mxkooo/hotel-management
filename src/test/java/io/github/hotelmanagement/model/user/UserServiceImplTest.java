package io.github.hotelmanagement.model.user;

import io.github.hotelmanagement.model.exception.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
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
        UserDTO userDTO = new UserDTO(1L, "Jan", "Nowak", new ArrayList<>(), new ArrayList<>());

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
    @Test
    void getUser() {
        //given
        User user = new User(1L, "Cristiano", "Ronaldo",new ArrayList<>(), new ArrayList<>());
        //when
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        User calledUser = userService.getUser(user.getId());
        //then
        assertNotNull(calledUser);
        assertEquals(user.getId(), calledUser.getId());
        assertEquals(user.getName(), calledUser.getName());
        assertEquals(user.getLastName(), calledUser.getLastName());
        verify(userRepository).findById(user.getId());
    }

    @Test
    void getUser_WhenUserDoesNotExist() {
        //given
        Long id = 1L;
        //when
        when(userRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> userService.getUser(id));
        //then
        verify(userRepository).findById(id);
    }



}