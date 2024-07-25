package pl.careaboutit.backend.controllers.oauth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import pl.careaboutit.backend.dtos.MessageDto;

import java.io.IOException;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class PrivateController {

    private final Map<String, WebSocketSession> sessions;

    @GetMapping("/messages")
    public ResponseEntity<MessageDto> messages(
            @AuthenticationPrincipal(expression = "name") String name) {
        return ResponseEntity.ok(
                new MessageDto("Private content " + name));
    }

}
