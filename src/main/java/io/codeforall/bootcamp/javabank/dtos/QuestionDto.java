package io.codeforall.bootcamp.javabank.dtos;

import jakarta.validation.constraints.NotNull;

/**
 * Data Transfer Object (DTO) representing a question.
 * This class is used to encapsulate data related to a question, ensuring that
 * the question field is always provided and not null through validation annotations.
 */
public class QuestionDto {

    @NotNull(message = "question is mandatory")
    private String question;

    /**
     * Get the question
     * @return the question, which must not be null.
     */
    public @NotNull(message = "question is mandatory") String getQuestion() {
        return question;
    }

    /**
     * Set the question
     * @param question the question to set, which must not be null in order to enforce data integrity.
     */
    public void setQuestion(@NotNull(message = "question is mandatory") String question) {
        this.question = question;
    }
}

