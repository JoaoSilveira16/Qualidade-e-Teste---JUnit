# Qualidade e Teste de Software (UFF): JUnit, Teste Estrutural e de Mutação

Continuação dos exercícios de JUnit. Aqui medimos a cobertura estrutural e o
escore de mutação das classes `Calculadora`, `Carrinho` e `Jokenpo`, testadas
pelo aluno e pelo ChatGPT.

- **Cobertura estrutural** (todos-nós, todas-arestas e complexidade ciclomática):
  [JaCoCo](https://www.jacoco.org/jacoco/) 0.8.12
- **Teste de mutação**: [PIT](https://pitest.org/) 1.16.1

> O enunciado sugere o EclEmma, mas ele é um plugin do Eclipse e o trabalho foi
> feito no VSCode. O EclEmma é apenas uma interface para o JaCoCo, então o
> JaCoCo foi usado diretamente (mesmo motor, mesmos números). O projeto não usa
> Maven/Gradle: tudo roda por linha de comando.

## Estrutura

```
intro_junit-master/
├── src/                    código das classes
│   ├── calculadora/Calculadora.java
│   ├── carrinho/Carrinho.java
│   ├── produto/            Produto e ProdutoNaoEncontradoException
│   └── jokenpo/Main.java   classe do Jokenpo
├── test/                   testes JUnit 5
├── lib/                    .jar das ferramentas (não versionados, ver abaixo)
└── relatorios/
    ├── A/                  cenário A: só os testes do aluno
    │   ├── jacoco/<classe>/index.html
    │   └── pit/<classe>/index.html
    └── B/                  cenário B: testes do aluno + testes do ChatGPT
        ├── jacoco/<classe>/index.html
        └── pit/<classe>/index.html
```

Para ver os relatórios, basta abrir os `index.html` no navegador.

## Cenários

| Cenário | Testes considerados | Como obter |
|---|---|---|
| **A** | só os do aluno | comentar o bloco abaixo de `// Testes adicionais gerados pelo ChatGPT` em `CalculadoraTest` e `CarrinhoTest` |
| **B** | aluno + ChatGPT | todos os testes ativos (estado atual do repositório) |

O `MainTest` (Jokenpo) não separa os testes do aluno e os da IA, então tem um
cenário único, salvo em `relatorios/B`.

## Resultados

| Classe | Cenário | Testes | Instruções | Ramos | Cxty | Escore de mutação |
|---|---|---|---|---|---|---|
| Calculadora | A | 12 | 85% | 62% | 12 | 14/19 = 74% |
| Calculadora | B | 44 | 100% | 100% | 12 | 17/19 = 89% |
| Carrinho | A | 5 | 98% | 75% | 8 | 6/6 = 100% |
| Carrinho | B | 20 | 100% | 100% | 8 | 6/6 = 100% |
| Jokenpo | único | 15 | 100% | 100% | 9 | 14/14 = 100% |

**Como ler:** no JaCoCo, *Instructions/Lines* corresponde a todos-nós,
*Branches* a todas-arestas, e *Cxty* é a complexidade ciclomática (soma dos
métodos, incluindo o construtor). No PIT, *Mutation Coverage* é o escore de
mutação (mortos ÷ gerados).

**Mutantes equivalentes:** os 2 mutantes que sobrevivem na Calculadora (cenário B)
não podem ser mortos por nenhum teste:

- `somatoria`, linha 63: `n >= 0` → `n > 0`. A mudança só deixa de somar 0.
- `compara`, linha 91: `a > b` → `a >= b`. A linha só é alcançada quando `a != b`.

Descontando os equivalentes, o escore da Calculadora B é de 17/17 = 100%.

## Como reproduzir

Requisitos: JDK 17+ e um terminal Git Bash (Windows). No Windows, o classpath
usa `;` mesmo dentro do Git Bash.

### 1. Baixar as bibliotecas para `intro_junit-master/lib/`

- [junit-platform-console-standalone-1.10.2.jar](https://repo1.maven.org/maven2/org/junit/platform/junit-platform-console-standalone/1.10.2/junit-platform-console-standalone-1.10.2.jar)
- [pitest-1.16.1.jar](https://repo1.maven.org/maven2/org/pitest/pitest/1.16.1/pitest-1.16.1.jar)
- [pitest-entry-1.16.1.jar](https://repo1.maven.org/maven2/org/pitest/pitest-entry/1.16.1/pitest-entry-1.16.1.jar)
- [pitest-command-line-1.16.1.jar](https://repo1.maven.org/maven2/org/pitest/pitest-command-line/1.16.1/pitest-command-line-1.16.1.jar)
- [pitest-junit5-plugin-1.2.1.jar](https://repo1.maven.org/maven2/org/pitest/pitest-junit5-plugin/1.2.1/pitest-junit5-plugin-1.2.1.jar)
- [commons-text-1.10.0.jar](https://repo1.maven.org/maven2/org/apache/commons/commons-text/1.10.0/commons-text-1.10.0.jar)
- [commons-lang3-3.12.0.jar](https://repo1.maven.org/maven2/org/apache/commons/commons-lang3/3.12.0/commons-lang3-3.12.0.jar)
- [jacoco-0.8.12.zip](https://repo1.maven.org/maven2/org/jacoco/jacoco/0.8.12/jacoco-0.8.12.zip):
  extrair `lib/jacocoagent.jar` e `lib/jacococli.jar` para `intro_junit-master/lib/jacoco/`

### 2. Compilar (dentro de `intro_junit-master/`)

```bash
rm -rf out   # o agente do JaCoCo acumula dados: limpar sempre ao trocar de cenário

javac -g -encoding UTF-8 -d out/classes \
  src/calculadora/Calculadora.java src/carrinho/Carrinho.java \
  src/produto/Produto.java src/produto/ProdutoNaoEncontradoException.java \
  src/jokenpo/Main.java

javac -g -encoding UTF-8 -d out/test-classes \
  -cp "lib/junit-platform-console-standalone-1.10.2.jar;out/classes" \
  test/calculadora/CalculadoraTest.java test/carrinho/CarrinhoTest.java test/jokenpo/MainTest.java
```

### 3. Cobertura com JaCoCo (exemplo: Calculadora, cenário B)

```bash
java -javaagent:lib/jacoco/jacocoagent.jar=destfile=out/calculadora.exec \
  -jar lib/junit-platform-console-standalone-1.10.2.jar execute \
  -cp "out/classes;out/test-classes" --select-class calculadora.CalculadoraTest

mkdir -p relatorios/B/jacoco/calculadora   # o jacococli não cria a pasta sozinho

java -jar lib/jacoco/jacococli.jar report out/calculadora.exec \
  --classfiles out/classes/calculadora --sourcefiles src --encoding UTF-8 \
  --html relatorios/B/jacoco/calculadora \
  --xml relatorios/B/jacoco/calculadora/jacoco.xml \
  --name "Calculadora - aluno + IA"
```

### 4. Mutação com PIT (exemplo: Calculadora, cenário B)

```bash
java -cp "lib/*;out/classes;out/test-classes" \
  org.pitest.mutationtest.commandline.MutationCoverageReport \
  --reportDir relatorios/B/pit/calculadora \
  --targetClasses calculadora.Calculadora \
  --targetTests calculadora.CalculadoraTest \
  --sourceDirs src --timestampedReports=false
```

Para as outras classes, troque `calculadora`/`Calculadora`/`CalculadoraTest`
por `carrinho`/`Carrinho`/`CarrinhoTest` ou `jokenpo`/`Main`/`MainTest`. Para
o cenário A, comente os testes do ChatGPT, rode `rm -rf out`, recompile e troque
`relatorios/B` por `relatorios/A`.

## Uso de IA

Este README e o arquivo `.gitignore` foram redigidos com o auxílio do Claude
(Anthropic). A execução das ferramentas e a análise dos resultados foram feitas
pelo aluno.
