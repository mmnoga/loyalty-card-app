package pl.careaboutit.backend.service.user;

import pl.careaboutit.backend.dto.user.SignupUserDto;
import pl.careaboutit.backend.dto.user.UserResponseDto;

public interface UserService {

    UserResponseDto addUser(SignupUserDto user);

    UserResponseDto findUserByEmail(String email);

}
