package pl.careaboutit.backend.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.careaboutit.backend.dtos.MessageDto;

@RestController
public class PrivateController {

    @GetMapping("/messages")
    public ResponseEntity<MessageDto> messages(
            @AuthenticationPrincipal(expression = "name") String name) {
        return ResponseEntity.ok(
                new MessageDto("private content " + name));
    }

}
