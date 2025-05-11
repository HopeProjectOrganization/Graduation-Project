package com.GraduationProject.demo.DTO;
import java.util.List;
public record chatDto() {


    public record OpenRouterRequest(
            String model,
            List<Message> messages,
            Double temperature,
            Integer max_tokens
    ) {
        public OpenRouterRequest(String model, List<Message> messages) {
            this(model, messages, 0.7, 500);
        }
    }

    public record Message(
            String role, // "user", "system", "assistant"
            String content
    ) {}

    public record OpenRouterResponse(
            List<Choice> choices
    ) {
    }

    public  record Choice(
            Message message
    ) {}

}