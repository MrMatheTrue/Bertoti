# school-min

Projeto mínimo em Java 17 + Maven para demonstrar a relação simples entre três classes (`Student`, `Teacher`, `Course`)
sem banco de dados, sem JPA. Tudo em memória.

## Como rodar

```bash
mvn -q clean package
mvn -q exec:java
```

## O que você verá

- Criação de um `Teacher` (Ada Lovelace)
- Criação de dois `Student`
- Criação de um `Course` ("Estruturas de Dados") com `workloadHours=60`
- Associação do professor ao curso
- Associação dos alunos ao curso
- Impressões no console mostrando os objetos e a contagem de alunos matriculados
