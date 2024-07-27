package pl.careaboutit.backend.service.user;

import pl.careaboutit.backend.dto.LoginRequestDto;
import pl.careaboutit.backend.dto.user.SignupUserDto;
import pl.careaboutit.backend.dto.user.UserResponseDto;
import pl.careaboutit.backend.exception.BusinessException;

public interface UserService {

    /**
     * Adds a new user to the system.
     *
     * @param user the data transfer object containing user details.
     * @return a {@link UserResponseDto} representing the added user.
     * @throws BusinessException if a user with the provided email already exists.
     */
    UserResponseDto addUser(SignupUserDto user);

    /**
     * Finds a user by their email address.
     *
     * @param email the email address of the user to find.
     * @return a {@link UserResponseDto} representing the found user.
     * @throws BusinessException if no user with the provided email is found.
     */
    UserResponseDto findUserByEmail(String email);

    UserResponseDto loginUser(LoginRequestDto loginRequest);

}
