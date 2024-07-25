package pl.careaboutit.backend.mapper.impl;

import org.springframework.stereotype.Component;
import pl.careaboutit.backend.dto.SignupUserDto;
import pl.careaboutit.backend.dto.UserResponseDto;
import pl.careaboutit.backend.mapper.UserMapper;
import pl.careaboutit.backend.model.User;

@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User mapSignupUserDtoToUser(SignupUserDto signupUserDto) {
        User user = new User();

        user.setEmail(signupUserDto.email());
        user.setFirstName(signupUserDto.firstName());
        user.setLastName(signupUserDto.lastName());
        user.setPassword(signupUserDto.password());
        user.setPhone(signupUserDto.phone());

        return user;
    }

    @Override
    public UserResponseDto mapUserToUserResponseDto(User user) {
        if (user == null) {
            throw new IllegalArgumentException("Cannot map null User to UserResponseDto");
        }

        return new UserResponseDto(
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                user.getPhone(),
                user.getRole().name()
        );
    }

}
