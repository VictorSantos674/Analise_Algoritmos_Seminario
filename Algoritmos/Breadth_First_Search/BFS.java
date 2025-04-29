package Breadth_First_Search;

import java.util.LinkedList;
import java.util.Queue;

public class BFS {

    // Função para realizar a busca em largura
    public static void bfs(int[][] grafo, int inicio) {
        // Array para marcar os vértices visitados
        boolean[] visitado = new boolean[grafo.length];
        // Fila para armazenar os vértices a serem visitados
        Queue<Integer> fila = new LinkedList<>();
        // Marcar o vértice inicial como visitado e adicioná-lo à fila
        visitado[inicio] = true;
        fila.add(inicio);

        // Enquanto a fila não estiver vazia
        while (!fila.isEmpty()) {
            // Remover o vértice da frente da fila e imprimir
            int vertice = fila.poll();
            System.out.print(vertice + " ");

            // Percorrer todos os vértices adjacentes ao vértice atual
            for (int i = 0; i < grafo.length; i++) {
                // Se o vértice adjacente não foi visitado e há uma aresta entre eles
                if (!visitado[i] && grafo[vertice][i] == 1) {
                    // Marcar o vértice adjacente como visitado e adicioná-lo à fila
                    visitado[i] = true;
                    fila.add(i);
                }
            }
        }
    }

    // Método principal
    public static void main(String[] args) {
        int[][] grafo = {
            {0, 1, 0, 0, 1},
            {1, 0, 1, 1, 1},
            {0, 1, 0, 1, 0},
            {0, 1, 1, 0, 1},
            {1, 1, 0, 1, 0}
        };
        // Chamar a função BFS com o vértice inicial 0
        System.out.println("BFS traversal starting from vertex 0:");
        bfs(grafo, 0);
    }
}