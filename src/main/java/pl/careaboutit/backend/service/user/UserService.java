package pl.careaboutit.backend.service.user;

import pl.careaboutit.backend.dto.SignupUserDto;
import pl.careaboutit.backend.dto.UserResponseDto;

public interface UserService {

    UserResponseDto addUser(SignupUserDto user);

    UserResponseDto findUserByEmail(String email);

}
