package project;

import utils.Ponto;
import utils.Route;
import java.util.List;
/*
    Will handle the graphing of the routes self-explanatory
 */
public class RouteGraphing {
    private final static List<Route> routes ={new Route(),new Route(),new Route()};
    private final static List<Port> ports ={new Port(),new Port(),new Port(),new Port()};
    private Port portToFind;

    public RouteGraphing() {
    }

    /*
        We need to decide on a pathing algortihm maybe Dijkastra or wtv tf one spells that
     */
    public List<Route> findPath(Port beginning, Port end){
        // find route that goes from beginning to portToFind
        return null;
    }

    public Port getPort(int i){
        return ports.get(i);
    }

    public Route getRoute(int i){
        return routes.get(i);
    }

    public boolean interceptsMovingObstacle(Route r, MovingObstacle ob1, MovingObstacle ob2){
        if(r.intersectsFigura(ob1).size()>1 || r.intersectsFigura(ob2).size()>1){
            return true;
        }
        return false;
    }
}
