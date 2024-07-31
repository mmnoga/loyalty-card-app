package pl.careaboutit.backend.controller.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import pl.careaboutit.backend.dto.websocket.ChatMessage;

@Controller
@Slf4j
public class WebSocketController {

    @MessageMapping("/chat/{roomId}")
    @SendTo("/topic/{roomId}")
    public ChatMessage chat(
            @DestinationVariable String roomId,
            ChatMessage message) {
        log.info(message.toString());

        return new ChatMessage(
                message.getMessage(),
                message.getUser(),
                message.getIdUser());
    }

}