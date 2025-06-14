package com.GraduationProject.demo.controller;

import com.GraduationProject.demo.model.NotificationModel;
import com.GraduationProject.demo.service.NotificationService;
import com.google.firebase.messaging.FirebaseMessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notify")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping("/token")
    public ResponseEntity<String> notifyUser(@RequestBody NotificationModel request)
            throws FirebaseMessagingException {
        String response = notificationService.sendToToken(request);
        return ResponseEntity.ok("Send to user : " + response);
    }

    @PostMapping("/topic")
    public ResponseEntity<String> notifyTopic(@RequestBody NotificationModel request)
            throws FirebaseMessagingException {
        String response = notificationService.sendToTopic(request);
        return ResponseEntity.ok("Send : " + response);
    }

    @PostMapping("/all")
    public ResponseEntity<String> notifyAll(@RequestBody NotificationModel request)
            throws FirebaseMessagingException {
        String response = notificationService.sendToAll(request);
        return ResponseEntity.ok("Send to every one : " + response);
    }
}
