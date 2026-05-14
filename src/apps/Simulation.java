package apps;

import project.*;
import utils.Vetor;

/*
    Will act as our main
 */
public class Simulation {
    static void main() {
        RouteGraphing test = new RouteGraphing();
        IO.print("");           // load bearing print
        Ship ship = new Ship(test.getPort("A"),test.getPort("C"),1,0);
        while(!ship.hasArrived()){
            ship.movement(2);
            IO.println(ship.getPosition());
        }
    }
}
