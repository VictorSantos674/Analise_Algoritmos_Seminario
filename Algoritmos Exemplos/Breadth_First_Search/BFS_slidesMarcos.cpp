#include <iostream>
#include <vector>
#include <queue>

using namespace std;

const int BRANCO = -1;
const int CINZA = 0;
const int PRETO = 1;

vector<int> cor;
vector<int> distancia;
vector<int> ante;
queue<int> fila;

void BFS(vector<vector<int>>& grafo, int s) {
    cor.assign(grafo.size(), BRANCO);
    distancia.assign(grafo.size(), -1);
    ante.assign(grafo.size(), -1);

    cor[s] = CINZA;
    distancia[s] = 0;
    fila.push(s);

    while (!fila.empty()) {
        int u = fila.front();
        fila.pop();

        for (int v : grafo[u]) {
            if (cor[v] == BRANCO) {
                cor[v] = CINZA;
                distancia[v] = distancia[u] + 1;
                ante[v] = u;
                fila.push(v);
            }
        }

        cor[u] = PRETO;
    }
}

int main() {
    // Exemplo de grafo (lista de adjacência)
    vector<vector<int>> grafo = {
        {1, 4},     // vértice 0
        {0, 2, 4},  // vértice 1
        {1, 3},     // vértice 2
        {2, 4, 5},  // vértice 3
        {0, 1, 3},  // vértice 4
        {3}         // vértice 5
    };

    // Executando o algoritmo BFS começando a partir do vértice 0
    BFS(grafo, 0);

    // Exibindo os resultados
    cout << "Resultado do algoritmo BFS:" << endl;
    cout << "Vertice:   Cor:    Distancia:    Antecessor:" << endl;
    for (int v = 0; v < grafo.size(); ++v) {
        cout << v << "          " << cor[v] << "       " << distancia[v] << "         " << ante[v] << endl;
    }

    return 0;
}