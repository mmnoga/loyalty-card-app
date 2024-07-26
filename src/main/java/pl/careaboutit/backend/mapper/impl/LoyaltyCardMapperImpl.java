package pl.careaboutit.backend.mapper.impl;

import org.springframework.stereotype.Component;
import pl.careaboutit.backend.dto.loyalty.LoyaltyCardResponseDto;
import pl.careaboutit.backend.mapper.LoyaltyCardMapper;
import pl.careaboutit.backend.model.LoyaltyCard;

@Component
public class LoyaltyCardMapperImpl implements LoyaltyCardMapper {

    @Override
    public LoyaltyCardResponseDto mapLoyaltyCardToLoyaltyCardResponseDto(LoyaltyCard savedCard) {
        return new LoyaltyCardResponseDto(
                savedCard.getCardNumber(),
                savedCard.getStatus().toString(),
                0
        );
    }

}
