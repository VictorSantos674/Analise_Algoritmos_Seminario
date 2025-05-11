package Djikstra;

import java.util.*;

// ------------------------------
// 1. ALGORITMO DE DIJKSTRA
// ------------------------------

// Pseudocódigo Dijkstra (para grafos com pesos positivos):
/*
1. Inicialize dist[v] = infinito para todos os v ∈ V, exceto dist[source] = 0
2. Crie um conjunto Q com todos os v ∈ V
3. Enquanto Q não estiver vazio:
   a. u ← v em Q com menor dist[u]
   b. Remova u de Q
   c. Para cada vizinho v de u:
       i. alt = dist[u] + peso(u, v)
       ii. Se alt < dist[v], atualize dist[v] = alt
*/

// Complexidade:
// - O(n^2) usando matriz de adjacência e busca linear
// - O((V + E) log V) com fila de prioridade (Heap)
// - Notção O: O(V^2) ou O((V + E) log V)
// - Ω: Ω(V)
// - Θ: Θ((V + E) log V) no melhor caso com heap

public class Djikstra {
   public static class Aresta {
        int destino, peso;
        public Aresta(int d, int p) { destino = d; peso = p; }
    }

    public static void dijkstra(List<List<Aresta>> grafo, int origem) {
        int V = grafo.size();
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[origem] = 0;

        PriorityQueue<int[]> fila = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        fila.add(new int[]{origem, 0});

        while (!fila.isEmpty()) {
            int[] atual = fila.poll();
            int u = atual[0];

            for (Aresta a : grafo.get(u)) {
                int v = a.destino;
                int peso = a.peso;
                if (dist[u] + peso < dist[v]) {
                    dist[v] = dist[u] + peso;
                    fila.add(new int[]{v, dist[v]});
                }
            }
        }

        System.out.println("Distâncias mínimas a partir de " + origem + ": " + Arrays.toString(dist));
    } 
}
