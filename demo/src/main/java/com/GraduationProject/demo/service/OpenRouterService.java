package com.GraduationProject.demo.service;

import com.GraduationProject.demo.DTO.chatDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class OpenRouterService {

    private final WebClient webClient;

    private static final String API_KEY = "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";
    private static final String API_URL = "https://openrouter.ai/api/v1/chat/completions";

    public OpenRouterService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder
                .baseUrl(API_URL)
                .defaultHeader("Authorization", "Bearer " + API_KEY)
                .defaultHeader("HTTP-Referer", "http://localhost:8080")
                .defaultHeader("X-Title", "My Spring Boot Chat")
                .build();
    }

    public Mono<String> getChatResponse(String prompt) {
        chatDto.OpenRouterRequest request = new chatDto.OpenRouterRequest(
                "openai/gpt-3.5-turbo",
                List.of(new chatDto.Message("user", prompt))
        );

        return webClient.post()
                .bodyValue(request)
                .retrieve()
                .onStatus(status -> status.isError(), response -> {
                    return response.bodyToMono(String.class)
                            .flatMap(errorBody -> Mono.error(new RuntimeException(
                                    "OpenRouter API error: " + response.statusCode() + " - " + errorBody
                            )));
                })
                .bodyToMono(chatDto.OpenRouterResponse.class)
                .map(response -> response.choices().get(0).message().content())
                .onErrorResume(e -> {
                    System.err.println("Error calling OpenRouter: " + e.getMessage());
                    return Mono.just("Sorry, I'm having trouble responding right now.");
                });
    }
}
