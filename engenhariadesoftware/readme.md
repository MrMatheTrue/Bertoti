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
```mermaid
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
  - students: List~Student~
  + getId(): String
  + getName(): String
  + getWorkloadHours(): int
  + getTeacher(): Teacher
  + getStudents(): List~Student~
  + setTeacher(t: Teacher): void
  + addStudent(s: Student): void
}

%% Relações
Teacher "1" <.. "0..*" Course : teaches
Course  "0..*" --> "0..*" Student : enrolls
(https://github.com/MrMatheTrue/Bertoti/tree/main/engenhariadesoftware/exercicio%204/school-min)


- Exercicio 5

Student.java

public class Student {
    private String id;
    private String name;
    private String email;

    public Student(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
}


Teacher.java

public class Teacher {
    private String id;
    private String name;
    private String email;

    public Teacher(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
}


Course.java

public class Course {
    private String id;
    private String name;
    private int workloadHours;
    private Teacher teacher;

    public Course(String id, String name, int workloadHours, Teacher teacher) {
        this.id = id;
        this.name = name;
        this.workloadHours = workloadHours;
        this.teacher = teacher;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public int getWorkloadHours() { return workloadHours; }
    public Teacher getTeacher() { return teacher; }
}




Main.java

public class Main {
    public static void main(String[] args) {
        // Criar professor
        Teacher t = new Teacher("T1", "Ana", "ana@escola.com");

        // Criar curso com professor
        Course c = new Course("C1", "Algoritmos", 60, t);

        // Criar aluno
        Student s = new Student("S1", "João", "joao@aluno.com");

        // Teste simples
        System.out.println("Aluno: " + s.getId() + " - " + s.getName() + " - " + s.getEmail());
        System.out.println("Professor: " + c.getTeacher().getName());
        System.out.println("Curso: " + c.getName() + " (" + c.getWorkloadHours() + "h)");
    }
}




- Exercicio 6

![Imagem Teste](https://github.com/MrMatheTrue/Bertoti/blob/main/img/exercicio6.png)



- Exercicio 7

  https://github.com/MrMatheTrue/Bertoti/tree/main/engenhariadesoftware/exercicio-7



- Exercicio 8

https://github.com/MrMatheTrue/Bertoti/tree/main/engenhariadesoftware/exercicio-8





