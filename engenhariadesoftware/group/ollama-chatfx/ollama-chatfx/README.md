# Ollama ChatFX

Interface JavaFX simples para conversar com um servidor **Ollama** local (ex.: `http://localhost:11434`) usando o endpoint `/api/generate` com **streaming**.

## Pré-requisitos
- **JDK 17** instalado e no `PATH` (`java -version` deve mostrar 17).
- **Maven 3.8+**.
- **Ollama** rodando localmente e com um **modelo baixado** (ex.: `ollama run llama3`).

## Como rodar
```bash
mvn -q clean javafx:run
```

## Dicas
- No topo da janela você pode trocar o **modelo** e o **host** do Ollama.
- Campo de texto aceita **Enter** para enviar (Shift+Enter insere nova linha).
- Botão **Parar** cancela o streaming atual.
