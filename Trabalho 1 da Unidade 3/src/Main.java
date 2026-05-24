import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        sc.useLocale(Locale.US); // Garante a leitura e escrita com ponto decimal

        if (!sc.hasNextInt()) {
            sc.close();
            return;
        }

        int numCasos = sc.nextInt();

        for (int caso = 0; caso < numCasos; caso++) {
            int n = sc.nextInt();
            Point[] ilhas = new Point[n];

            // 1. Lê as coordenadas das ilhas
            for (int i = 0; i < n; i++) {
                ilhas[i] = new Point(sc.nextDouble(), sc.nextDouble());
            }

            // 2. Cria todas as arestas possíveis (Grafo Completo)
            List<Edge> arestas = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    // Cálculo da distância Euclidiana (Pitágoras)
                    double dx = ilhas[i].x - ilhas[j].x;
                    double dy = ilhas[i].y - ilhas[j].y;
                    double distancia = Math.sqrt(dx * dx + dy * dy);

                    arestas.add(new Edge(i, j, distancia));
                }
            }

            // 3. Ordena as arestas do menor peso para o maior (Passo crucial do Kruskal)
            Collections.sort(arestas);

            // 4. Aplica o Algoritmo de Kruskal
            UnionFind uf = new UnionFind(n);
            double custoTotalMST = 0.0;
            int arestasAdicionadas = 0;

            for (Edge aresta : arestas) {
                // Se as ilhas u e v ainda não estão conectadas
                if (uf.union(aresta.u, aresta.v)) {
                    custoTotalMST += aresta.weight;
                    arestasAdicionadas++;

                    // Uma Árvore Geradora Mínima sempre terá exatamente (V - 1) arestas
                    if (arestasAdicionadas == n - 1) {
                        break;
                    }
                }
            }

            // 5. Imprime o resultado formatado com 3 casas decimais
            System.out.printf(Locale.US, "%.3f\n", custoTotalMST);
        }

        sc.close();
    }
}