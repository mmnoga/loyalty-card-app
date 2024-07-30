package pl.careaboutit.backend.controller.loyalty;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.careaboutit.backend.dto.loyalty.LoyaltyCardResponseDto;
import pl.careaboutit.backend.dto.loyalty.UpdateRequestDto;
import pl.careaboutit.backend.service.loyalty.LoyaltyCardService;

@RestController
@RequestMapping("/card")
@RequiredArgsConstructor
public class LoyaltyCardController {

    private final LoyaltyCardService loyaltyCardService;

    @GetMapping("/by-number/{cardNumber}")
    public LoyaltyCardResponseDto getCardDetails(
            @PathVariable String cardNumber) {
        return loyaltyCardService.getCardDetails(cardNumber);
    }

    @GetMapping("/by-email/{email}")
    public LoyaltyCardResponseDto getCardDetailsByUserEmail(
            @PathVariable String email) {
        return loyaltyCardService.getCardDetailsByUserEmail(email);
    }

    @PostMapping()
    public LoyaltyCardResponseDto generateNewCardForUser(
            @RequestParam String email) {
        return loyaltyCardService.generateNewCardForUser(email);
    }

    @PutMapping()
    public LoyaltyCardResponseDto updateCardPoints(
            @RequestBody UpdateRequestDto updateRequestDto) {
        return loyaltyCardService.updateCardPoints(updateRequestDto);
    }

}
