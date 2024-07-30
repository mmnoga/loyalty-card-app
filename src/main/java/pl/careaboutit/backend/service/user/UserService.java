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

    /**
     * Authenticates a user based on the provided login request and generates a JWT token upon successful login.
     * <p>
     * This method checks if the user with the provided email exists and if the provided password matches the stored password.
     * If authentication is successful, it generates a JWT token and returns a {@link UserResponseDto} containing user details and the token.
     * </p>
     *
     * @param loginRequest the login request containing email and password.
     * @return a {@link UserResponseDto} containing the user details and the generated JWT token.
     * @throws BusinessException if the user with the given email is not found or if the password is invalid.
     */
    UserResponseDto loginUser(LoginRequestDto loginRequest);

    /**
     * Checks whether a user with the given email address exists in the repository.
     * <p>
     * This method queries the user repository to check if there is a user with the specified email.
     * If no user is found with the given email, a {@link BusinessException} is thrown.
     * </p>
     *
     * @param email the email address to check for existence.
     * @return {@code true} if a user with the specified email exists; {@code false} otherwise.
     * @throws BusinessException if no user with the given email is found.
     */
    boolean existsByEmail(String email);

}
