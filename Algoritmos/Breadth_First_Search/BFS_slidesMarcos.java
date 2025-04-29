package Breadth_First_Search;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BFS_slidesMarcos {
    static final int BRANCO = -1;
    static final int CINZA = 0;
    static final int PRETO = 1;

    static ArrayList<Integer> cor;
    static ArrayList<Integer> distancia;
    static ArrayList<Integer> ante;
    static Queue<Integer> fila;

    static void BFS(ArrayList<ArrayList<Integer>> grafo, int s) {
        cor = new ArrayList<>(grafo.size());
        distancia = new ArrayList<>(grafo.size());
        ante = new ArrayList<>(grafo.size());
        fila = new LinkedList<>();

        for (int i = 0; i < grafo.size(); i++) {
            cor.add(BRANCO);
            distancia.add(-1);
            ante.add(-1);
        }

        cor.set(s, CINZA);
        distancia.set(s, 0);
        fila.add(s);

        while (!fila.isEmpty()) {
            int u = fila.poll();

            for (int v : grafo.get(u)) {
                if (cor.get(v) == BRANCO) {
                    cor.set(v, CINZA);
                    distancia.set(v, distancia.get(u) + 1);
                    ante.set(v, u);
                    fila.add(v);
                }
            }

            cor.set(u, PRETO);
        }
    }

    public static void main(String[] args) {
        // Exemplo de grafo (lista de adjacência)
        ArrayList<ArrayList<Integer>> grafo = new ArrayList<>();
        grafo.add(new ArrayList<>() {{ add(1); add(4); }});
        grafo.add(new ArrayList<>() {{ add(0); add(2); add(4); }});
        grafo.add(new ArrayList<>() {{ add(1); add(3); }});
        grafo.add(new ArrayList<>() {{ add(2); add(4); add(5); }});
        grafo.add(new ArrayList<>() {{ add(0); add(1); add(3); }});
        grafo.add(new ArrayList<>() {{ add(3); }});

        // Executando o algoritmo BFS começando a partir do vértice 0
        BFS(grafo, 0);

        // Exibindo os resultados
        System.out.println("Resultado do algoritmo BFS:");
        System.out.println("Vertice:   Cor:    Distancia:    Antecessor:");
        for (int v = 0; v < grafo.size(); ++v) {
            System.out.println(v + "          " + cor.get(v) + "       " + distancia.get(v) + "         " + ante.get(v));
        }
    }
}