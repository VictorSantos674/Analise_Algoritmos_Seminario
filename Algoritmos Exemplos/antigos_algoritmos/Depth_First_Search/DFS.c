#include <stdio.h>

// Função para realizar a busca em profundidade
void dfs(int grafo[][5], int vertice, int visitado[]) {
    // Marcar o vértice atual como visitado e imprimir
    visitado[vertice] = 1;
    printf("%d ", vertice);

    // Percorrer todos os vértices adjacentes ao vértice atual
    for (int i = 0; i < 5; i++) {
        // Se o vértice adjacente não foi visitado e há uma aresta entre eles
        if (!visitado[i] && grafo[vertice][i] == 1) {
            // Chamada recursiva para visitar o vértice adjacente
            dfs(grafo, i, visitado);
        }
    }
}

// Função principal
int main() {
    int grafo[5][5] = {
        {0, 1, 0, 0, 1},
        {1, 0, 1, 1, 1},
        {0, 1, 0, 1, 0},
        {0, 1, 1, 0, 1},
        {1, 1, 0, 1, 0}
    };
    int visitado[5] = {0}; // Array para marcar os vértices visitados

    printf("DFS traversal starting from vertex 0:\n");
    dfs(grafo, 0, visitado); // Chamar a função DFS com o vértice inicial 0

    return 0;
}