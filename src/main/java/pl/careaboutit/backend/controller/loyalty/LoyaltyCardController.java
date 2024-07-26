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

    @GetMapping()
    public LoyaltyCardResponseDto getCardDetails(
            @RequestParam String cardNumber) {
        return loyaltyCardService.getCardDetails(cardNumber);
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
