package io.codeforall.bootcamp.javabank.controllers.rest;

import io.codeforall.bootcamp.javabank.services.AiService;
import org.springframework.ai.chat.model.Generation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * A REST API AI Controller responsible for rendering jokes
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/ai")
public class RestAiController {

    private AiService aiService;

    /**
     * Handles HTTP POST requests
     * @return a {@link ResponseEntity} containing the generated joke of type {@link Generation}
     * and an HTTP status of {@code OK}.
     */
    @RequestMapping(method = RequestMethod.POST, path={"/", ""})
    public ResponseEntity<Generation> joke() {
        return new ResponseEntity<>(aiService.joke(), HttpStatus.OK);
    }

    /**
     * Set the AI service
     * @param aiService to set
     */
    @Autowired
    public void setAiService(AiService aiService) {
        this.aiService = aiService;
    }
}
