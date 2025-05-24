package Dijkstra;

import java.util.*;

public class Dijkstra {
    private final Map<String, Map<String, Integer>> grafo;

    public Dijkstra() {
        this.grafo = new HashMap<>();
    }

    public void addEdge(String origem, String destino, int peso) {
        grafo.computeIfAbsent(origem, k -> new HashMap<>()).put(destino, peso);
        grafo.computeIfAbsent(destino, k -> new HashMap<>()).put(origem, peso);
    }

    public void calcularMenorCaminho(String origem, String destinoFinal) {
        Map<String, Integer> dist = new HashMap<>();
        Map<String, String> anterior = new HashMap<>();
        for (String cidade : grafo.keySet()) {
            dist.put(cidade, Integer.MAX_VALUE);
            anterior.put(cidade, null);
        }

        dist.put(origem, 0);
        PriorityQueue<String> fila = new PriorityQueue<>(Comparator.comparingInt(dist::get));
        fila.add(origem);

        long inicio = System.nanoTime();

        while (!fila.isEmpty()) {
            String atual = fila.poll();

            for (Map.Entry<String, Integer> vizinho : grafo.getOrDefault(atual, Collections.emptyMap()).entrySet()) {
                String cidadeVizinha = vizinho.getKey();
                int peso = vizinho.getValue();

                int novaDist = dist.get(atual) + peso;
                if (novaDist < dist.get(cidadeVizinha)) {
                    dist.put(cidadeVizinha, novaDist);
                    anterior.put(cidadeVizinha, atual);
                    fila.add(cidadeVizinha);
                }
            }
        }

        long fim = System.nanoTime();
        double tempoMs = (fim - inicio) / 1e6;

        // Reconstrói o caminho
        List<String> caminho = new ArrayList<>();
        for (String at = destinoFinal; at != null; at = anterior.get(at)) {
            caminho.add(at);
        }
        Collections.reverse(caminho);

        // Saída
        System.out.println("\n--- Algoritmo de Dijkstra ---");
        System.out.printf("Tempo de execução: %.3f ms\n", tempoMs);
        System.out.println("Distância total de " + origem + " até " + destinoFinal + ": " +
                (dist.get(destinoFinal) == Integer.MAX_VALUE ? "∞" : dist.get(destinoFinal)));

        System.out.print("Caminho: ");
        for (int i = 0; i < caminho.size(); i++) {
            System.out.print(caminho.get(i));
            if (i < caminho.size() - 1) System.out.print(" -> ");
        }
        System.out.println();
    }

    public Set<String> getVertices() {
        return grafo.keySet();
    }
}
