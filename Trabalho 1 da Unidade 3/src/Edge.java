// Classe para representar a ponte entre duas ilhas (Aresta do Grafo)
class Edge implements Comparable<Edge> {
    int u, v; // Índices das ilhas
    double weight; // Distância Euclidiana

    public Edge(int u, int v, double weight) {
        this.u = u;
        this.v = v;
        this.weight = weight;
    }

    // Usado para ordenar as arestas pelo menor peso
    @Override
    public int compareTo(Edge other) {
        return Double.compare(this.weight, other.weight);
    }
}