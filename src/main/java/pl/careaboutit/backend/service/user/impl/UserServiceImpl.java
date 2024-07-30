package pl.careaboutit.backend.service.user.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.careaboutit.backend.auth.JwtTokenProvider;
import pl.careaboutit.backend.dto.LoginRequestDto;
import pl.careaboutit.backend.dto.user.SignupUserDto;
import pl.careaboutit.backend.dto.user.UserResponseDto;
import pl.careaboutit.backend.exception.BusinessException;
import pl.careaboutit.backend.mapper.UserMapper;
import pl.careaboutit.backend.model.AuthProvider;
import pl.careaboutit.backend.model.User;
import pl.careaboutit.backend.repository.UserRepository;
import pl.careaboutit.backend.service.user.UserService;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public UserResponseDto addUser(SignupUserDto userDto) {
        if (userRepository.findByEmail(userDto.email()).isPresent()) {
            throw new BusinessException(
                    "User with email: " + userDto.email() + " already exists",
                    HttpStatus.BAD_REQUEST
            );
        }
        User newUser = userMapper.mapSignupUserDtoToUser(userDto);

        String encodedPassword = passwordEncoder.encode(newUser.getPassword());
        newUser.setPassword(encodedPassword);
        newUser.setRoles(Set.of("USER"));
        newUser.setAuthProvider(AuthProvider.LOCAL);

        User savedUser = userRepository.save(newUser);

        return userMapper.mapUserToUserResponseDto(savedUser);
    }

    @Override
    public UserResponseDto findUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new BusinessException(
                        "User with email: " + email + " not found",
                        HttpStatus.BAD_REQUEST
                ));

        return userMapper.mapUserToUserResponseDto(user);
    }

    @Override
    public UserResponseDto loginUser(LoginRequestDto loginRequest) {
        User user = userRepository.findByEmail(loginRequest.email())
                .orElseThrow(() -> new BusinessException(
                        "User with email: " + loginRequest.email() + " not found",
                        HttpStatus.BAD_REQUEST
                ));

        if (!passwordEncoder.matches(loginRequest.password(), user.getPassword())) {
            throw new BusinessException("Invalid password", HttpStatus.UNAUTHORIZED);
        }

        String token = jwtTokenProvider.generateToken(user.getEmail());

        UserResponseDto userResponseDto = userMapper.mapUserToUserResponseDto(user);

        return new UserResponseDto(
                userResponseDto.email(),
                userResponseDto.firstName(),
                userResponseDto.lastName(),
                userResponseDto.authProvider(),
                userResponseDto.roles(),
                token
        );
    }

    @Override
    public boolean existsByEmail(String email) {
        userRepository.findByEmail(email)
                .orElseThrow(() -> new BusinessException(
                        "User with email: " + email + " not found",
                        HttpStatus.BAD_REQUEST
                ));

        return true;
    }

}
