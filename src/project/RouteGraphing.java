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
    Map<RouteNode, Double> neighbours = new HashMap<>();

    RouteNode(String key, Ponto value){
        this.key=key;
        this.value = value;
        distance=0;
    }

    public void addNeighbour(RouteNode neighbour){
        neighbours.put(neighbour, value.dist(neighbour.getValue()));
    }

    public void addNeighbour(RouteNode[] neighbour){
        for(RouteNode n : neighbour){
            neighbours.put(n,value.dist(n.getValue()));
        }
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
    private final static Port C=new Port("C",new Ponto(800,800));
    private final static Port D=new Port("D",new Ponto(800,0));

    private final static Port[] ports = {A,B,C,D};
    private static final Set<RouteNode> routeNodes = new HashSet<>();

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
    }
//    public List<String> shortestPath(String start, String end) {
//        Map<String, Integer> distances = new HashMap<>();
//        Map<String, String> previous = new HashMap<>();
//        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(node -> node.distance));
//
//        for (String node : adjList.keySet()) {
//            distances.put(node, Integer.MAX_VALUE);
//            previous.put(node, null);
//        }
//
//        distances.put(start, 0);
//        queue.add(new Node(start, 0));
//
//        while (!queue.isEmpty()) {
//            Node current = queue.poll();
//
//            if (current.name.equals(end)) {
//                return constructPath(previous, end);
//            }
//
//            for (Edge edge : adjList.get(current.name)) {
//                int newDist = distances.get(current.name) + edge.weight;
//
//                if (newDist < distances.get(edge.destination)) {
//                    distances.put(edge.destination, newDist);
//                    previous.put(edge.destination, current.name);
//                    queue.add(new Node(edge.destination, newDist));
//                }
//            }
//        }
//
//        return Collections.emptyList();
//    }

    static void setDistances(RouteNode src){
        for(RouteNode adj : src.neighbours.keySet()){
            adj.distance=src.neighbours.get(adj);
            setDistances(adj);
        }
    }

    public static void findPaths(RouteNode src, RouteNode dest,Stack<RouteNode> connectionPath, List<Stack<RouteNode>> connectionPaths) {

        for (RouteNode nextNode : src.getNeighbours().keySet()) {
            if (nextNode.getValue().equals(dest.getValue())) {
                Stack<RouteNode> temp = new Stack<>();
                temp.addAll(connectionPath);
                connectionPaths.add(temp);
            } else if (!connectionPath.contains(nextNode)) {
                connectionPath.push(nextNode);
                findPaths(nextNode, dest,connectionPath,connectionPaths);
                connectionPath.pop();
            }
        }

    }

    public static void f(RouteNode src, RouteNode dest){

        Stack<RouteNode> connectionPath = new Stack<>();

        List<Stack<RouteNode>> connectionPaths = new ArrayList<>();
        findPaths(src,dest,connectionPath,connectionPaths);

        List<Route> routes=new ArrayList<>();
        List<Ponto> pontos=new ArrayList<>();
        for(Stack<RouteNode> srn : connectionPaths){                    // not working and im killing myself bc of it
            RouteNode[] routeNs = srn.toArray(new RouteNode[0]);
            for(RouteNode pts : routeNs){
                pontos.add(pts.getValue());
            }
            routes.add(new Route(pontos.toArray(new Ponto[0])));
        }
        double minDist = Double.MAX_VALUE;
        int i=0;
        for(Route r : routes){
            if(r.findDistance() < minDist){
                minDist=r.findDistance();
            }
            i++;
        }
        Route[] routesArray = routes.toArray(new Route[0]);
        SegmentoReta[] sr = routesArray[i-1].turnToLineSegment(false);
        for(SegmentoReta srs : sr){
            IO.println(srs);
        }
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
}

public class RouteGraphing {
    Graph graph = new Graph();
    public RouteGraphing(){
         findShortestPath(getPort("A"),getPort("D"));

    }
    public void findShortestPath(Port beginning, Port end){
        Graph.f(graph.getRouteNode(beginning.getName()),graph.getRouteNode(end.getName()));
    }
    public Port getPort(int i){
        return graph.getPort(i);
    }

    public Port getPort(String name){
        return graph.getPort(name);
    }
}
