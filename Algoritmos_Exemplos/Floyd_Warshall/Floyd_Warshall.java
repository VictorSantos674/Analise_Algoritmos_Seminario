package Floyd_Warshall;

import java.util.*;

public class Floyd_Warshall {
    private Map<String, Map<String, Integer>> graph;

    public Floyd_Warshall(Map<String, Map<String, Integer>> graph) {
        this.graph = graph;
    }

    public void calcularMenorCaminho(String origem, String destino) {
        long startTime = System.nanoTime();

        Set<String> nodes = graph.keySet();
        List<String> nodeList = new ArrayList<>(nodes);
        int n = nodeList.size();
        Map<String, Integer> nodeIndex = new HashMap<>();

        for (int i = 0; i < n; i++) {
            nodeIndex.put(nodeList.get(i), i);
        }

        int[][] dist = new int[n][n];
        int[][] next = new int[n][n];

        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
            Arrays.fill(next[i], -1);
            dist[i][i] = 0;
        }

        for (String u : graph.keySet()) {
            int uIdx = nodeIndex.get(u);
            for (Map.Entry<String, Integer> entry : graph.get(u).entrySet()) {
                String v = entry.getKey();
                int weight = entry.getValue();
                int vIdx = nodeIndex.get(v);
                dist[uIdx][vIdx] = weight;
                next[uIdx][vIdx] = vIdx;
            }
        }

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (dist[i][k] != Integer.MAX_VALUE && dist[k][j] != Integer.MAX_VALUE) {
                        if (dist[i][j] > dist[i][k] + dist[k][j]) {
                            dist[i][j] = dist[i][k] + dist[k][j];
                            next[i][j] = next[i][k];
                        }
                    }
                }
            }
        }

        int origemIdx = nodeIndex.get(origem);
        int destinoIdx = nodeIndex.get(destino);

        System.out.println("Floyd-Warshall");
        System.out.println("Origem: " + origem + " | Destino: " + destino);

        if (next[origemIdx][destinoIdx] == -1) {
            System.out.println("Caminho não encontrado.");
            System.out.println("Custo total: Infinito");
        } else {
            List<String> path = new ArrayList<>();
            path.add(nodeList.get(origemIdx));
            
            int currentIdx = origemIdx;
            while (currentIdx != destinoIdx) {
                currentIdx = next[currentIdx][destinoIdx];
                path.add(nodeList.get(currentIdx));
            }

            System.out.println("Caminho: " + String.join(" -> ", path));
            System.out.println("Custo total: " + dist[origemIdx][destinoIdx]);
        }

        double tempo = (System.nanoTime() - startTime) / 1e9;
        System.out.printf("Tempo de execução: %.6f segundos%n", tempo);
    }
}