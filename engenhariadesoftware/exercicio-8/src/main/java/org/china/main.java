package org.china;

import java.io.IOException;

import io.github.ollama4j.OllamaAPI;
import io.github.ollama4j.exceptions.OllamaBaseException;
import io.github.ollama4j.models.response.OllamaResult;

public class main {

    public static void main(String[] args) throws OllamaBaseException, IOException, InterruptedException {
        String host = "http://127.0.0.1:11434/";
        String modelName = "qwen3:1.7b";

        OllamaAPI ollamaAPI = new OllamaAPI(host);
        ollamaAPI.setRequestTimeoutSeconds(60);

        OllamaResult result =
                ollamaAPI.generate(modelName, "you are china?", null);

        System.out.println(result.getResponse());
    }
}