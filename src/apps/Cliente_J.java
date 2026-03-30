package apps;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Classe responsável pela leitura dos dados de entrada Lê uma rota e um obstáculo geométrico e determina
 * se a rota intersecta o obstáculo, imprimindo os pontos de interseção ou "null".
 *
 * @author Pedro Ambrósio, nº88589
 * @version 5.0 [23/03/26]
 */
public class Cliente {

    /**
     * Metodo principal do programa.
     * @param args argumentos de linha de comandos
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String linhaRota = sc.nextLine().trim();
        String[] tokensRota = linhaRota.split("\\s+");
        List<Ponto> pontos = new ArrayList<>();
        for (int i = 0; i + 1 < tokensRota.length; i += 2) {
            double x = Double.parseDouble(tokensRota[i]);
            double y = Double.parseDouble(tokensRota[i + 1]);
            pontos.add(new Ponto(x, y));
        }
        Route rota = new Route(pontos);
        String linhaFigura = sc.nextLine().trim();
        String[] tokens = linhaFigura.split("\\s+");
        String tipo = tokens[0];

        Figura figura = parseFigura(tipo, tokens);
        List<Ponto> intersecoes = rota.intersectsFigura(figura);

        if (intersecoes == null || intersecoes.isEmpty()) {
            System.out.println("null");
        } else {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < intersecoes.size(); i++) {
                if (i > 0) sb.append(" ");
                sb.append(String.format("(%.2f,%.2f)",
                        intersecoes.get(i).getX(),
                        intersecoes.get(i).getY()));
            }
            System.out.println(sb.toString());
        }
    }

    /**
     * Constrói a figura geométrica a partir do tipo e dos tokens de entrada.
     * Termina o programa com mensagem de erro se o tipo for desconhecido.
     * @param tipo letra identificadora da figura: P, T, R, S ou C
     * @param tokens array completo de tokens da linha
     * @return a figura geométrica construída
     */
    private static Figura parseFigura(String tipo, String[] tokens) {
        return switch (tipo) {
            case "P" -> parsePoligono(tokens);
            case "T" -> parseTriangulo(tokens);
            case "R" -> parseRetangulo(tokens);
            case "S" -> parseQuadrado(tokens);
            case "C" -> parseCirculo(tokens);
            default -> null;
        };
    }

    /**
     * Constrói um Poligono a partir dos tokens (P x1 y1 x2 y2 ...).
     * @param tokens tokens da linha de entrada
     * @return Poligono construído
     */
    private static Poligno parsePoligono(String[] tokens) {
        Ponto[] vertices = lerVertices(tokens, 1);
        return new Poligno(vertices);
    }

    /**
     * Constrói um Triangulo a partir dos tokens (T x1 y1 x2 y2 x3 y3).
     * @param tokens tokens da linha de entrada
     * @return Triangulo construído
     */
    private static Triangulo parseTriangulo(String[] tokens) {
        Ponto[] v = lerVertices(tokens, 1);
        return new Triangulo(v[0], v[1], v[2]);
    }

    /**
     * Constrói um Retangulo a partir dos tokens (R x1 y1 x2 y2 x3 y3 x4 y4).
     * @param tokens tokens da linha de entrada
     * @return Retangulo construído
     */
    private static Retangulo parseRetangulo(String[] tokens) {
        Ponto[] v = lerVertices(tokens, 1);
        return new Retangulo(v[0], v[1], v[2], v[3]);
    }

    /**
     * Constrói um Quadrado a partir dos tokens (S x1 y1 x2 y2 x3 y3 x4 y4).
     * @param tokens tokens da linha de entrada
     * @return Quadrado construído
     */
    private static Quadrado parseQuadrado(String[] tokens) {
        Ponto[] v = lerVertices(tokens, 1);
        return new Quadrado(v[0], v[1], v[2], v[3]);
    }

    /**
     * Constrói um Circulo a partir dos tokens (C cx cy raio).
     * @param tokens tokens da linha de entrada
     * @return Circulo construído
     */
    private static Circulo parseCirculo(String[] tokens) {
        double cx   = Double.parseDouble(tokens[1]);
        double cy   = Double.parseDouble(tokens[2]);
        double raio = Double.parseDouble(tokens[3]);
        return new Circulo(new Ponto(cx, cy), raio);
    }

    /**
     * Lê pares de coordenadas a partir de uma posição inicial no array de tokens
     * e devolve um array de Ponto.
     * @param tokens array de tokens
     * @param inicio índice a partir do qual começar a ler (salta o tipo)
     * @return array de Ponto com os vértices lidos
     */
    private static Ponto[] lerVertices(String[] tokens, int inicio) {
        int n = (tokens.length - inicio) / 2;
        Ponto[] vertices = new Ponto[n];
        for (int i = 0; i < n; i++) {
            double x = Double.parseDouble(tokens[inicio + i * 2]);
            double y = Double.parseDouble(tokens[inicio + i * 2 + 1]);
            vertices[i] = new Ponto(x, y);
        }
        return vertices;
    }
}
