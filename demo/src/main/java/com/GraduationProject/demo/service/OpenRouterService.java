package com.GraduationProject.demo.service;

import com.GraduationProject.demo.DTO.chatDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
@Service
public class OpenRouterService {

    private final WebClient.Builder webClientBuilder;

    private final String apiKey;

    private static final String BASE_URL = "https://openrouter.ai/api/v1/chat/completions";

    public OpenRouterService(WebClient.Builder webClientBuilder,
                             @Value("${openrouter.api.key}") String apiKey) {
        this.webClientBuilder = webClientBuilder;
        this.apiKey = apiKey;
    }

    public Mono<String> getChatResponse(String prompt) {
        chatDto.OpenRouterRequest request = new chatDto.OpenRouterRequest(
                "openai/gpt-3.5-turbo",
                List.of(new chatDto.Message("user", prompt))
        );

        return webClientBuilder.build()
                .post()
                .uri(BASE_URL)
                .header("Authorization", "Bearer " + apiKey) // use the key from the environment
                .bodyValue(request)
                .retrieve()
                .onStatus(status -> status.isError(), response -> {
                    return response.bodyToMono(String.class)
                            .flatMap(errorBody -> {
                                System.err.println("OpenRouter error body: " + errorBody);
                                return Mono.error(new RuntimeException(errorBody));
                            });
                })
                .bodyToMono(chatDto.OpenRouterResponse.class)
                .map(response -> response.choices().get(0).message().content())
                .onErrorResume(error -> {
                    System.err.println("Error calling OpenRouter: " + error.getMessage());
                    return Mono.just("Sorry, I'm having trouble responding right now.");
                });
    }
}
