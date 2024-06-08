package Dijkstra;

import java.util.LinkedList;
import java.util.Queue;

// Uma queue ou fila, é uma estrutura de dados linear que segue o princípio FIFO (First In,
// First Out). Sobre a implementação dela em Java, temos a biblioteca Java util 
// que apresenta a classe Queue.

public class Fila {
    public static void main(String[] args) {
        Queue<Integer> fila = new LinkedList<>();
        fila.add(1);
        fila.add(2);
        fila.add(3);
        while (!fila.isEmpty()) {
            System.out.println(fila.poll());
        }
    }
}
