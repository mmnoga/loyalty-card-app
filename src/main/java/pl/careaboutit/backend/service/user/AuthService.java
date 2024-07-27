package pl.careaboutit.backend.service.user;

import pl.careaboutit.backend.dto.AuthResponseDto;

public interface AuthService {

    AuthResponseDto login(String token);

}
