package Dijkstra;

import java.util.*;

//DESCRIÇÃO DO OBJETIVO DO ARQUIVO:

/*
Neste tópico vou aplicar o algoritmo de Dijkstra em um problema prático de telecomunicações. 
Considere um cenário onde uma empresa de telecomunicações deseja otimizar a rota de transmissão de dados
entre diferentes centros de dados em uma rede de fibra óptica. 
Cada centro de dados é representado por um vértice no grafo, e cada conexão de fibra óptica entre
centros de dados é representada por uma aresta com um peso correspondente ao tempo de transmissão
(latência) entre esses centros.

O objetivo é determinar o caminho mais curto (com menor latência) de um centro de dados
de origem para todos os outros centros de dados na rede.
*/

public class DijkstraProblemaPratico {
    // Classe Node representa cada vértice no grafo
    public static class Node implements Comparable<Node> {
        public final String name; // Nome do vértice
        public int dist = Integer.MAX_VALUE; // Distância inicial (infinita)
        public Node previous = null; // Predecessor no caminho mais curto
        public final Map<Node, Integer> neighbors = new HashMap<>(); // Vizinhos e pesos das arestas

        // Construtor da classe Node
        public Node(String name) {
            this.name = name;
        }

        // Método para imprimir o caminho mais curto até este nó
        private void printPath() {
            if (this == this.previous) {
                System.out.printf("%s", this.name); // Imprime o nó se for o início do caminho
            } else if (this.previous == null) {
                System.out.printf("%s(unreached)", this.name); // Indica que o nó não foi alcançado
            } else {
                this.previous.printPath(); // Chama recursivamente para imprimir o caminho anterior
                System.out.printf(" -> %s(%d)", this.name, this.dist); // Imprime o nó atual e a distância
            }
        }

        // Método de comparação para a fila de prioridade
        public int compareTo(Node other) {
            return Integer.compare(dist, other.dist); // Compara as distâncias
        }
    }

    // Método para calcular os caminhos mais curtos a partir de um nó fonte
    public static void computePaths(Node source) {
        source.dist = 0; // A distância da fonte para si mesma é zero
        PriorityQueue<Node> queue = new PriorityQueue<>(); // Fila de prioridade para nós a serem processados
        queue.add(source); // Adiciona o nó fonte à fila

        while (!queue.isEmpty()) { // Enquanto a fila não estiver vazia
            Node u = queue.poll(); // Remove o nó com a menor distância da fila
            // Processa cada vizinho do nó removido
            for (Map.Entry<Node, Integer> neighborEntry : u.neighbors.entrySet()) {
                Node v = neighborEntry.getKey(); // Vértice vizinho
                int weight = neighborEntry.getValue(); // Peso da aresta entre u e v
                int distanceThroughU = u.dist + weight; // Distância alternativa passando por u
                if (distanceThroughU < v.dist) { // Se a distância alternativa for menor
                    queue.remove(v); // Remove v da fila para atualização
                    v.dist = distanceThroughU; // Atualiza a distância de v
                    v.previous = u; // Define u como o predecessor de v
                    queue.add(v); // Adiciona v de volta à fila com a nova distância
                }
            }
        }
    }

    public static void main(String[] args) {
        // Criação dos nós (vértices do grafo)
        Node A = new Node("A");
        Node B = new Node("B");
        Node C = new Node("C");
        Node D = new Node("D");
        Node E = new Node("E");

        // Definição das arestas e seus pesos
        A.neighbors.put(B, 4); // A -> B com peso 4
        A.neighbors.put(C, 2); // A -> C com peso 2
        B.neighbors.put(C, 1); // B -> C com peso 1
        B.neighbors.put(D, 5); // B -> D com peso 5
        C.neighbors.put(D, 8); // C -> D com peso 8
        C.neighbors.put(E, 10); // C -> E com peso 10
        D.neighbors.put(E, 2); // D -> E com peso 2

        // Array de nós para facilitar a impressão dos resultados
        Node[] nodes = { A, B, C, D, E };
        computePaths(A); // Computa os caminhos mais curtos a partir do nó A

        // Imprime os caminhos mais curtos de A para todos os outros nós
        for (Node node : nodes) {
            System.out.print("Caminho mais curto de A para " + node.name + ": ");
            node.printPath(); // Chama o método para imprimir o caminho
            System.out.println(); // Nova linha para o próximo nó
        }
    }
}