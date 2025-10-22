# school-enrollment-min

Projeto mínimo (Java 17) com relação simples:
- `Course -> Teacher` (composição direta pelo construtor)
- `Enrollment` liga `Student` a `Course` (N para N via registros de matrícula)
Sem JPA, sem banco, só para rodar.

## Rodar com Maven
```bash
mvn -q clean package
mvn -q exec:java
```

## Rodar sem Maven (Java puro)
```bash
javac src/main/java/*.java -d out
java -cp out Main
```

## Saída esperada
```
Aluno: S1 - João - joao@aluno.com
Curso: Algoritmos (60h) | Professor: Ana
Matrícula: E1 | Student=João | Course=Algoritmos
```
