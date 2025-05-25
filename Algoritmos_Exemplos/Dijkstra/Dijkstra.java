package Dijkstra;

import java.util.*;

public class Dijkstra {
    private Map<String, Map<String, Integer>> graph;

    public Dijkstra(Map<String, Map<String, Integer>> graph) {
        this.graph = graph;
    }

    public void calcularMenorCaminho(String origem, String destino) {
        long startTime = System.nanoTime();

        Map<String, Integer> distances = new HashMap<>();
        Map<String, String> predecessors = new HashMap<>();
        PriorityQueue<String> queue = new PriorityQueue<>(Comparator.comparingInt(node -> distances.getOrDefault(node, Integer.MAX_VALUE)));

        for (String node : graph.keySet()) {
            distances.put(node, Integer.MAX_VALUE);
        }
        distances.put(origem, 0);
        queue.add(origem);

        Set<String> visited = new HashSet<>();

        while (!queue.isEmpty()) {
            String current = queue.poll();
            if (visited.contains(current)) continue;
            visited.add(current);

            Map<String, Integer> neighbors = graph.getOrDefault(current, Collections.emptyMap());
            for (Map.Entry<String, Integer> neighbor : neighbors.entrySet()) {
                String nextNode = neighbor.getKey();
                int weight = neighbor.getValue();

                if (!visited.contains(nextNode)) {
                    int newDist = distances.get(current) + weight;
                    if (newDist < distances.getOrDefault(nextNode, Integer.MAX_VALUE)) {
                        distances.put(nextNode, newDist);
                        predecessors.put(nextNode, current);
                        queue.add(nextNode);
                    }
                }
            }
        }

        long endTime = System.nanoTime();
        double tempo = (endTime - startTime) / 1e9;

        List<String> path = new ArrayList<>();
        String current = destino;
        boolean pathExists = distances.get(destino) != Integer.MAX_VALUE;

        System.out.println("Dijkstra");
        System.out.println("Origem: " + origem + " | Destino: " + destino);
        
        if (pathExists) {
            while (current != null) {
                path.add(current);
                current = predecessors.get(current);
            }
            Collections.reverse(path);
            System.out.println("Caminho: " + String.join(" -> ", path));
            System.out.println("Custo total: " + distances.get(destino));
        } else {
            System.out.println("Caminho não encontrado.");
            System.out.println("Custo total: Infinito");
        }
        System.out.printf("Tempo de execução: %.6f segundos%n", tempo);
    }
}