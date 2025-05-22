package Depth_First_Search;

import java.util.*;

class Graph {
    private Map<Integer, Boolean> visited = new HashMap<>();
    private Map<Integer, List<Integer>> adj = new HashMap<>();

    // Function to add an edge to graph
    void addEdge(int v, int w) {
        if (!adj.containsKey(v)) {
            adj.put(v, new LinkedList<>());
        }
        adj.get(v).add(w);
    }

    // DFS traversal of the vertices
    // reachable from v
    void DFS(int v) {
        visited.put(v, true);
        System.out.print(v + " ");

        List<Integer> neighbors = adj.get(v);
        if (neighbors != null) {
            for (int neighbor : neighbors) {
                if (!visited.containsKey(neighbor) || !visited.get(neighbor)) {
                    DFS(neighbor);
                }
            }
        }
    }
}

public class DFS_geek4geek {
    public static void main(String[] args) {
        // Create a graph given in the above diagram
        Graph g = new Graph();
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 3);

        System.out.println("Following is Depth First Traversal (starting from vertex 2)");
        g.DFS(2);
    }
}