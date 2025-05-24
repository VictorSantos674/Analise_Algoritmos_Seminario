package Floyd_Warshall;

import java.util.*;

public class Floyd_Warshall {
    private final Map<String, Map<String, Integer>> grafo;

    public Floyd_Warshall() {
        this.grafo = new HashMap<>();
    }

    public void addEdge(String origem, String destino, int peso) {
        grafo.computeIfAbsent(origem, k -> new HashMap<>()).put(destino, peso);
    }

    public void calcularTodosMenoresCaminhos() {
        Set<String> vertices = new HashSet<>(grafo.keySet());
        for (Map<String, Integer> vizinhos : grafo.values()) {
            vertices.addAll(vizinhos.keySet());
        }

        List<String> listaVertices = new ArrayList<>(vertices);
        int V = listaVertices.size();

        Map<String, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < V; i++) {
            indexMap.put(listaVertices.get(i), i);
        }

        int[][] dist = new int[V][V];

        // Inicializa matriz de distâncias
        for (int i = 0; i < V; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
            dist[i][i] = 0;
        }

        for (String u : grafo.keySet()) {
            for (Map.Entry<String, Integer> entrada : grafo.get(u).entrySet()) {
                String v = entrada.getKey();
                int peso = entrada.getValue();
                dist[indexMap.get(u)][indexMap.get(v)] = peso;
            }
        }

        long inicio = System.nanoTime();

        // Floyd-Warshall
        for (int k = 0; k < V; k++) {
            for (int i = 0; i < V; i++) {
                for (int j = 0; j < V; j++) {
                    if (dist[i][k] != Integer.MAX_VALUE && dist[k][j] != Integer.MAX_VALUE) {
                        dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                    }
                }
            }
        }

        long fim = System.nanoTime();
        double tempoMs = (fim - inicio) / 1e6;

        System.out.println("\n--- Algoritmo de Floyd-Warshall ---");
        System.out.printf("Tempo de execução: %.3f ms\n", tempoMs);

        System.out.println("\nMatriz de distâncias mínimas:");
        System.out.print("       ");
        for (String v : listaVertices) {
            System.out.printf("%10s", v);
        }
        System.out.println();

        for (int i = 0; i < V; i++) {
            System.out.printf("%10s", listaVertices.get(i));
            for (int j = 0; j < V; j++) {
                if (dist[i][j] == Integer.MAX_VALUE)
                    System.out.printf("%10s", "INF");
                else
                    System.out.printf("%10d", dist[i][j]);
            }
            System.out.println();
        }
    }
}
