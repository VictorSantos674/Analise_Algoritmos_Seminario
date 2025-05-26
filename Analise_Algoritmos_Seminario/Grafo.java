package Analise_Algoritmos_Seminario;

import java.util.*;

public class Grafo {
    private Map<String, List<Edge>> adjList = new HashMap<>();
    private Set<String> vertices = new HashSet<>();

    public void inicializarGrafo() {
        // Grafo baseado no mapa da Romênia (exemplo clássico)
        addEdge("Arad", "Zerind", 75);
        addEdge("Arad", "Sibiu", 140);
        addEdge("Arad", "Timisoara", 118);
        addEdge("Zerind", "Oradea", 71);
        addEdge("Oradea", "Sibiu", 151);
        addEdge("Sibiu", "Fagaras", 99);
        addEdge("Sibiu", "Rimnicu Vilcea", 80);
        addEdge("Timisoara", "Lugoj", 111);
        addEdge("Lugoj", "Mehadia", 70);
        addEdge("Mehadia", "Drobeta", 75);
        addEdge("Drobeta", "Craiova", 120);
        addEdge("Rimnicu Vilcea", "Craiova", 146);
        addEdge("Rimnicu Vilcea", "Pitesti", 97);
        addEdge("Craiova", "Pitesti", 138);
        addEdge("Fagaras", "Bucharest", 211);
        addEdge("Pitesti", "Bucharest", 101);
        addEdge("Giurgiu", "Bucharest", 90);
    }

    public void addEdge(String from, String to, int weight) {
        adjList.computeIfAbsent(from, k -> new ArrayList<>()).add(new Edge(to, weight));
        adjList.computeIfAbsent(to, k -> new ArrayList<>()).add(new Edge(from, weight));
        vertices.add(from);
        vertices.add(to);
    }

    public Set<String> getVertices() {
        return vertices;
    }

    public Map<String, List<Edge>> getAdjList() {
        return adjList;
    }

    // Conversor para compatibilidade com algoritmos atualizados
    public Map<String, Map<String, Integer>> getAdjacencyMap() {
        Map<String, Map<String, Integer>> converted = new HashMap<>();
        for (String origem : adjList.keySet()) {
            Map<String, Integer> vizinhos = new HashMap<>();
            for (Edge edge : adjList.get(origem)) {
                vizinhos.put(edge.to, edge.weight);
            }
            converted.put(origem, vizinhos);
        }
        return converted;
    }

    public void printGraph() {
        for (String city : adjList.keySet()) {
            System.out.print(city + " -> ");
            for (Edge edge : adjList.get(city)) {
                System.out.print(edge.to + "(" + edge.weight + ") ");
            }
            System.out.println();
        }
    }

    public static class Edge {
        public String to;
        public int weight;
        public Edge(String to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }
}