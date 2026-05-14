package project;

import utils.*;

import java.util.*;
/*
    Will handle the graphing of the routes self-explanatory
 */

class RouteNode {

    String key;
    Ponto value;
    double distance;
    boolean isBlocked;
    Map<RouteNode, Double> neighbours = new HashMap<>();

    RouteNode(String key, Ponto value){
        this.key=key;
        this.value = value;
        distance=0;
        isBlocked=false;
    }

    public void addNeighbour(RouteNode neighbour){
        neighbours.put(neighbour, value.dist(neighbour.getValue()));
    }

    public void addNeighbour(RouteNode[] neighbour){
        for(RouteNode n : neighbour){
            neighbours.put(n,value.dist(n.getValue()));
        }
    }

    public boolean isBlocked(){
        return isBlocked;
    }

    public String getKey(){
        return this.key;
    }

    public Ponto getValue(){
        return this.value;
    }

    public Map<RouteNode,Double> getNeighbours(){
        return this.neighbours;
    }

}

class Graph{
    private final static Port A=new Port("A",new Ponto(0,0));
    private final static Port B=new Port("B",new Ponto(0,800));
    private final static Port C=new Port("C",new Ponto(800,0));
    private final static Port D=new Port("D",new Ponto(800,800));

    private final static Port[] ports = {A,B,C,D};
    private final Set<RouteNode> routeNodes = new HashSet<>();
    private final Map<Integer,RouteNode> graph = new HashMap<>();

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

    public Route findPath(RouteNode src, RouteNode dest){
        Route r;
        List<Integer> route = f(src,dest);
        List<Ponto> pontos = new ArrayList<>();
        for(Integer n : route){
//            IO.println(graph.get(n).getKey());
            pontos.add(graph.get(n).getValue());
        }

        r = new Route(pontos.toArray(new Ponto[0]));
        return r;
    }

    protected RouteNode getRouteNode(String key){
        for(RouteNode n : routeNodes){
            if(n.getKey().equals(key)){
                return n;
            }
        }
        return null;
    }

    public Port getPort(int i){
        if(i<ports.length && i>=0)
            return ports[i];
        return null;
    }

    public Port getPort(String name){
        for(Port p : ports){
            if(p.getName().equals(name)){
                return p;
            }
        }
        return null;
    }

    public void setBlocked(Ponto node){
        for(RouteNode nodes : routeNodes){
            if(nodes.getValue().equals(node)){
                nodes.isBlocked=true;
            }
        }
    }

    public void blockedNodes(){
        for(RouteNode nodes : routeNodes){
            if(nodes.isBlocked){
                IO.println(nodes.getKey());
            }
        }
    }
}

public class RouteGraphing {
    Graph graph = new Graph();
    Poligono[] staticObstacle = {
            new Poligono(new Ponto[]{new Ponto(150,480),new Ponto(60,290), new Ponto(200,340)}),
            new Poligono(new Ponto[]{new Ponto(370,790),new Ponto(460,740),new Ponto(350,640)}),
            new Poligono(new Ponto[]{new Ponto(760,580),new Ponto(610,460),new Ponto(770,360)}),
            new Poligono(new Ponto[]{new Ponto(360,160),new Ponto(510,30),new Ponto(290,80)}),
    };


    public RouteGraphing(){
        MovingObstacle mo1 = new MovingObstacle(new Ponto(0,0),1);
        MovingObstacle mo2 = new MovingObstacle(new Ponto(0,0),1);

        mo1.positioning(0);
        mo2.positioning(1);

        graph.setBlocked(mo1.getPosition());
        graph.setBlocked(mo2.getPosition());
    }

    public Route findPath(Port beginning, Port end){
        return graph.findPath(graph.getRouteNode(beginning.getName()),graph.getRouteNode(end.getName()));
    }
    public Port getPort(int i){
        return graph.getPort(i);
    }

    public Port getPort(String name){
        return graph.getPort(name);
    }

    public Poligono[] getStaticObstacle(){
        return staticObstacle;
    }

}