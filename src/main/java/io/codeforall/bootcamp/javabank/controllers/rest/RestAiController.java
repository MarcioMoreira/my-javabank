package io.codeforall.bootcamp.javabank.controllers.rest;


import io.codeforall.bootcamp.javabank.converters.GenerationToAnswerDto;
import io.codeforall.bootcamp.javabank.dtos.AnswerDto;
import io.codeforall.bootcamp.javabank.dtos.QuestionDto;
import io.codeforall.bootcamp.javabank.services.AiService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 * A REST API AI Controller responsible for rendering AI responses
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/ai")
public class RestAiController {

    private AiService aiService;
    private GenerationToAnswerDto generationToAnswerDto;


    /**
     * Handles POST requests to the "/info" endpoint.
     * This method processes a question sent in the request body and returns a corresponding answer.
     * It validates the input {@link QuestionDto}, and if validation errors are found, it responds with
     * a {@code 400 BAD REQUEST} status. If the input is valid, the result is converted into an {@link AnswerDto}.
     *
     * @param questionDto    the {@link QuestionDto} containing the question to be processed.
     * @param bindingResult  the {@link BindingResult} used to check for validation errors in the {@code questionDto}.
     * @return a {@link ResponseEntity} containing:
     *         - An {@link AnswerDto} with the processed answer and a {@code 200 OK} status if successful.
     *         - A {@code 400 BAD REQUEST} status if validation errors are present.
     */
    @RequestMapping(method = RequestMethod.POST, path = {"/info"})
    public ResponseEntity<AnswerDto> info(@Valid @RequestBody QuestionDto questionDto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(generationToAnswerDto.convert(aiService.info(questionDto.getQuestion())), HttpStatus.OK);
    }

    /**
     * Set the AI service
     * @param aiService to set
     */
    @Autowired
    public void setAiService(AiService aiService) {
        this.aiService = aiService;
    }

    /**
     * Set the GenerationToAnswerDto converter
     * @param generationToAnswerDto to set
     */
    @Autowired
    public void setGenerationToAnswerDto(GenerationToAnswerDto generationToAnswerDto) {
        this.generationToAnswerDto = generationToAnswerDto;
    }
}
