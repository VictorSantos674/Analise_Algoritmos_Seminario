package Floyd_Warshall;

import java.util.*;

// ------------------------------
// 2. ALGORITMO DE FLOYD-WARSHALL
// ------------------------------

// Pseudocódigo:
/*
1. Para todos os pares (i, j):
   - se i == j: dist[i][j] = 0
   - se (i, j) é aresta: dist[i][j] = peso(i,j)
   - senão: dist[i][j] = infinito
2. Para cada vértice k:
   Para cada par (i, j):
       dist[i][j] = min(dist[i][j], dist[i][k] + dist[k][j])
*/

// Complexidade:
// - O(V^3)
// - Ω(V^2) (matriz inicial)
// - Θ(V^3) no geral

public class Floyd_Warshall {
    static void floydWarshall(int[][] grafo) {
        int V = grafo.length;
        int[][] dist = new int[V][V];

        for (int i = 0; i < V; i++)
            for (int j = 0; j < V; j++)
                dist[i][j] = grafo[i][j];

        for (int k = 0; k < V; k++)
            for (int i = 0; i < V; i++)
                for (int j = 0; j < V; j++)
                    if (dist[i][k] != Integer.MAX_VALUE && dist[k][j] != Integer.MAX_VALUE)
                        dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);

        System.out.println("Matriz de distâncias mínimas:");
        for (int[] linha : dist)
            System.out.println(Arrays.toString(linha));
    }
}