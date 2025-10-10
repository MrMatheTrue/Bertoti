package org.china;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * Minimal Ollama client for /api/generate with streaming.
 *
 * Request: POST { "model": "...", "prompt": "...", "stream": true }
 * Response: chunked JSON lines with fields: {"model":"...","created_at":"...","response":"...","done":false}
 * Final line has "done": true.
 */
public class OllamaClient {

    private final String baseUrl;
    private static final ObjectMapper MAPPER = new ObjectMapper();

    public OllamaClient(String baseUrl) {
        this.baseUrl = baseUrl.endsWith("/") ? baseUrl.substring(0, baseUrl.length()-1) : baseUrl;
    }

    public interface StreamListener {
        void onStart();
        void onToken(String token);
        void onDone();
        void onError(Exception ex);
    }

    public static class RunningCall {
        private volatile boolean cancelled = false;
        private Thread thread;

        private synchronized void bind(Thread t) { this.thread = t; }

        public void cancel() {
            cancelled = true;
            Thread t;
            synchronized (this) { t = thread; }
            if (t != null) t.interrupt();
        }
        public void await() throws InterruptedException {
            Thread t;
            synchronized (this) { t = thread; }
            if (t != null) t.join();
        }
    }

    public RunningCall generateStream(String model, String prompt, StreamListener listener) throws IOException {
        URL url = new URL(baseUrl + "/api/generate");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");
        conn.setDoOutput(true);
        conn.setDoInput(true);

        // Build payload
        String payload = "{\"model\":\"" + escape(model) + "\",\"prompt\":\"" + escape(prompt) + "\",\"stream\":true}";

        try (OutputStream os = conn.getOutputStream()) {
            os.write(payload.getBytes(StandardCharsets.UTF_8));
        }

        int code = conn.getResponseCode();
        InputStream is = (code >= 200 && code < 300) ? conn.getInputStream() : conn.getErrorStream();
        if (is == null) throw new IOException("HTTP " + code + " sem corpo");

        RunningCall rc = new RunningCall();
        Thread t = new Thread(() -> {
            listener.onStart();
            try (BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
                String line;
                while (!Thread.currentThread().isInterrupted() && (line = br.readLine()) != null) {
                    if (rc.cancelled) break;
                    line = line.trim();
                    if (line.isEmpty()) continue;
                    JsonNode node = MAPPER.readTree(line);
                    if (node.has("response")) {
                        String tok = node.get("response").asText("");
                        if (!tok.isEmpty()) listener.onToken(tok);
                    }
                    if (node.has("done") && node.get("done").asBoolean(false)) {
                        break;
                    }
                }
                listener.onDone();
            } catch (Exception ex) {
                if (!rc.cancelled) listener.onError(ex);
            } finally {
                try { conn.disconnect(); } catch (Exception ignore) {}
            }
        }, "ollama-stream");
        rc.bind(t);
        t.start();
        return rc;
    }

    private static String escape(String s) {
        return s.replace("\\", "\\\\").replace("\"", "\\\"").replace("\n", "\\n");
    }
}
