package pl.careaboutit.backend.mapper;

import pl.careaboutit.backend.dto.user.SignupUserDto;
import pl.careaboutit.backend.dto.user.UserResponseDto;
import pl.careaboutit.backend.model.User;

public interface UserMapper {

    User mapSignupUserDtoToUser(SignupUserDto signupUserDto);

    UserResponseDto mapUserToUserResponseDto(User user);

}
