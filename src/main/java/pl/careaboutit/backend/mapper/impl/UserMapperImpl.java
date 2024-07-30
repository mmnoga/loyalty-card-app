package pl.careaboutit.backend.mapper.impl;

import org.springframework.stereotype.Component;
import pl.careaboutit.backend.dto.user.SignupUserDto;
import pl.careaboutit.backend.dto.user.UserResponseDto;
import pl.careaboutit.backend.mapper.UserMapper;
import pl.careaboutit.backend.model.User;

import java.util.HashSet;
import java.util.Set;

@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User mapSignupUserDtoToUser(SignupUserDto signupUserDto) {
        User user = new User();

        user.setEmail(signupUserDto.email());
        user.setFirstName(signupUserDto.firstName());
        user.setLastName(signupUserDto.lastName());
        user.setPassword(signupUserDto.password());
        signupUserDto.phone().ifPresent(user::setPhone);
        user.setAuthProvider(signupUserDto.authProvider());

        if (signupUserDto.roles() != null && !signupUserDto.roles().isEmpty()) {
            user.setRoles(new HashSet<>(signupUserDto.roles()));
        } else {
            user.setRoles(Set.of("USER"));
        }

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
                user.getAuthProvider(),
                user.getRoles(),
                null
        );
    }

}
