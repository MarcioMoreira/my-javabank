package io.codeforall.bootcamp.javabank.factories;

import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.beans.factory.FactoryBean;

public class OpenAiChatOptionsFactory implements FactoryBean<OpenAiChatOptions> {

    private String model;
    private Double temperature;

    @Override
    public OpenAiChatOptions getObject() {
        return OpenAiChatOptions.builder().model(model).temperature(temperature).build();
    }

    @Override
    public Class<?> getObjectType() {
        return OpenAiChatOptions.class;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }
}
