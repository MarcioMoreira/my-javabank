package io.codeforall.bootcamp.javabank.services;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.Generation;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AiServiceImpl implements AiService {

    private ChatModel chatModel;

    @Autowired
    public void setChatModel(ChatModel chatModel) {
        this.chatModel = chatModel;
    }

    @Override
    public Generation joke() {
        Prompt prompt = new Prompt("Tell me a funny joke!");
        return chatModel.call(prompt).getResult();
    }
}
