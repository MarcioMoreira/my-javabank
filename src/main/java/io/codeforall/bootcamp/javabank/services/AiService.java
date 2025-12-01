package io.codeforall.bootcamp.javabank.services;

import org.springframework.ai.chat.model.Generation;

/**
 * Common interface for an AI service which interacts with a chat client
 * to generate AI-driven responses, based on given prompts.
 */
public interface AiService {

    /**
     * Generates a joke using the AI chat client.
     * @return a {@link Generation} object containing the AI-generated joke.
     */
    public Generation joke();
}
