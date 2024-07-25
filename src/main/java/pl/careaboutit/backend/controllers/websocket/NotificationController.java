package pl.careaboutit.backend.controllers.websocket;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.careaboutit.backend.services.websocket.NotificationService;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping("/{user}")
    public ResponseEntity<String> sendNotification(
            @PathVariable String user,
            @RequestBody String notification) {
        try {
            notificationService.sendNotification(user, notification);
            return ResponseEntity.ok("Notification sent");
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Error sending notification");
        } catch (IllegalArgumentException | IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{user}")
    public ResponseEntity<String> closeConnection(@PathVariable String user) {
        try {
            notificationService.closeConnection(user);
            return ResponseEntity.ok("Connection closed");
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Error closing connection");
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
