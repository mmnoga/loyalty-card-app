package pl.careaboutit.backend.controller.user;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.careaboutit.backend.dto.user.SignupUserDto;
import pl.careaboutit.backend.dto.user.UserResponseDto;
import pl.careaboutit.backend.service.user.UserService;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public UserResponseDto createUser(@RequestBody SignupUserDto userDto) {
        return userService.addUser(userDto);
    }

    @GetMapping()
    public UserResponseDto findUser(@RequestParam String email) {
        return userService.findUserByEmail(email);
    }

}
