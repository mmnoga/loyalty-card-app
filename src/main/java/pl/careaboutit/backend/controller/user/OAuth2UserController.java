package pl.careaboutit.backend.controller.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;
import pl.careaboutit.backend.dto.AuthResponseDto;
import pl.careaboutit.backend.dto.oauth.TokenDto;
import pl.careaboutit.backend.dto.user.UserResponseDto;
import pl.careaboutit.backend.service.user.AuthService;
import pl.careaboutit.backend.service.user.OAuth2UserService;

@RestController
@RequestMapping("/oauth")
@RequiredArgsConstructor
public class OAuth2UserController {

    private final OAuth2UserService userService;
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<UserResponseDto> signUp(
            @AuthenticationPrincipal OAuth2User oAuth2User) {
        return ResponseEntity.ok(userService.signUpUser(oAuth2User));
    }

    @GetMapping("/user")
    public ResponseEntity<UserResponseDto> geUser(
            @AuthenticationPrincipal OAuth2User oAuth2User) {
        return ResponseEntity.ok(userService.getUser(oAuth2User));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(
            @RequestBody TokenDto tokenDto) {
        return ResponseEntity.ok(authService.login(tokenDto.token()));
    }

}
