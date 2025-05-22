package Breadth_First_Search;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BFS_geek4geek {
    // Function to perform Breadth First Search on a graph
    // represented using adjacency list
    static void bfs(ArrayList<ArrayList<Integer>> adjList, int startNode,
                    boolean[] visited) {
        // Create a queue for BFS
        Queue<Integer> q = new LinkedList<>();

        // Mark the current node as visited and enqueue it
        visited[startNode] = true;
        q.add(startNode);

        // Iterate over the queue
        while (!q.isEmpty()) {
            // Dequeue a vertex from queue and print it
            int currentNode = q.poll();
            System.out.print(currentNode + " ");

            // Get all adjacent vertices of the dequeued vertex
            // currentNode If an adjacent has not been visited,
            // then mark it visited and enqueue it
            for (int neighbor : adjList.get(currentNode)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    q.add(neighbor);
                }
            }
        }
    }

    // Function to add an edge to the graph
    static void addEdge(ArrayList<ArrayList<Integer>> adjList, int u, int v) {
        adjList.get(u).add(v);
    }

    public static void main(String[] args) {
        // Number of vertices in the graph
        int vertices = 5;

        // Adjacency list representation of the graph
        ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < vertices; i++) {
            adjList.add(new ArrayList<>());
        }

        // Add edges to the graph
        addEdge(adjList, 0, 1);
        addEdge(adjList, 0, 2);
        addEdge(adjList, 1, 3);
        addEdge(adjList, 1, 4);
        addEdge(adjList, 2, 4);

        // Mark all the vertices as not visited
        boolean[] visited = new boolean[vertices];

        // Perform BFS traversal starting from vertex 0
        System.out.print("Breadth First Traversal starting from vertex 0: ");
        bfs(adjList, 0, visited);
    }
}