package io.github.hotelmanagement.model.user;


import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(RoutesUser.ROOT)
@AllArgsConstructor
public class UserController {
    private UserService userService;

    @PostMapping(RoutesUser.POST)
    public UserDTO createUser(@RequestBody @Validated UserDTO userDTO){
        return userService.createUser(userDTO);
    }
    @DeleteMapping(RoutesUser.DELETE + "/{userId}")
    public void deleteById(@PathVariable Long userId) throws Exception{
        userService.deleteById(userId);
    }
}
