package project;

import utils.*;

import java.util.*;
/**
 * Nó de um grafo de rotas, associando uma chave textual a uma posição geográfica
 * e mantendo as ligações (arestas com peso) aos nós vizinhos.
 *
 * @author Aashma Pandey-88430, Bruno Simão-80143, Pedro Ambrósio-88589.
 * @version 1.0 - 2025-05-14
 * @inv Os valores e vizinhos não podem ser nulos
 */
class RouteNode {

    String key;
    Ponto value;
    double distance;
    boolean isBlocked;
    Map<RouteNode, Double> neighbours = new HashMap<>();
    /**
     * Constrói um nó de rota com a chave e posição fornecidas.
     * @param key   identificador textual do nó
     * @param value posição geográfica do nó
     */
    RouteNode(String key, Ponto value){
        this.key=key;
        this.value = value;
        distance=0;
        isBlocked=false;
    }

    /**
     * Adiciona um único nó vizinho, calculando automaticamente o peso da aresta
     * como a distância euclidiana entre as duas posições.
     *
     * @param neighbour o nó vizinho a adicionar
     */
    public void addNeighbour(RouteNode neighbour){
        neighbours.put(neighbour, value.dist(neighbour.getValue()));
    }

    /**
     * Adiciona um array de nós vizinhos, calculando o peso de cada aresta
     * como a distância euclidiana entre as posições.
     *
     * @param neighbour array de nós vizinhos a adicionar.
     */
    public void addNeighbour(RouteNode[] neighbour){
        for(RouteNode n : neighbour){
            neighbours.put(n,value.dist(n.getValue()));
        }
    }

    /**
     * Indica se este nó está bloqueado por um obstáculo.
     *
     * @return true se o nó está bloqueado; false caso contrário
     */
    public boolean isBlocked() {
        return isBlocked;
    }

    /**
     * Devolve a chave textual identificadora do nó.
     *
     * @return a chave do nó como String
     */
    public String getKey() {
        return this.key;
    }

    /**
     * Devolve a posição geográfica associada ao nó.
     *
     * @return o Ponto que representa a posição do nó
     */
    public Ponto getValue() {
        return this.value;
    }

    /**
     * Devolve o mapa de vizinhos do nó com os respetivos pesos das arestas.
     *
     * @return um Map de RouteNode para double com os vizinhos e pesos
     */
    public Map<RouteNode, Double> getNeighbours() {
        return this.neighbours;
    }

}
/**
 * Grafo que representa a rede de rotas marítimas entre os portos e nós intermédios
 * da simulação. Suporta pesquisa exaustiva (DFS) e pesquisa de caminho mínimo (Dijkstra).
 * Um para mapear as rotas para demonstração gráfica e outro para encontrar o
 * caminho mais curto
 *
 * @author Aashma Pandey-88430, Bruno Simão-80143, Pedro Ambrósio-88589.
 * @version 1.0 - 2025-05-14
 * @inv O grafo contém sempre os quatro portos fixos (A, B, C, D) e o conjunto de
 *      nós de rota é imutável após construção. Nós bloqueados não são considerados
 *      em pesquisas de caminho mais curto.
 */
class Graph{
    private final static Port A=new Port("A",new Ponto(0,0));
    private final static Port B=new Port("B",new Ponto(0,800));
    private final static Port C=new Port("C",new Ponto(800,0));
    private final static Port D=new Port("D",new Ponto(800,800));

    private final static Port[] ports = {A,B,C,D};
    private final Set<RouteNode> routeNodes = new HashSet<>();
    private final Map<Integer,RouteNode> graph = new HashMap<>();
    /**
     * Constrói o grafo de rotas, inicializando todos os nós e as suas ligações.
     */
    public Graph(){
        RouteNode[] nodes = {
                new RouteNode("A",A.getPosition()),         //0
                new RouteNode("B",B.getPosition()),         //1
                new RouteNode("C",C.getPosition()),         //2
                new RouteNode("D",D.getPosition()),         //3
                new RouteNode("E",new Ponto(640,800)),//4
                new RouteNode("F",new Ponto(480,600)),//5
                new RouteNode("G",new Ponto(270,600)),//6
                new RouteNode("H", new Ponto(0,600)), //7
                new RouteNode("I",new Ponto(100,600)),//8
                new RouteNode("J",new Ponto(340,370)),//9
                new RouteNode("K",new Ponto(600,0)),  //10
                new RouteNode("L",new Ponto(800,200)),//11
                new RouteNode("M",new Ponto(580,300)),//12
                new RouteNode("N",new Ponto(480,400)),//13
                new RouteNode("O",new Ponto(120,190)),//14
                new RouteNode("P",new Ponto(200,0)),  //15
                new RouteNode("Q", new Ponto(406,251))//16
        };
        int i=0;
        nodes[0].addNeighbour(new RouteNode[]{nodes[14], nodes[15]});
        nodes[1].addNeighbour(new RouteNode[]{nodes[7], nodes[6]});
        nodes[2].addNeighbour(new RouteNode[]{nodes[11], nodes[10]});
        nodes[3].addNeighbour(new RouteNode[]{nodes[4], nodes[13]});
        nodes[4].addNeighbour(new RouteNode[]{nodes[5], nodes[3]});
        nodes[5].addNeighbour(new RouteNode[]{nodes[4], nodes[6]});
        nodes[6].addNeighbour(new RouteNode[]{nodes[5], nodes[1],nodes[8],nodes[13]});
        nodes[7].addNeighbour(new RouteNode[]{nodes[1], nodes[8]});
        nodes[8].addNeighbour(new RouteNode[]{nodes[7], nodes[9],nodes[6]});
        nodes[9].addNeighbour(new RouteNode[]{nodes[8], nodes[13],nodes[14],nodes[16]});
        nodes[10].addNeighbour(new RouteNode[]{nodes[16], nodes[2]});
        nodes[11].addNeighbour(new RouteNode[]{nodes[12], nodes[2]});
        nodes[12].addNeighbour(new RouteNode[]{nodes[11], nodes[13],nodes[16]});
        nodes[13].addNeighbour(new RouteNode[]{nodes[3], nodes[6],nodes[12],nodes[9]});
        nodes[14].addNeighbour(new RouteNode[]{nodes[9], nodes[0]});
        nodes[15].addNeighbour(new RouteNode[]{nodes[0], nodes[16]});
        nodes[16].addNeighbour(new RouteNode[]{nodes[15], nodes[12],nodes[9],nodes[10]});

        routeNodes.addAll(List.of(nodes));
        for(RouteNode rn : routeNodes){
            graph.put(rn.hashCode(),rn);
        }

    }

    /**
     * Inicia uma pesquisa DFS e devolve todos os caminhos possíveis entre dois nós.
     *
     * @param src  nó de origem
     * @param dest nó de destino
     * @return lista com todos os caminhos possíveis entre os dois nós
     */
    public List<Route> getAllPaths(RouteNode src, RouteNode dest) {
        List<Route> allRoutes = new ArrayList<>();
        List<Ponto> currentPath = new ArrayList<>();
        Set<RouteNode> visited = new HashSet<>();

        dfs(src, dest, visited, currentPath, allRoutes);
        return allRoutes;
    }

    /**
     * Pesquisa em profundidade (DFS) recursiva que explora todos os caminhos simples
     * entre o nó atual e o destino, registando cada caminho completo encontrado.
     *
     * @param current nó atualmente a ser explorado
     * @param dest nó de destino da pesquisa
     * @param visited conjunto de nós já visitados no caminho atual
     * @param currentPath lista de posições que formam o caminho atual
     * @param allRoutes lista acumuladora de todas as rotas completas encontradas
     */
    private void dfs(RouteNode current, RouteNode dest, Set<RouteNode> visited, List<Ponto> currentPath, List<Route> allRoutes) {
        visited.add(current);
        currentPath.add(current.getValue());

        if (current == dest) {
            allRoutes.add(new Route(currentPath.toArray(new Ponto[0])));
        } else {
            for (RouteNode neighbour : current.getNeighbours().keySet()) {
                if (!visited.contains(neighbour)) {
                    dfs(neighbour, dest, visited, currentPath, allRoutes);
                }
            }
        }

        // backtrack
        visited.remove(current);
        currentPath.removeLast();
    }

    /**
     * Obtém todos os caminhos possíveis entre cada par distinto de portos,
     * combinando os resultados de DFS para todas as combinações.
     *
     * @return lista com todos os caminhos entre todos os pares de portos
     */
    public List<Route> getAllPortPaths() {
        List<Route> allRoutes = new ArrayList<>();
        Port[] ports = getPorts();

        for (int i = 0; i < ports.length; i++) {
            for (int j = 0; j < ports.length; j++) {
                if (i != j) {
                    RouteNode src = getRouteNode(ports[i].getName());
                    RouteNode dest = getRouteNode(ports[j].getName());
                    allRoutes.addAll(getAllPaths(src, dest));
                }
            }
        }

        return allRoutes;
    }
    /**
     * Implementação do algoritmo de Dijkstra que devolve a sequência de hashes dos nós
     * no caminho de custo mínimo entre origem e destino, ignorando nós bloqueados.
     *
     * @param src  nó de origem
     * @param dest nó de destino
     * @return lista de inteiros representando os hashes dos nós no caminho mínimo
     * @see "https://labex.io/tutorials/java-how-to-find-the-shortest-path-between-nodes-in-a-java-graph-414028"
     */
    public List<Integer> f(RouteNode src, RouteNode dest) {
        Map<Integer, Integer> distances = new HashMap<>();
        Map<Integer, Integer> previous = new HashMap<>();
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));

        for (RouteNode node : routeNodes) {
            if(!node.isBlocked)
                distances.put(node.hashCode(), Integer.MAX_VALUE);
        }

        distances.put(src.hashCode(), 0);
        pq.offer(new int[]{src.hashCode(), 0});

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int currentNode = current[0];
            int currentDistance = current[1];

            // destination
            if (currentNode == dest.hashCode()) {
                break;
            }

            // current distance > recorded distance? skip
            if (currentDistance > distances.get(currentNode)) {
                continue;
            }

            // check neighbours
            for (Map.Entry<RouteNode, Double> neighbour :graph.get(currentNode).getNeighbours().entrySet()) {
                if(!neighbour.getKey().isBlocked) {
                    int neighborNode = neighbour.getKey().hashCode();
                    int weight = neighbour.getValue().intValue();
                    int totalDistance = currentDistance + weight;

                    if (totalDistance < distances.get(neighborNode)) {
                        distances.put(neighborNode, totalDistance);
                        previous.put(neighborNode, currentNode);            // parent
                        pq.offer(new int[]{neighborNode, totalDistance});
                    }
                }
            }
        }
        List<Integer> path = new ArrayList<>();
        Integer current = dest.hashCode();

        while (current != null) {
            path.add(current);
            current = previous.get(current);
        }

        // reverse to get src -> dest
        Collections.reverse(path);
        return path;
    }
    /**
     * Determina o caminho de custo mínimo entre dois nós usando Dijkstra e
     * converte a sequência de hashes numa lista de posições.
     * @param src  nó de origem
     * @param dest nó de destino
     * @return a lista que representa o caminho mínimo entre os dois nós
     */
    public Route findPath(RouteNode src, RouteNode dest){
        Route r;
        List<Integer> route = f(src,dest);
        List<Ponto> pontos = new ArrayList<>();
        for(Integer n : route){

            pontos.add(graph.get(n).getValue());
        }

        r = new Route(pontos.toArray(new Ponto[0]));
        return r;
    }

    /**
     *Procura e devolve o nó de rota com a chave textual fornecida.
     * @param key a chave textual do nó a localizar
     * @return  o nó com a chave correspondente, ou null se não encontrado
     */
    protected RouteNode getRouteNode(String key){
        for(RouteNode n : routeNodes){
            if(n.getKey().equals(key)){
                return n;
            }
        }
        return null;
    }

    /**
     *  Procura e retorna um porto com o indice especificado do array de portos
     * @param i indice do porto
     * @return o porto no indice, ou se falhar null.
     */
    public Port getPort(int i){
        if(i<ports.length && i>=0)
            return ports[i];
        return null;
    }

    /**
     *  Procura e retorna um porto com o nome especificado
     * @param name nome do port
     * @return o porto correspondente, ou se falhar null.
     */
    public Port getPort(String name){
        for(Port p : ports){
            if(p.getName().equals(name)){
                return p;
            }
        }
        return null;
    }

    /**
     * Marca como bloqueado o nó de rota cuja posição coincide com o ponto fornecido.
     * @param node a posição do nó a bloquear.
     */
    public void setBlocked(Ponto node){
        for(RouteNode nodes : routeNodes){
            if(nodes.getValue().equals(node)){
                nodes.isBlocked=true;
            }
        }
    }

    /**
     * Imprime no output as chaves de todos os nós atualmente bloqueados.
     */
    public void blockedNodes(){
        for(RouteNode nodes : routeNodes){
            if(nodes.isBlocked){
                IO.println(nodes.getKey());
            }
        }
    }

    /**
     * Devolve o array com todos os portos disponíveis na simulação.
     * @return um array com os quatro portos.
     */
    public Port[] getPorts(){
        return ports;
    }
}

/**
 * Componente principal de gestão do grafo de rotas, obstáculos estáticos e móveis
 * da simulação. Integra o grafo de nós com os obstáculos e expõe uma interface
 *  simplificada para pesquisa de caminhos e acesso a portos.
 *
 *  @author Aashma Pandey-88430, Bruno Simão-80143, Pedro Ambrósio-88589.
 *  @version 1.0 - 2025-05-14
 *  @inv O grafo é sempre inicializado com dois obstáculos móveis posicionados e
 *  os seus nós correspondentes bloqueados. Os obstáculos estáticos são fixos
 *  durante toda a simulação.
 */
public class RouteGraphing {
    Graph graph = new Graph();
    MovingObstacle mo1;
    MovingObstacle mo2;
    Poligono[] staticObstacle = {
            new Poligono(new Ponto[]{new Ponto(150,480),new Ponto(60,290), new Ponto(200,340)}),
            new Poligono(new Ponto[]{new Ponto(370,790),new Ponto(460,740),new Ponto(350,640)}),
            new Poligono(new Ponto[]{new Ponto(760,580),new Ponto(610,460),new Ponto(770,360)}),
            new Poligono(new Ponto[]{new Ponto(360,160),new Ponto(510,30),new Ponto(290,80)}),
    };

    /**
     *Constrói o gestor de rotas, posicionando os obstáculos móveis e bloqueando
     *os nós correspondentes no grafo para a simulação atual.
     */
    public RouteGraphing(){
        mo1 = new MovingObstacle(new Ponto(0,0),100);
        mo2 = new MovingObstacle(new Ponto(0,0),100);

        mo1.positioning(0);
        mo2.positioning(1);

        graph.setBlocked(mo1.getPosition());
        graph.setBlocked(mo2.getPosition());
    }
    /**
     * Determina o caminho de custo mínimo entre dois portos, evitando nós bloqueados.
     *
     * @param beginning porto de origem
     * @param end porto de destino
     * @return o caminho mínimo entre os dois portos
     */
    public Route findPath(Port beginning, Port end){
        return graph.findPath(graph.getRouteNode(beginning.getName()),graph.getRouteNode(end.getName()));
    }

    /**
     * Devolve o porto no índice especificado.
     *
     * @param i índice do porto;
     * @return o porto no índice , ou null se inválido
     */
    public Port getPort(int i) {
        return graph.getPort(i);
    }

    /**
     * Procura e devolve o porto com o nome especificado.
     *
     * @param name o nome do porto a localizar
     * @return o Porto com o nome correspondente, ou null se não encontrado
     */
    public Port getPort(String name) {
        return graph.getPort(name);
    }

    /**
     * Devolve o array com todos os portos disponíveis na simulação.
     *
     * @return array com os portos do grafo
     */
    public Port[] getPorts() {
        return graph.getPorts();
    }

    /**
     * Devolve os dois obstáculos móveis da simulação atual.
     *
     * @return array com mo1 e mo2
     */
    public MovingObstacle[] getMovingObstacle() {
        return new MovingObstacle[]{mo1, mo2};
    }

    /**
     * Devolve o array de obstáculos estáticos da simulação.
     *
     * @return array com os quatro obstáculos estáticos
     */
    public Poligono[] getStaticObstacle() {
        return staticObstacle;
    }

    /**
     * Devolve todos os caminhos possíveis entre todos os pares de portos do grafo.
     *
     * @return lista com todos os caminhos entre todos os pares de portos
     */
    public List<Route> getRoutes() {
        return graph.getAllPortPaths();
    }
}