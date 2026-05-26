# Resolução do Problemas com Grafos - Trabalho Prático 1 (Unidade 3)
**Professor:** Prof. Me Ricardo Carubbi  
**Disciplina:** Resolução de Problemas com Grafos  
**Instituição:** Centro de Ciências Tecnológicas (CCT)

---

## 📋 Informações Gerais

* **Nome do Problema:** Island Hopping
* **Link do Problema:** [Kattis - Island Hopping](https://open.kattis.com/problems/islandhopping)
* **Linguagem Utilizada:** Java (JDK 17 ou superior)
* **Algoritmo Implementado:** Algoritmo de Kruskal com Union-Find (DSU)

### 👥 Integrantes do Grupo
* [Natan Adams] - [2210351]
* [Luis Guilherme] - [2410504]
* [Samuel Moreira] - [2413536]
* [José Guilherme] - [2410501]

---

## 🗺️ Modelagem do Problema como Grafo Ponderado

O problema exige encontrar a menor quantidade de metros de pontes necessários para interconectar um conjunto de ilhas isoladas no oceano. Cada ilha possui coordenadas cartesianas $(x, y)$ bidimensionais.

A modelagem adotada foi a seguinte:
1.  **Vértices ($V$):** Cada ilha representa um vértice no grafo, identificada por um índice de $0$ a $N-1$.
2.  **Arestas ($E$):** Como qualquer ilha pode, em teoria, ligar-se diretamente a qualquer outra ilha, o problema descreve um **Grafo Completo (Não Direcionado)**. Portanto, o número total de arestas possíveis é dado pela fórmula:
    $$E = \frac{V(V-1)}{2}$$
3.  **Pesos:** O peso de cada aresta representa o custo de construção da ponte entre duas ilhas, calculado através da **Distância Euclidiana** entre as coordenadas $A(x_1, y_1)$ e $B(x_2, y_2)$:
    $$d = \sqrt{(x_2 - x_1)^2 + (y_2 - y_1)^2}$$

---

## 🛠️ Algoritmo Utilizado e Estruturas de Dados

Para encontrar a **Árvore Geradora Mínima (MST)**, optamos pelo **Algoritmo de Kruskal**, cuja estratégia consiste em processar as arestas em ordem crescente de peso, adicionando-as à solução desde que não formem ciclos.

### O Papel do Union-Find / DSU (Disjoint Set Union)
Para gerenciar os conjuntos de componentes conectados e realizar a detecção de ciclos de forma ultraeficiente, implementamos a nossa própria estrutura **Union-Find**. 
* **Find:** Localiza o representante (raiz) de um determinado conjunto. Utiliza a otimização de **Compressão de Caminho (Path Compression)**, apontando todos os nós visitados diretamente para a raiz, achatando a árvore.
* **Union:** Une dois subconjuntos distintos em um só. Utiliza a otimização de **União por Rank (Union by Rank)**, pendurando a árvore de menor altura sob a raiz da árvore de maior altura, minimizando o crescimento da profundidade.

### Variação de MST Usada
Foi utilizada a aplicação clássica da MST (Árvore Geradora Mínima). Não há restrições adicionais, restrições de grau ou componentes previamente conectados no enunciado básico. O objetivo é puramente minimizar o peso total sobre um grafo denso conexo.

---

## 📊 Análise de Complexidade

### Complexidade de Tempo
1.  **Construção do Grafo:** Iterar por todos os pares de ilhas para calcular as distâncias custa $\mathcal{O}(V^2)$.
2.  **Ordenação das Arestas:** Como o grafo é completo, temos $E = \mathcal{O}(V^2)$ arestas. Ordenar essas arestas via `Collections.sort` (TimSort) leva:
    $$\mathcal{O}(E \log E) = \mathcal{O}(V^2 \log(V^2)) = \mathcal{O}(V^2 \cdot 2 \log V) = \mathcal{O}(V^2 \log V)$$
3.  **Operações Union-Find:** O algoritmo itera pelas arestas ordenadas executando chamadas de `find` e `union`. Com as otimizações de *path compression* e *union by rank*, o custo por operação é amortizado em $\mathcal{O}(\alpha(V))$, onde $\alpha$ é a função inversa de Ackermann (efetivamente constante $\le 4$ na prática). Para $E$ arestas, o tempo é $\mathcal{O}(E \cdot \alpha(V))$.

**Complexidade Total de Tempo:** $\mathcal{O}(V^2 \log V)$ por caso de teste, dominada inteiramente pelo processo de ordenação das arestas.

### Complexidade de Espaço
* Para armazenar as coordenadas das ilhas: $\mathcal{O}(V)$
* Para armazenar a lista de todas as arestas do grafo completo: $\mathcal{O}(E) = \mathcal{O}(V^2)$
* Estruturas de vetores `parent` e `rank` do DSU: $\mathcal{O}(V)$

**Complexidade Total de Espaço:** $\mathcal{O}(V^2)$ para alocar e gerenciar todas as pontes potenciais.

---

## ⚠️ Casos Especiais Relevantes

Durante o desenvolvimento, os seguintes cenários e nuances foram tratados para mitigar erros:
* **Precisão de Ponto Flutuante (`double`):** As coordenadas e distâncias exigem alta precisão decimal. Variáveis do tipo `float` causariam erros de arredondamento (*Wrong Answer*). Toda a lógica matemática utiliza `double`.
* **Formatação de Saída (Localidade):** O juiz online Kattis espera separador decimal por PONTO (ex: `116.541`) e precisão exata de 3 casas decimais. No Java, configuramos explicitamente o `Locale.US` tanto no `Scanner` quanto no `System.out.printf`, impedindo que sistemas rodando em Português usem a vírgula como separador.
* **Grafo de Ilha Única:** Embora o limite inferior seja tipicamente maior, se $N=1$, o loop de arestas nem inicia, o DSU encerra imediatamente e o custo retornado é `0.000`, mantendo a consistência.

---

## 🚀 Como Executar a Solução

O projeto é autocontido e não depende de IDEs específicas, podendo ser compilado e executado direto via linha de comando (terminal).

### Pré-requisitos
* Java Development Kit (JDK) 17 ou superior instalado.
* Variáveis de ambiente do Java configuradas (`java` e `javac`).

### Passo a Passo

1.  **Clonar o repositório:**
    ```bash
    git clone https://github.com/[seu-usuario]/[seu-repositorio].git
    cd [seu-repositorio]/T1/src
    ```

2.  **Compilar o código fonte:**
    ```bash
    javac Main.java
    ```

3.  **Executar passando um arquivo de dados como entrada (ou digitando no terminal):**
    ```bash
    java Main < ../dados/entradas_do_problema.txt
    ```

---

## 🏆 Evidência de Aceitação (Accepted)

Abaixo está o registo de aceitação da submissão na plataforma Kattis:

![Resultado Accepted](../evidencias/accepted.png)

*(Nota: Caso a imagem acima não carregue, o arquivo correspondente pode ser consultado diretamente na pasta `/evidencias` deste repositório).*
