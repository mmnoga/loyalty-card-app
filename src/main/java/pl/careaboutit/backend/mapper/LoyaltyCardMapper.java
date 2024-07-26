package pl.careaboutit.backend.mapper;

import pl.careaboutit.backend.dto.loyalty.LoyaltyCardResponseDto;
import pl.careaboutit.backend.model.LoyaltyCard;

public interface LoyaltyCardMapper {

    LoyaltyCardResponseDto mapLoyaltyCardToLoyaltyCardResponseDto(LoyaltyCard savedCard);

}
