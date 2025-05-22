package Depth_First_Search;

public class DFS {

    // Função para realizar a busca em profundidade
    public static void dfs(int[][] grafo, int inicio) {
        // Array para marcar os vértices visitados
        boolean[] visitado = new boolean[grafo.length];
        // Chamada recursiva para o método auxiliar dfsUtil
        dfsUtil(grafo, inicio, visitado);
    }

    // Método auxiliar para a busca em profundidade
    private static void dfsUtil(int[][] grafo, int vertice, boolean[] visitado) {
        // Marcar o vértice atual como visitado e imprimir
        visitado[vertice] = true;
        System.out.print(vertice + " ");

        // Percorrer todos os vértices adjacentes ao vértice atual
        for (int i = 0; i < grafo.length; i++) {
            // Se o vértice adjacente não foi visitado e há uma aresta entre eles
            if (!visitado[i] && grafo[vertice][i] == 1) {
                // Chamada recursiva para visitar o vértice adjacente
                dfsUtil(grafo, i, visitado);
            }
        }
    }

    // Método principal
    public static void main(String[] args) {
        int[][] grafo = {
            {0, 1, 0, 0, 1},
            {1, 0, 1, 1, 1},
            {0, 1, 0, 1, 0},
            {0, 1, 1, 0, 1},
            {1, 1, 0, 1, 0}
        };
        // Chamar a função DFS com o vértice inicial 0
        System.out.println("DFS traversal starting from vertex 0:");
        dfs(grafo, 0);
    }
}