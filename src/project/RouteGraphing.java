package project;

import utils.Ponto;
import utils.Route;
import java.util.List;
/*
    Will handle the graphing of the routes self-explanatory
 */
public class RouteGraphing {
    private List<Route> routes;
    // private ports[4]

    public RouteGraphing(List<Route> routes, List<Port> ports) {
        this.routes = routes;
    }

    /*
        We need to decide on a pathing algortihm maybe Dijkastra or wtv tf one spells that
     */
    public List<Route> findPath(Port A, Port B){

        return null;
    }



    public boolean interceptsMovingObstacle(Route r, MovingObstacle ob1, MovingObstacle ob2){
        if(r.intersectsFigura(ob1).size()>1 || r.intersectsFigura(ob2).size()>1){
            return true;
        }
        return false;
    }
}
