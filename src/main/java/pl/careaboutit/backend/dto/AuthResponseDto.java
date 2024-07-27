package pl.careaboutit.backend.dto;

import pl.careaboutit.backend.dto.user.UserResponseDto;

public record AuthResponseDto(

        String token,
        UserResponseDto user

) {
}
