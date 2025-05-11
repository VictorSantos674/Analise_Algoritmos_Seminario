package Johnson;

import java.util.*;
import Djikstra.Djikstra;

// ------------------------------
// 3. ALGORITMO DE JOHNSON
// ------------------------------

// Pseudocódigo:
/*
1. Adicione vértice q conectado a todos os vértices com peso 0
2. Execute Bellman-Ford a partir de q para calcular h[v] (potenciais)
3. Recalcule os pesos: w'(u,v) = w(u,v) + h[u] - h[v]
4. Remova q e use Dijkstra de cada vértice com os novos pesos
*/

// Complexidade:
// - O(VE + V^2 log V)
// - Ω(V)
// - Θ(VE + V^2 log V)

class Johnson {
    static class Aresta {
        int origem, destino, peso;
        Aresta(int o, int d, int p) { origem = o; destino = d; peso = p; }
    }

    static boolean bellmanFord(List<Aresta> arestas, int V, int origem, int[] dist) {
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[origem] = 0;

        for (int i = 1; i < V; i++)
            for (Aresta e : arestas)
                if (dist[e.origem] != Integer.MAX_VALUE && dist[e.origem] + e.peso < dist[e.destino])
                    dist[e.destino] = dist[e.origem] + e.peso;

        for (Aresta e : arestas)
            if (dist[e.origem] != Integer.MAX_VALUE && dist[e.origem] + e.peso < dist[e.destino])
                return false; // ciclo negativo
        return true;
    }

    static void johnson(int[][] grafo) {
        int V = grafo.length;
        List<Aresta> arestas = new ArrayList<>();
        for (int u = 0; u < V; u++)
            for (int v = 0; v < V; v++)
                if (grafo[u][v] != Integer.MAX_VALUE)
                    arestas.add(new Aresta(u, v, grafo[u][v]));

        int[] h = new int[V];
        if (!bellmanFord(arestas, V, 0, h)) {
            System.out.println("Ciclo negativo detectado.");
            return;
        }

        int[][] novoGrafo = new int[V][V];
        for (int u = 0; u < V; u++)
            for (int v = 0; v < V; v++)
                if (grafo[u][v] != Integer.MAX_VALUE)
                    novoGrafo[u][v] = grafo[u][v] + h[u] - h[v];
                else
                    novoGrafo[u][v] = Integer.MAX_VALUE;

        for (int u = 0; u < V; u++) {
            List<List<Djikstra.Aresta>> adj = new ArrayList<>();
            for (int i = 0; i < V; i++) adj.add(new ArrayList<>());
            for (int v = 0; v < V; v++)
                if (novoGrafo[u][v] != Integer.MAX_VALUE)
                    adj.get(u).add(new Djikstra.Aresta(v, novoGrafo[u][v]));

            Djikstra.dijkstra(adj, u);
        }
    }
}