#include <stdio.h>
#include <stdbool.h>

// Função para realizar a busca em largura
void bfs(int grafo[][5], int inicio) {
    // Array para marcar os vértices visitados
    bool visitado[5] = {false};
    // Fila para armazenar os vértices a serem visitados
    int fila[5], frente = 0, fim = 0;
    
    // Marcar o vértice inicial como visitado e adicioná-lo à fila
    visitado[inicio] = true;
    fila[fim++] = inicio;

    // Enquanto a fila não estiver vazia
    while (frente != fim) {
        // Remover o vértice da frente da fila e imprimir
        int vertice = fila[frente++];
        printf("%d ", vertice);

        // Percorrer todos os vértices adjacentes ao vértice atual
        for (int i = 0; i < 5; i++) {
            // Se o vértice adjacente não foi visitado e há uma aresta entre eles
            if (!visitado[i] && grafo[vertice][i] == 1) {
                // Marcar o vértice adjacente como visitado e adicioná-lo à fila
                visitado[i] = true;
                fila[fim++] = i;
            }
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

    printf("BFS traversal starting from vertex 0:\n");
    bfs(grafo, 0); // Chamar a função BFS com o vértice inicial 0

    return 0;
}