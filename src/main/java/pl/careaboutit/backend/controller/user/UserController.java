package pl.careaboutit.backend.controller.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.careaboutit.backend.dto.LoginRequestDto;
import pl.careaboutit.backend.dto.MessageResponseDto;
import pl.careaboutit.backend.dto.user.SignupUserDto;
import pl.careaboutit.backend.dto.user.UserResponseDto;
import pl.careaboutit.backend.service.token.SessionTokenService;
import pl.careaboutit.backend.service.user.UserService;

import java.util.Map;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final SessionTokenService sessionTokenService;

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

    @DeleteMapping("/logout")
    public ResponseEntity<MessageResponseDto> logoutUser(@RequestBody Map<String, String> requestBody) {
        String token = requestBody.get("token");
        return ResponseEntity.ok(sessionTokenService.deleteToken(token));
    }

}
