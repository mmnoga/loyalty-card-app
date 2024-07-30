package pl.careaboutit.backend.service.loyalty;

import pl.careaboutit.backend.dto.loyalty.NewLoyaltyCardRequestDto;
import pl.careaboutit.backend.dto.loyalty.LoyaltyCardResponseDto;
import pl.careaboutit.backend.dto.loyalty.UpdateRequestDto;

public interface LoyaltyCardService {

    LoyaltyCardResponseDto getCardDetails(String cardNumber);

    LoyaltyCardResponseDto generateNewCardForUser(String email);

    LoyaltyCardResponseDto updateCardPoints(UpdateRequestDto updateRequestDto);

    LoyaltyCardResponseDto getCardDetailsByUserEmail(String userEmail);

}
