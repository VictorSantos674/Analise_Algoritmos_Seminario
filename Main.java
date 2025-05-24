package Analise_Algoritmos_Seminario;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Grafo romania = new Grafo();

        romania.addEdge("Oradea", "Zerind", 71);
        romania.addEdge("Zerind", "Arad", 75);
        romania.addEdge("Oradea", "Sibiu", 151);
        romania.addEdge("Arad", "Sibiu", 140);
        romania.addEdge("Arad", "Timisoara", 118);
        romania.addEdge("Timisoara", "Lugoj", 111);
        romania.addEdge("Lugoj", "Mehadia", 70);
        romania.addEdge("Mehadia", "Drobeta", 75);
        romania.addEdge("Drobeta", "Craiova", 120);
        romania.addEdge("Craiova", "Rimnicu Vilcea", 146);
        romania.addEdge("Craiova", "Pitesti", 138);
        romania.addEdge("Rimnicu Vilcea", "Sibiu", 80);
        romania.addEdge("Rimnicu Vilcea", "Pitesti", 97);
        romania.addEdge("Sibiu", "Fagaras", 99);
        romania.addEdge("Fagaras", "Bucharest", 211);
        romania.addEdge("Pitesti", "Bucharest", 101);
        romania.addEdge("Bucharest", "Giurgiu", 90);
        romania.addEdge("Bucharest", "Urziceni", 85);
        romania.addEdge("Urziceni", "Hirsova", 98);
        romania.addEdge("Hirsova", "Eforie", 86);
        romania.addEdge("Urziceni", "Vaslui", 142);
        romania.addEdge("Vaslui", "Iasi", 92);
        romania.addEdge("Iasi", "Neamt", 87);

        while (true) {
            System.out.print("Digite a cidade de origem: ");
            String origem = scanner.nextLine();
            System.out.print("Digite a cidade de destino: ");
            String destino = scanner.nextLine();

            if (!romania.getVertices().contains(origem)) {
                System.out.println("Cidade de origem não existe no grafo. Tente novamente.");
                continue;
            }
            if (!romania.getVertices().contains(destino)) {
                System.out.println("Cidade de destino não existe no grafo. Tente novamente.");
                continue;
            }

            System.out.println("\n=== MENU ===");
            System.out.println("1 - Dijkstra");
            System.out.println("2 - Floyd-Warshall");
            System.out.println("3 - Johnson");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

              switch (choice) {
                case 1: {
                    Map<String, Integer> distances = romania.dijkstra(origem);
                    int dist = distances.getOrDefault(destino, Integer.MAX_VALUE);
                    System.out.println("Distância de " + origem + " até " + destino + ": " + 
                        (dist == Integer.MAX_VALUE ? "∞" : dist));
                    break;
                }
                case 2: {
                    Map<String, Map<String, Integer>> distFW = romania.floydWarshall();
                    int dist = distFW.getOrDefault(origem, Collections.emptyMap())
                                     .getOrDefault(destino, Integer.MAX_VALUE);
                    System.out.println("Distância de " + origem + " até " + destino + " (Floyd-Warshall): " +
                        (dist == Integer.MAX_VALUE ? "∞" : dist));
                    break;
                }
                case 3: {
                    Map<String, Map<String, Integer>> distJ = romania.johnson();
                    int dist = distJ.getOrDefault(origem, Collections.emptyMap())
                                    .getOrDefault(destino, Integer.MAX_VALUE);
                    System.out.println("Distância de " + origem + " até " + destino + " (Johnson): " +
                        (dist == Integer.MAX_VALUE ? "∞" : dist));
                    break;
                }
                case 0: {
                    System.out.println("Saindo...");
                    scanner.close();
                    return;
                }
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
}
