# school-variant-min

Mesmo relacionamento simples do seu exemplo, **mas com tipos diferentes** para variar:
- `Learner`/`Instructor` usam `UUID` para `id`.
- `Subject` tem `hours: short` e um `Level` (enum) além do `Instructor`.
- Sem JPA, sem banco; só Java 17 puro. Pronto para rodar.

## Rodar com Maven
```bash
mvn -q clean package
mvn -q exec:java
```

## Rodar sem Maven
```bash
javac src/main/java/*.java -d out
java -cp out Main
```

## Saída típica
```
Learner: 7d2e...-... - João - joao@aluno.com
Instructor: Ana
Subject: Algoritmos (60h, BEGINNER)
```
