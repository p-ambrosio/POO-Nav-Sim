package utils;

import java.util.ArrayList;
import java.util.List;
/**
 * A class Route representa uma rota composta por varios pontos, rota a qual é formada
 * por segmentos de reta entre pontos consecutivos.
 *
 * @author Pedro Ambrósio, nº88589
 * @version 2.0 [6/04/26]
 */
public class Route{
    private final List<Ponto> pontos;

    /**
     * Creates a route with the points
     * @param pontos the list of the points within the route
     */
    public Route(List<Ponto> pontos){
        this.pontos = new ArrayList<>(pontos);
    }

    /**
     * Basic getter that helps access route for the inheritance
     * @return a copy of the points of the route
     */
    public List<Ponto> getPoints() {return new ArrayList<>(pontos); }
    /**
     * Calcula o comprimento total somando os pontos dentro da rota
     * @return O comprimento total da rota
     */
    public double Length(){
        double length = 0; //starter value
        for (int i = 0;i <pontos.size()-1;i++ ){
            length+= pontos.get(i).CalcDistance(pontos.get(i+1));
        }
        return length;
    }

    /**
     * Função que retorna o ponto de interseção da rota com o segmento de reta
     * @param sr segemento de reta
     * @return Retorna o ponto ou null caso não intersect
     */
    public List<Ponto> Intersect_Route(SegmentoReta sr){
        List<Ponto> intersecoes = new ArrayList<>();
        for(int i = 0; i < pontos.size()-1; i++){
            SegmentoReta section = new SegmentoReta(pontos.get(i), pontos.get(i+1));
            Ponto p = section.Intersect(sr);
            if(p != null){
                intersecoes.add(p);
            }
        }
        return intersecoes;
    }

    /**
     * Verifica se um ponto já existe na lista (evita duplicados por vértices partilhados).
     * @param lista lista de pontos já encontrados
     * @param p ponto a verificar
     * @return true se já existe um ponto suficientemente próximo
     */
    public boolean isDuplicate(List<Ponto> lista, Ponto p) {
        for (Ponto existing : lista) {
            if (Math.abs(existing.getX() - p.getX()) < 1e-6
                    && Math.abs(existing.getY() - p.getY()) < 1e-6) {
                return true;
            }
        }
        return false;
    }

    /**
     * Retorna os pontos de interseção da rota com uma figura geométrica (obstáculo),
     * percorrendo todas as arestas da figura. Para o Circulo, delega diretamente
     * no metodo intersects() da figura pois não tem arestas.
     * @param figura figura geométrica que representa o obstáculo
     * @return lista de pontos de interseção ordenados desde o início da rota, ou null
     */
    public List<Ponto> intersectsFigura(Figura figura) {
        if (figura instanceof Circulo) {
            return intersectsCirculo((Circulo) figura);
        }

        List<Ponto> intersecoes = new ArrayList<>();
        SegmentoReta[] arestas = figura.getArestas();
        for (int i = 0; i < pontos.size() - 1; i++) {
            SegmentoReta segRota = new SegmentoReta(pontos.get(i), pontos.get(i + 1));
            for (SegmentoReta aresta : arestas) {
                Ponto p = segRota.Intersect(aresta);
                if (p != null && !isDuplicate(intersecoes, p)) {
                    intersecoes.add(p);
                }
            }
        }
        return intersecoes;
    }

    /**
     * Calcula os pontos de interseção entre a rota e um círculo usando a
     * equação quadrática da distância ponto-segmento ao centro.
     * @param circulo círculo a testar
     * @return lista de pontos de interseção, ou null se não houver
     * @see "https://en.wikipedia.org/wiki/Line–sphere_intersection"
     */
    private List<Ponto> intersectsCirculo(Circulo circulo) {
        List<Ponto> intersecoes = new ArrayList<>();
        Ponto centro = circulo.getCentro();
        double raio  = circulo.getRaio();

        for (int i = 0; i < pontos.size() - 1; i++) {
            Ponto p1 = pontos.get(i);
            Ponto p2 = pontos.get(i + 1);

            double dx = p2.getX() - p1.getX();
            double dy = p2.getY() - p1.getY();
            double fx = p1.getX() - centro.getX();
            double fy = p1.getY() - centro.getY();

            double a = dx * dx + dy * dy;
            double b = 2 * (fx * dx + fy * dy);
            double c = fx * fx + fy * fy - raio * raio;

            double disc = b * b - 4 * a * c;
            if (disc < 0) continue;

            double sqrtDisc = Math.sqrt(disc);
            double t1 = (-b - sqrtDisc) / (2 * a);
            double t2 = (-b + sqrtDisc) / (2 * a);

            if (t1 >= 0 && t1 <= 1) {
                intersecoes.add(new Ponto(p1.getX() + t1 * dx, p1.getY() + t1 * dy));
            }
            if (disc > 1e-10 && t2 >= 0 && t2 <= 1) {
                intersecoes.add(new Ponto(p1.getX() + t2 * dx, p1.getY() + t2 * dy));
            }
        }
        return intersecoes;
    }
}