package pl.careaboutit.backend.dto.loyalty;

import pl.careaboutit.backend.model.User;

public record NewLoyaltyCardRequestDto(

        User user

) {
}
