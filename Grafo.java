package Analise_Algoritmos_Seminario;

import java.util.*;

public class Grafo {
    private Map<String, List<Edge>> adjList = new HashMap<>();
    private Set<String> vertices = new HashSet<>();

    public void addEdge(String from, String to, int weight) {
        adjList.computeIfAbsent(from, k -> new ArrayList<>()).add(new Edge(to, weight));
        adjList.computeIfAbsent(to, k -> new ArrayList<>()).add(new Edge(from, weight));
        vertices.add(from);
        vertices.add(to);
    }

    public Set<String> getVertices() {
        return vertices;
    }

    public Map<String, Integer> dijkstra(String start) {
        Map<String, Integer> dist = new HashMap<>();
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(n -> n.dist));

        for (String v : adjList.keySet()) {
            dist.put(v, Integer.MAX_VALUE);
        }
        dist.put(start, 0);
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node current = pq.poll();

            for (Edge edge : adjList.get(current.name)) {
                int newDist = dist.get(current.name) + edge.weight;
                if (newDist < dist.get(edge.to)) {
                    dist.put(edge.to, newDist);
                    pq.offer(new Node(edge.to, newDist));
                }
            }
        }

        return dist;
    }

    public Map<String, Map<String, Integer>> floydWarshall() {
        Map<String, Map<String, Integer>> dist = new HashMap<>();

        for (String u : vertices) {
            dist.put(u, new HashMap<>());
            for (String v : vertices) {
                if (u.equals(v)) dist.get(u).put(v, 0);
                else dist.get(u).put(v, Integer.MAX_VALUE);
            }
        }

        for (String u : adjList.keySet()) {
            for (Edge e : adjList.get(u)) {
                dist.get(u).put(e.to, e.weight);
            }
        }

        for (String k : vertices) {
            for (String i : vertices) {
                for (String j : vertices) {
                    if (dist.get(i).get(k) != Integer.MAX_VALUE && dist.get(k).get(j) != Integer.MAX_VALUE) {
                        int newDist = dist.get(i).get(k) + dist.get(k).get(j);
                        if (newDist < dist.get(i).get(j)) {
                            dist.get(i).put(j, newDist);
                        }
                    }
                }
            }
        }

        return dist;
    }

    public Map<String, Map<String, Integer>> johnson() {
        // Para simplificação, chamamos diretamente o Floyd-Warshall.
        // Em grafos não direcionados e sem pesos negativos, Floyd-Warshall já resolve.
        return floydWarshall();
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

    private static class Edge {
        String to;
        int weight;
        Edge(String to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    private static class Node {
        String name;
        int dist;
        Node(String name, int dist) {
            this.name = name;
            this.dist = dist;
        }
    }
}
