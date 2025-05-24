package Johnson;

import java.util.*;
import Dijkstra.Dijkstra;

public class Johnson {
    private final Map<String, Map<String, Integer>> grafo;

    public Johnson() {
        this.grafo = new HashMap<>();
    }

    public void addEdge(String origem, String destino, int peso) {
        grafo.computeIfAbsent(origem, k -> new HashMap<>()).put(destino, peso);
    }

    private boolean bellmanFord(Map<String, Integer> h) {
        for (String cidade : grafo.keySet()) {
            h.put(cidade, Integer.MAX_VALUE);
        }
        String ficticia = "__ficticia__";
        h.put(ficticia, 0);

        List<Aresta> arestas = new ArrayList<>();
        for (String cidade : grafo.keySet()) {
            arestas.add(new Aresta(ficticia, cidade, 0));
            for (Map.Entry<String, Integer> entrada : grafo.get(cidade).entrySet()) {
                arestas.add(new Aresta(cidade, entrada.getKey(), entrada.getValue()));
            }
        }

        int V = h.size();

        for (int i = 1; i < V; i++) {
            for (Aresta e : arestas) {
                if (h.get(e.origem) != Integer.MAX_VALUE &&
                        h.get(e.origem) + e.peso < h.get(e.destino)) {
                    h.put(e.destino, h.get(e.origem) + e.peso);
                }
            }
        }

        for (Aresta e : arestas) {
            if (h.get(e.origem) != Integer.MAX_VALUE &&
                    h.get(e.origem) + e.peso < h.get(e.destino)) {
                return false;
            }
        }

        return true;
    }

    public void calcularMenorCaminho(String origem, String destino) {
        Map<String, Integer> h = new HashMap<>();

        long inicio = System.nanoTime();

        if (!bellmanFord(h)) {
            System.out.println("Ciclo negativo detectado.");
            return;
        }

        Map<String, Map<String, Integer>> grafoRecalculado = new HashMap<>();
        for (String u : grafo.keySet()) {
            for (Map.Entry<String, Integer> entrada : grafo.get(u).entrySet()) {
                String v = entrada.getKey();
                int pesoOriginal = entrada.getValue();
                int novoPeso = pesoOriginal + h.get(u) - h.get(v);
                grafoRecalculado
                        .computeIfAbsent(u, k -> new HashMap<>())
                        .put(v, novoPeso);
            }
        }

        long fim = System.nanoTime();
        double tempoMs = (fim - inicio) / 1e6;

        System.out.println("\n--- Algoritmo de Johnson ---");
        System.out.printf("Tempo de execução: %.3f ms\n", tempoMs);

        Dijkstra dijkstra = new Dijkstra();
        for (String u : grafoRecalculado.keySet()) {
            for (Map.Entry<String, Integer> entrada : grafoRecalculado.get(u).entrySet()) {
                dijkstra.addEdge(u, entrada.getKey(), entrada.getValue());
            }
        }

        dijkstra.calcularMenorCaminho(origem, destino);
    }

    public Set<String> getVertices() {
        return grafo.keySet();
    }

    private static class Aresta {
        String origem, destino;
        int peso;

        Aresta(String o, String d, int p) {
            origem = o;
            destino = d;
            peso = p;
        }
    }
}
