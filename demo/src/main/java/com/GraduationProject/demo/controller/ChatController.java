package com.GraduationProject.demo.controller;

import com.GraduationProject.demo.DTO.chatDto;
import com.GraduationProject.demo.service.OpenRouterService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/api/chat")
public class ChatController {

    private final OpenRouterService openRouterService;

    public ChatController(OpenRouterService openRouterService) {
        this.openRouterService = openRouterService;
    }

    @PostMapping
    public Mono<String> chat(@RequestBody String userMessage) {
        return openRouterService.getChatResponse(userMessage);
    }

    @PostMapping("/advanced")
    public Mono<String> advancedChat(@RequestBody chatDto.Message message) {
        return openRouterService.getChatResponse(message.content());
    }
}