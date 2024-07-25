package pl.careaboutit.backend.services.websocket.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import pl.careaboutit.backend.services.websocket.NotificationService;

import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationServiceImpl implements NotificationService {

    private final Map<String, WebSocketSession> sessions;

    @Override
    public void sendNotification(String user, String notification) throws IOException {
        if (notification == null) {
            log.error("Notification is null");
            throw new IllegalArgumentException("Notification should not be null");
        }

        var session = sessions.get(user);

        if (session == null) {
            log.error("User {} is not connected", user);
            throw new IllegalStateException(user + " is not connected");
        }

        log.info("Sending notification to user {}", user);
        session.sendMessage(new TextMessage(notification));
    }

    @Override
    public void closeConnection(String user) throws IOException, IllegalStateException {
        WebSocketSession session = sessions.get(user);

        if (session == null) {
            log.error("User {} is not connected", user);
            throw new IllegalStateException(user + " is not connected");
        }

        try {
            session.close(CloseStatus.NORMAL);
            sessions.remove(user);
            log.info("Connection closed for user {}", user);
        } catch (IOException e) {
            log.error("Error closing connection for user {}: {}", user, e.getMessage());
            throw e;
        }
    }

}
