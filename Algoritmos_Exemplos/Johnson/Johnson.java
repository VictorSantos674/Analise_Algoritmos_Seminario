package Johnson;

import java.util.*;

public class Johnson {
    private Map<String, Map<String, Integer>> graph;

    public Johnson(Map<String, Map<String, Integer>> graph) {
        this.graph = graph;
    }

    public void calcularMenorCaminho(String origem, String destino) {
        long startTime = System.nanoTime();

        try {
            Map<String, Map<String, Integer>> tempGraph = new HashMap<>(graph);
            String dummy = "DUMMY_JOHNSON";
            tempGraph.put(dummy, new HashMap<>());
            for (String node : graph.keySet()) {
                tempGraph.get(dummy).put(node, 0);
            }

            Map<String, Integer> h = bellmanFord(tempGraph, dummy);
            tempGraph.remove(dummy);

            Map<String, Map<String, Integer>> reweighted = reweightGraph(tempGraph, h);
            Map<String, Integer> distances = dijkstra(reweighted, origem, h, destino);

            System.out.println("Johnson");
            System.out.println("Origem: " + origem + " | Destino: " + destino);

            if (distances.get(destino) == Integer.MAX_VALUE) {
                System.out.println("Caminho não encontrado.");
                System.out.println("Custo total: Infinito");
            } else {
                List<String> path = reconstructPath(reweighted, origem, destino, distances);
                System.out.println("Caminho: " + String.join(" -> ", path));
                System.out.println("Custo total: " + 
                    (distances.get(destino) - h.get(origem) + h.get(destino)));
            }

        } catch (RuntimeException e) {
            System.out.println("Johnson");
            System.out.println("Origem: " + origem + " | Destino: " + destino);
            System.out.println("Erro: " + e.getMessage());
        }

        double tempo = (System.nanoTime() - startTime) / 1e9;
        System.out.printf("Tempo de execução: %.6f segundos%n", tempo);
    }

    private Map<String, Integer> bellmanFord(Map<String, Map<String, Integer>> graph, String source) {
        Map<String, Integer> distances = new HashMap<>();
        for (String node : graph.keySet()) {
            distances.put(node, Integer.MAX_VALUE);
        }
        distances.put(source, 0);

        for (int i = 0; i < graph.size() - 1; i++) {
            for (String u : graph.keySet()) {
                if (distances.get(u) == Integer.MAX_VALUE) continue;
                for (Map.Entry<String, Integer> edge : graph.get(u).entrySet()) {
                    String v = edge.getKey();
                    int weight = edge.getValue();
                    if (distances.get(v) > distances.get(u) + weight) {
                        distances.put(v, distances.get(u) + weight);
                    }
                }
            }
        }

        for (String u : graph.keySet()) {
            if (distances.get(u) == Integer.MAX_VALUE) continue;
            for (Map.Entry<String, Integer> edge : graph.get(u).entrySet()) {
                String v = edge.getKey();
                int weight = edge.getValue();
                if (distances.get(v) > distances.get(u) + weight) {
                    throw new RuntimeException("O grafo contém um ciclo de peso negativo.");
                }
            }
        }

        return distances;
    }

    private Map<String, Map<String, Integer>> reweightGraph(
            Map<String, Map<String, Integer>> graph, Map<String, Integer> h) {
        Map<String, Map<String, Integer>> reweighted = new HashMap<>();
        for (String u : graph.keySet()) {
            reweighted.put(u, new HashMap<>());
            for (Map.Entry<String, Integer> edge : graph.get(u).entrySet()) {
                String v = edge.getKey();
                int weight = edge.getValue();
                reweighted.get(u).put(v, weight + h.get(u) - h.get(v));
            }
        }
        return reweighted;
    }

    private Map<String, Integer> dijkstra(Map<String, Map<String, Integer>> graph, 
            String source, Map<String, Integer> h, String destino) {
        Map<String, Integer> distances = new HashMap<>();
        Map<String, String> predecessors = new HashMap<>();
        PriorityQueue<String> queue = new PriorityQueue<>(
            Comparator.comparingInt(node -> distances.getOrDefault(node, Integer.MAX_VALUE)));

        for (String node : graph.keySet()) {
            distances.put(node, Integer.MAX_VALUE);
        }
        distances.put(source, 0);
        queue.add(source);

        Set<String> visited = new HashSet<>();

        while (!queue.isEmpty()) {
            String u = queue.poll();
            if (visited.contains(u)) continue;
            visited.add(u);

            for (Map.Entry<String, Integer> edge : graph.getOrDefault(u, Collections.emptyMap()).entrySet()) {
                String v = edge.getKey();
                int weight = edge.getValue();

                if (!visited.contains(v)) {
                    int newDist = distances.get(u) + weight;
                    if (newDist < distances.get(v)) {
                        distances.put(v, newDist);
                        predecessors.put(v, u);
                        queue.add(v);
                    }
                }
            }
        }

        return distances;
    }

    private List<String> reconstructPath(Map<String, Map<String, Integer>> graph, 
            String origem, String destino, Map<String, Integer> distances) {
        List<String> path = new ArrayList<>();
        if (distances.get(destino) == Integer.MAX_VALUE) return path;

        Map<String, String> predecessors = new HashMap<>();
        PriorityQueue<String> queue = new PriorityQueue<>(
            Comparator.comparingInt(node -> distances.get(node)));

        queue.add(origem);
        while (!queue.isEmpty()) {
            String u = queue.poll();
            for (String v : graph.getOrDefault(u, Collections.emptyMap()).keySet()) {
                if (distances.get(v) == distances.get(u) + graph.get(u).get(v)) {
                    predecessors.put(v, u);
                    queue.add(v);
                }
            }
        }

        String current = destino;
        while (current != null) {
            path.add(current);
            current = predecessors.get(current);
        }
        Collections.reverse(path);

        return path;
    }
}