package utils;

/**
 * Classe abstrata que representa uma figura geométrica no plano bidimensional.
 * As subclasses (exceto Circulo) são definidas por uma lista ordenada de vértices
 * no sentido dos ponteiros do relógio. Dois vértices consecutivos formam uma aresta,
 * sendo a última aresta ligada pelo último ao primeiro vértice.
 *
 * @author Pedro Ambrósio, nº88589
 * @version 1.0 [23/03/26]
 */
public abstract class Figura {
    private final Ponto[] vertices;

    /**
     * Contrutor chamado pelas sub
     * @param vertices vertices lista ordenada de vértices
     */
    protected Figura(Ponto[] vertices) {
        this.vertices = vertices;
    }

    /**
     * Getter de vertices
     * @return Lista de vertices
     */
    public Ponto[] getVertices() {
        return vertices;
    }

    /**
     * Getter de arestas
     * @return Devolve as arestas da figura
     */
    public SegmentoReta[] getArestas(){
        SegmentoReta[] arestas = new SegmentoReta[vertices.length];
        for (int i = 0; i < vertices.length; i++) {
            Ponto p1 = vertices[i];
            Ponto p2 = vertices[(i + 1) % vertices.length];
            arestas[i] = new SegmentoReta(p1, p2);
        }
        return arestas;
    }

    /**
     * Verifica se ocorre uma interseção
     * @param sr segemento de reta
     * @return true or false
     */
    public boolean intersects(SegmentoReta sr) {
        for (SegmentoReta aresta : getArestas()) {
            if (aresta.Intersect(sr) != null) {
                return true;
            }
        }
        return false;
    }

    /**
     * Calcula a area da Figura, implementado pelas sub
     * @return area da figura
     */
    public abstract double Area();

}
