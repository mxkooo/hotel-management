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

    @PutMapping(RoutesUser.UPDATE + "/{userId}")
    public UserDTO updateUser(@PathVariable Long userId, @RequestBody @Validated UserDTO toUpdate){
        return userService.updateUser(userId, toUpdate);
    }

}
