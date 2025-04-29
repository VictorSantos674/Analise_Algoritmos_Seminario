package Dijkstra;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

// Uma fila de prioridade, é um tipo de fila em que podemos determinar uma condição
// de prioridade. No Java essa prioridade é por padrão ascendente, mas podemos
// usar uma outra classe e formar um comparador personalizado.

public class FilaDePrioridade {
    public static void main(String[] args) {
        Queue<Integer> filaDePrioridade = new PriorityQueue<>(Collections.reverseOrder());
        filaDePrioridade.add(2);
        filaDePrioridade.add(3);
        filaDePrioridade.add(1);
        while (!filaDePrioridade.isEmpty()) {
            System.out.println(filaDePrioridade.poll());
        }
    }
}