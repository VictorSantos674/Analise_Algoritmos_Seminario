package Analise_Algoritmos_Seminario;

import java.util.Scanner;
import Dijkstra.Dijkstra;
import Floyd_Warshall.FloydWarshall;
import Johnson.Johnson;

public class Main {
    public static void main(String[] args) {
        Grafo romenia = new Grafo();
        romenia.inicializarGrafo();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Algoritmos de Caminho Mínimo ===");
            System.out.println("1. Dijkstra");
            System.out.println("2. Floyd-Warshall");
            System.out.println("3. Johnson");
            System.out.println("4. Mostrar grafo");
            System.out.println("5. Sair");
            System.out.print("Escolha: ");
            int op;
            try {
                op = Integer.parseInt(sc.nextLine().trim());
            } catch (Exception e) {
                System.out.println("Opção inválida.");
                continue;
            }
            if (op == 5) {
                System.out.println("Encerrando...");
                break;
            }
            if (op == 4) {
                romenia.printGraph();
                continue;
            }
            if (op < 1 || op > 5) {
                System.out.println("Opção inválida.");
                continue;
            }

            System.out.print("Origem: ");
            String origem = sc.nextLine().trim();
            System.out.print("Destino: ");
            String destino = sc.nextLine().trim();

            if (!romenia.getVertices().contains(origem) ||
                !romenia.getVertices().contains(destino)) {
                System.out.println("Vértice não encontrado no grafo.");
                continue;
            }

            switch (op) {
                case 1:
                    new Dijkstra(romenia.getAdjacencyMap()).calcularMenorCaminho(origem, destino);
                    break;
                case 2:
                    new FloydWarshall(romenia.getAdjacencyMap()).calcularMenorCaminho(origem, destino);
                    break;
                case 3:
                    try {
                        new Johnson(romenia.getAdjacencyMap()).calcularMenorCaminho(origem, destino);
                    } catch (RuntimeException ex) {
                        System.out.println("Erro: " + ex.getMessage());
                    }
                    break;
            }
        }
        sc.close();
    }
}
