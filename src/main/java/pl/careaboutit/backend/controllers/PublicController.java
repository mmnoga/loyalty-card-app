package pl.careaboutit.backend.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.careaboutit.backend.dtos.MessageDto;

@RestController
public class PublicController {

    @GetMapping("/public/messages")
    public ResponseEntity<MessageDto> publicMessages() {
        return ResponseEntity.ok(
                new MessageDto("Public content"));
    }

}
