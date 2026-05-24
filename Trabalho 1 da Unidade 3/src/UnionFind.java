// Estrutura Union-Find (Disjoint Set Union) para verificar ciclos
class UnionFind {
    int[] parent;
    int[] rank;

    public UnionFind(int n) {
        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i; // Inicialmente, cada ilha é seu próprio pai
        }
    }

    // Encontra a raiz do conjunto com Otimização de Compressão de Caminho
    public int find(int i) {
        if (parent[i] == i) {
            return i;
        }
        return parent[i] = find(parent[i]);
    }

    // Une dois conjuntos com Otimização de União por Rank
    public boolean union(int i, int j) {
        int rootI = find(i);
        int rootJ = find(j);

        if (rootI != rootJ) {
            if (rank[rootI] < rank[rootJ]) {
                parent[rootI] = rootJ;
            } else if (rank[rootI] > rank[rootJ]) {
                parent[rootJ] = rootI;
            } else {
                parent[rootJ] = rootI;
                rank[rootI]++;
            }
            return true; // União feita com sucesso (não formou ciclo)
        }
        return false; // Já estavam no mesmo conjunto (formaria ciclo)
    }
}