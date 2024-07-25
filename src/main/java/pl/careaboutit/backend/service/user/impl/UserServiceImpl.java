package pl.careaboutit.backend.service.user.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.careaboutit.backend.dto.SignupUserDto;
import pl.careaboutit.backend.dto.UserResponseDto;
import pl.careaboutit.backend.exception.BusinessException;
import pl.careaboutit.backend.mapper.UserMapper;
import pl.careaboutit.backend.model.Role;
import pl.careaboutit.backend.model.User;
import pl.careaboutit.backend.repository.UserRepository;
import pl.careaboutit.backend.service.user.UserService;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

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

        newUser.setRole(Role.USER);

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

}
