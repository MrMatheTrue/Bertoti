package org.china;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

/**
 * Simple JavaFX chat UI that talks to a local Ollama server (http://localhost:11434)
 * using the /api/generate endpoint with streaming.
 */
public class App extends Application {

    private final TextArea chatArea = new TextArea();
    private final TextField promptField = new TextField();
    private final ComboBox<String> modelBox = new ComboBox<>();
    private final TextField hostField = new TextField("http://127.0.0.1:11434");
    private final Button sendBtn = new Button("Enviar");
    private final Button stopBtn = new Button("Parar");
    private final ExecutorService ioPool = Executors.newSingleThreadExecutor();
    private volatile OllamaClient.RunningCall runningCall;

    @Override
    public void start(Stage stage) {
        stage.setTitle("Ollama ChatFX");

        chatArea.setEditable(false);
        chatArea.setWrapText(true);

        modelBox.getItems().addAll("llama3.2:1b");
        modelBox.getSelectionModel().selectFirst();

        promptField.setPromptText("Digite sua pergunta e pressione Enter...");
        promptField.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER && !e.isShiftDown()) {
                e.consume();
                sendMessage();
            }
        });

        sendBtn.setDefaultButton(true);
        sendBtn.setOnAction(e -> sendMessage());

        stopBtn.setOnAction(e -> {
            if (runningCall != null) {
                runningCall.cancel();
            }
        });

        HBox topBar = new HBox(8,
                new Label("Modelo:"), modelBox,
                new Label("Host:"), hostField,
                stopBtn
        );
        topBar.setPadding(new Insets(10));

        HBox bottomBar = new HBox(8, promptField, sendBtn);
        bottomBar.setPadding(new Insets(10));
        HBox.setHgrow(promptField, Priority.ALWAYS);

        BorderPane root = new BorderPane();
        root.setTop(topBar);
        root.setCenter(chatArea);
        root.setBottom(bottomBar);

        Scene scene = new Scene(root, 900, 600);
        stage.setScene(scene);
        stage.show();

        appendSystem("Conecte-se a um servidor Ollama local rodando em " + hostField.getText() + " e escolha um modelo.");
    }

    private void sendMessage() {
        String prompt = promptField.getText().trim();
        if (prompt.isEmpty()) return;

        String model = modelBox.getValue();
        String host = hostField.getText().trim().isEmpty() ? "http://127.0.0.1:11434" : hostField.getText().trim();

        appendUser(prompt);
        promptField.clear();

        sendBtn.setDisable(true);
        stopBtn.setDisable(false);

        // Start streaming call in background
        ioPool.submit(() -> {
            try {
                OllamaClient client = new OllamaClient(host);
                runningCall = client.generateStream(model, prompt, new OllamaClient.StreamListener() {
                    @Override
                    public void onToken(String token) {
                        Platform.runLater(() -> chatArea.appendText(token));
                    }
                    @Override
                    public void onStart() {
                        Platform.runLater(() -> appendAssistantHeader());
                    }
                    @Override
                    public void onDone() {
                        Platform.runLater(() -> {
                            chatArea.appendText("\n");
                            sendBtn.setDisable(false);
                            stopBtn.setDisable(true);
                        });
                    }
                    @Override
                    public void onError(Exception ex) {
                        Platform.runLater(() -> {
                            appendSystem("Erro: " + ex.getMessage());
                            sendBtn.setDisable(false);
                            stopBtn.setDisable(true);
                        });
                    }
                });
                runningCall.await(); // wait until done or canceled
            } catch (Exception ex) {
                Platform.runLater(() -> {
                    appendSystem("Falha ao iniciar chamada: " + ex.getMessage());
                    sendBtn.setDisable(false);
                    stopBtn.setDisable(true);
                });
            } finally {
                runningCall = null;
            }
        });
    }

    private void appendUser(String text) {
        chatArea.appendText("\n[Você]\n" + text + "\n");
    }

    private void appendAssistantHeader() {
        chatArea.appendText("\n[Ollama]\n");
    }

    private void appendSystem(String text) {
        chatArea.appendText("[Sistema] " + text + "\n");
    }

    @Override
    public void stop() {
        if (runningCall != null) runningCall.cancel();
        ioPool.shutdownNow();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
