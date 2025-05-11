# Projeto: Algoritmos de Menor Caminho em Grafos

Este projeto implementa e analisa os principais algoritmos de menor caminho em grafos: **Dijkstra**, **Floyd-Warshall** e **Johnson**, com foco educacional para uso em seminários e estudos de Análise de Algoritmos.

## Algoritmos Implementados

### 1. Dijkstra

* **Tipo de Grafo**: Grafos com pesos positivos.
* **Técnica**: Algoritmo guloso com uso de fila de prioridade.
* **Complexidade**:

  * Pior caso: $O((V + E) \log V)$
  * Melhor caso: $\Omega(V)$
  * Caso médio: $\Theta((V + E) \log V)$

### 2. Floyd-Warshall

* **Tipo de Grafo**: Grafos ponderados com pesos positivos ou negativos (sem ciclos negativos).
* **Técnica**: Programação dinâmica.
* **Complexidade**:

  * $O(V^3)$
  * $\Omega(V^2)$ (preenchimento da matriz inicial)
  * $\Theta(V^3)$

### 3. Johnson

* **Tipo de Grafo**: Grafos com pesos positivos e negativos (sem ciclos negativos).
* **Técnica**: Reponderação + Bellman-Ford + Dijkstra.
* **Complexidade**:

  * Pior caso: $O(VE + V^2 \log V)$
  * $\Omega(V)$
  * $\Theta(VE + V^2 \log V)$

---

## Estrutura do Código

* **Dijkstra.java**: Implementa o algoritmo de Dijkstra com classe auxiliar `Aresta`.
* **FloydWarshall.java**: Usa matriz de adjacência para encontrar todos os pares de menor caminho.
* **Johnson.java**: Reutiliza `Dijkstra` e aplica reponderação com Bellman-Ford.

---

## Como Executar

1. Compile os arquivos:

```bash
javac *.java
```

2. Execute com os dados desejados:

```bash
java Main
```

---

## Aplicabilidade

* Seminários sobre Teoria dos Grafos.
* Estudos de Eficiência de Algoritmos.
* Comparativos entre algoritmos para grafos com diferentes tipos de pesos.

---

## Autor

* **Aluno:** Maiara Torres, Victor Santos, Maria Vitória, 
* **Disciplina:** Análise de Algoritmos
* **Professor:** Robson Lins
* **Instituição:** Universidade Católica de Pernambuco (UNICAP)

---

## Licença

Este projeto tem fins educacionais e está livre para uso e modificação em ambientes acadêmicos.
