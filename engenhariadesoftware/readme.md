- Exercicio 1

O primeiro trecho fala sobre a diferença entre “programar” e “engenharia de software”. Embora no dia a dia as pessoas usem esses termos como se fossem a mesma coisa, na prática eles têm significados e implicações diferentes. Programar é o ato de escrever código, muitas vezes focado em resolver um problema imediato. Já a engenharia de software traz um peso maior, pois envolve aplicar conhecimento teórico para criar algo real, estruturado e confiável — assim como fazem engenheiros mecânicos, civis ou aeronáuticos.

A diferença é que, nessas engenharias mais tradicionais, existem regras rígidas e práticas muito bem estabelecidas, porque um erro pode causar danos físicos graves. Na programação, historicamente, não houve a mesma rigidez. Mas hoje, como o software está presente em praticamente todas as áreas da vida, precisamos aplicar métodos mais rigorosos para que o que desenvolvemos seja seguro, confiável e mantenha qualidade com o tempo.

- Exercicio 2

O segundo trecho amplia a visão de engenharia de software, mostrando que não se trata apenas de escrever código, mas também de pensar no conjunto de práticas, processos e ferramentas que garantem que esse código continue útil e sustentável ao longo do tempo. É como se “engenharia de software” fosse “programação integrada ao tempo” — considerando todo o ciclo de vida do software: criação, manutenção, evolução e até a desativação.

O texto destaca três princípios fundamentais que devem estar presentes nas decisões técnicas:

Tempo e mudança – entender que o código vai precisar mudar e se adaptar durante sua vida útil.

Escala e crescimento – prever como a organização e o sistema vão evoluir à medida que aumentam de tamanho.


Trade-offs e custos – fazer escolhas conscientes entre alternativas, avaliando o impacto de longo prazo.

- Exercicio 3

Exemplo Trade-off

Exemplo 1 – Velocidade de entrega vs. Qualidade do código
Às vezes, a pressão para lançar uma funcionalidade rápido faz a gente cortar caminho, pular testes ou deixar de seguir boas práticas para atender um prazo. Isso resolve o problema imediato, mas pode criar dívidas técnicas que vão cobrar a conta no futuro, deixando o código mais difícil de manter e propenso a erros. É uma escolha que precisa ser consciente: vale a pena entregar rápido agora sabendo que vou gastar mais tempo corrigindo depois?

Exemplo 2 – Complexidade da arquitetura vs. Facilidade de manutenção
Uma arquitetura mais complexa pode resolver problemas sofisticados e permitir escalabilidade a longo prazo. Porém, quanto mais camadas e abstrações, mais difícil fica para novos desenvolvedores entenderem o sistema. Isso pode atrasar a entrada de novos membros no time e tornar mudanças simples mais demoradas. Então, preciso avaliar: será que o ganho de desempenho ou escalabilidade compensa a curva de aprendizado e a manutenção mais difícil?

Exemplo 3 – Otimização de desempenho vs. Clareza do código
Existem situações em que posso escrever um código super otimizado para ganhar alguns milissegundos de performance. Mas, para isso, preciso usar técnicas mais complexas ou menos legíveis. O risco é que, no futuro, eu ou outro desenvolvedor não entendamos tão facilmente o que foi feito, o que complica ajustes e correções. Então, devo decidir: vale sacrificar a clareza para ter um ganho pequeno de performance ou é melhor deixar o código mais claro e fácil de manter?

- Exercicio 4
  
class Student {
  - id: String
  - name: String
  - email: String
  + getId(): String
  + getName(): String
  + getEmail(): String
}

class Teacher {
  - id: String
  - name: String
  - email: String
  + getId(): String
  + getName(): String
  + getEmail(): String
}

class Course {
  - id: String
  - name: String
  - workloadHours: int
  - teacher: Teacher
  - students: List<Student>
  + getId(): String
  + getName(): String
  + getWorkloadHours(): int
  + getTeacher(): Teacher
  + getStudents(): List<Student>
  + setTeacher(t: Teacher): void
  + addStudent(s: Student): void
}

' Um professor pode ministrar vários cursos; cada curso tem 1 professor
Teacher "1" <-- "0..*" Course : teaches

' Curso mantém uma lista de alunos (associação unidirecional)
Course  "0..*" --> "0..*" Student : enrolls >

(https://github.com/MrMatheTrue/Bertoti/tree/main/engenhariadesoftware/exercicio%204/school-min)


- Exercicio 5

  classDiagram
direction LR

class Student{
  - id: String
  - name: String
  - email: String
  + getId(): String
  + getName(): String
  + getEmail(): String
}

class Teacher{
  - id: String
  - name: String
  - email: String
  + getId(): String
  + getName(): String
  + getEmail(): String
}

class Course{
  - id: String
  - name: String
  - workloadHours: int
  - teacher: Teacher
  + getId(): String
  + getName(): String
  + getWorkloadHours(): int
  + getTeacher(): Teacher
}

class Enrollment{
  - id: String
  - student: Student
  - course: Course
  + getId(): String
  + getStudent(): Student
  + getCourse(): Course
}

Teacher "1" <-- "0..*" Course : teaches
Enrollment "1" --> "1" Student : student
Enrollment "1" --> "1" Course  : course
%% Student e Course podem participar de várias Enrollment (N..N via Enrollment)


https://github.com/MrMatheTrue/Bertoti/tree/main/engenhariadesoftware/exercicio%205/school-enrollment-min




- Exercicio 6

classDiagram
direction LR

class Instructor{
  - id: UUID
  - fullName: String
  - email: String
}

class Subject{
  - id: UUID
  - title: String
  - hours: short
  - level: Level
  - instructor: Instructor
}

class Learner{
  - id: UUID
  - fullName: String
  - email: String
}

class Level {
<<enumeration>>
  BEGINNER
  INTERMEDIATE
  ADVANCED
}

Instructor "1" <.. "0..*" Subject : teaches
Subject ..> Level : uses




- Exercicio 7

  https://github.com/MrMatheTrue/Bertoti/tree/main/engenhariadesoftware/exercicio-7



- Exercicio 8

https://github.com/MrMatheTrue/Bertoti/tree/main/engenhariadesoftware/exercicio-8









