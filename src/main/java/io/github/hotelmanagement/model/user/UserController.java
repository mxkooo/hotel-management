package io.github.hotelmanagement.model.user;


import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(RoutesUser.ROOT)
@AllArgsConstructor
public class UserController {
    private UserServiceImpl userService;

    @PostMapping(RoutesUser.POST)
    public UserDTO createUser(@RequestBody @Validated UserDTO userDTO){
        return userService.createUser(userDTO);
    }
    @GetMapping(RoutesUser.GET + "/all")
    public List<UserDTO> getAllUsers(){
        return userService.getAllUsers();
    }
    @GetMapping(RoutesUser.GET + "/{userId}")
    public UserDTO findById(@PathVariable Long userId){
        return userService.findUserById(userId);
    }

}
