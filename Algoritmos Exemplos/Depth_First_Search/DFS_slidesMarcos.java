package Depth_First_Search;

import java.util.ArrayList;

public class DFS_slidesMarcos {
    static final int BRANCO = -1;
    static final int CINZA = 0;
    static final int PRETO = 1;

    static ArrayList<Integer> cor;
    static ArrayList<Integer> i;
    static ArrayList<Integer> f;
    static ArrayList<Integer> ante;
    static int tempo = 1;

    static void DFS_VISIT(ArrayList<ArrayList<Integer>> grafo, int u) {
        cor.set(u, CINZA);
        i.set(u, tempo++);

        for (int v : grafo.get(u)) {
            if (cor.get(v) == BRANCO) {
                ante.set(v, u);
                DFS_VISIT(grafo, v);
            }
        }

        cor.set(u, PRETO);
        f.set(u, tempo++);
    }

    static void DFS_START(ArrayList<ArrayList<Integer>> grafo, int s) {
        cor = new ArrayList<>(grafo.size());
        i = new ArrayList<>(grafo.size());
        f = new ArrayList<>(grafo.size());
        ante = new ArrayList<>(grafo.size());
        tempo = 1;

        for (int j = 0; j < grafo.size(); j++) {
            cor.add(BRANCO);
            i.add(-2);
            f.add(-2);
            ante.add(-2);
        }

        DFS_VISIT(grafo, s);
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

        // Executando o algoritmo DFS começando a partir do vértice 0
        DFS_START(grafo, 0);

        // Exibindo os resultados
        System.out.println("Resultado do algoritmo DFS:");
        System.out.println("Vertice:   Cor:    Inicio:    Fim:    Antecessor:");
        for (int v = 0; v < grafo.size(); ++v) {
            System.out.println(v + "          " + cor.get(v) + "       " + i.get(v) + "         " + f.get(v) + "        " + ante.get(v));
        }
    }
}