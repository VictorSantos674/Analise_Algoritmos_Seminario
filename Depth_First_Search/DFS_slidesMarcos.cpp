#include <iostream>
#include <vector>

using namespace std;

const int BRANCO = -1;
const int CINZA = 0;
const int PRETO = 1;

vector<int> cor;
vector<int> i;
vector<int> f;
vector<int> ante;
int tempo = 1;

void DFS_VISIT(vector<vector<int>>& grafo, int u) {
    cor[u] = CINZA;
    i[u] = tempo++;

    for (int v : grafo[u]) {
        if (cor[v] == BRANCO) {
            ante[v] = u;
            DFS_VISIT(grafo, v);
        }
    }

    cor[u] = PRETO;
    f[u] = tempo++;
}

void DFS_START(vector<vector<int>>& grafo, int s) {
    cor.assign(grafo.size(), BRANCO);
    i.assign(grafo.size(), -2);
    f.assign(grafo.size(), -2);
    ante.assign(grafo.size(), -2);
    tempo = 1;

    DFS_VISIT(grafo, s);
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

    // Executando o algoritmo DFS começando a partir do vértice 0
    DFS_START(grafo, 0);

    // Exibindo os resultados
    cout << "Resultado do algoritmo DFS:" << endl;
    cout << "Vertice:   Cor:    Inicio:    Fim:    Antecessor:" << endl;
    for (int v = 0; v < grafo.size(); ++v) {
        cout << v << "          " << cor[v] << "       " << i[v] << "         " << f[v] << "        " << ante[v] << endl;
    }

    return 0;
}