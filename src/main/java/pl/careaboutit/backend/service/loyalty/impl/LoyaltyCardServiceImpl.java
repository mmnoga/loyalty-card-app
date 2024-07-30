package pl.careaboutit.backend.service.loyalty.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import pl.careaboutit.backend.dto.loyalty.LoyaltyCardResponseDto;
import pl.careaboutit.backend.dto.loyalty.PointOperation;
import pl.careaboutit.backend.dto.loyalty.UpdateRequestDto;
import pl.careaboutit.backend.exception.BusinessException;
import pl.careaboutit.backend.mapper.LoyaltyCardMapper;
import pl.careaboutit.backend.model.CardStatus;
import pl.careaboutit.backend.model.LoyaltyCard;
import pl.careaboutit.backend.model.LoyaltyPoint;
import pl.careaboutit.backend.model.User;
import pl.careaboutit.backend.repository.LoyaltyCardRepository;
import pl.careaboutit.backend.repository.LoyaltyPointRepository;
import pl.careaboutit.backend.repository.UserRepository;
import pl.careaboutit.backend.service.loyalty.LoyaltyCardService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LoyaltyCardServiceImpl implements LoyaltyCardService {

    private final LoyaltyCardRepository loyaltyCardRepository;
    private final LoyaltyPointRepository loyaltyPointRepository;
    private final UserRepository userRepository;
    private final LoyaltyCardMapper loyaltyCardMapper;

    @Override
    public LoyaltyCardResponseDto getCardDetails(String cardNumber) {
        LoyaltyCard card =
                loyaltyCardRepository.findByCardNumber(cardNumber)
                        .orElseThrow(() -> new BusinessException(
                "Card " + cardNumber + " not found",
                HttpStatus.BAD_REQUEST
        ));

        List<LoyaltyPoint> points = loyaltyPointRepository.findByCard_Id(card.getId());

        int totalPoints = points.stream()
                .mapToInt(LoyaltyPoint::getPoints)
                .sum();

        return new LoyaltyCardResponseDto(
                card.getCardNumber(),
                card.getStatus().toString(),
                totalPoints
        );
    }

    @Override
    public LoyaltyCardResponseDto generateNewCardForUser(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new BusinessException(
                        "User with email: " + email + " not found",
                        HttpStatus.BAD_REQUEST
                ));

        LoyaltyCard newCard = new LoyaltyCard();
        newCard.setCardNumber(UUID.randomUUID().toString());
        newCard.setUser(user);
        newCard.setStatus(CardStatus.ACTIVE);

        LoyaltyCard savedCard = loyaltyCardRepository.save(newCard);

        return loyaltyCardMapper.mapLoyaltyCardToLoyaltyCardResponseDto(savedCard);
    }

    @Override
    public LoyaltyCardResponseDto updateCardPoints(UpdateRequestDto updateRequestDto) {
        LoyaltyCard cardToUpdate =
                loyaltyCardRepository.findByCardNumber(updateRequestDto.cardNumber())
                        .orElseThrow(() -> new BusinessException(
                                "Loyalty card with " + updateRequestDto.cardNumber() + " not found",
                                HttpStatus.BAD_REQUEST
                        ));

        PointOperation operation = updateRequestDto.operation();

        int pointsToChange = updateRequestDto.points();

        Optional<LoyaltyPoint> existingPoints = loyaltyPointRepository.findByCard(cardToUpdate);

        LoyaltyPoint loyaltyPoint;
        if (existingPoints.isPresent()) {
            loyaltyPoint = existingPoints.get();
            int currentPoints = loyaltyPoint.getPoints();

            int newPoints = operation ==
                    PointOperation.ADD ? currentPoints + pointsToChange : currentPoints - pointsToChange;

            loyaltyPoint.setPoints(newPoints);
            loyaltyPoint.setUpdatedAt(LocalDateTime.now());

            loyaltyPointRepository.save(loyaltyPoint);
        } else {
            loyaltyPoint = new LoyaltyPoint();
            loyaltyPoint.setCard(cardToUpdate);
            loyaltyPoint.setPoints(operation == PointOperation.ADD ? pointsToChange : -pointsToChange);
            loyaltyPoint.setCreatedAt(LocalDateTime.now());
            loyaltyPoint.setUpdatedAt(LocalDateTime.now());

            loyaltyPointRepository.save(loyaltyPoint);
        }

        return new LoyaltyCardResponseDto(
                cardToUpdate.getCardNumber(),
                cardToUpdate.getStatus().name(),
                loyaltyPoint.getPoints()
        );
    }

}
