package pl.careaboutit.backend.controller.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.careaboutit.backend.dto.LoginRequestDto;
import pl.careaboutit.backend.dto.user.SignupUserDto;
import pl.careaboutit.backend.dto.user.UserResponseDto;
import pl.careaboutit.backend.service.user.UserService;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> createUser(@RequestBody SignupUserDto userDto) {
        return ResponseEntity.ok(userService.addUser(userDto));
    }

    @GetMapping()
    public ResponseEntity<UserResponseDto> findUser(@RequestParam String email) {
        return ResponseEntity.ok(userService.findUserByEmail(email));
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponseDto> loginUser(@RequestBody LoginRequestDto loginRequest) {
        return ResponseEntity.ok(userService.loginUser(loginRequest));
    }

}
