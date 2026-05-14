package apps;

import project.*;
import utils.*;

import java.util.*;

/*
    Will act as our main
 */
public class Simulation {

    private static final List<Ship> ships = new ArrayList<>();
    static int[] arrived = {0,0};

    static void main() {
        Simulation s = new Simulation();

        while(arrived[0] != 1 || arrived[1] != 1)
            s.update(2);
    }

    public Simulation() {
        RouteGraphing graph = new RouteGraphing();
        Ship ship = new Ship(graph.getPort("A"), graph.getPort("D"),1,0);
        Ship ship2 = new Ship(graph.getPort("B"), graph.getPort("C"),1,10);

        ships.add(ship);
        ships.add(ship2);
    }

    public void update(double dt) {
        int i=0;
        for(Ship ship : ships){
            ship.update(dt,ships);

            IO.println("Ship "+i+"- "+ship.getPosition()+"; Has arrived: "+ship.hasArrived()+"; is Waiting: "+ship.isWaiting());

            if(ship.hasArrived())       // scuffed way of telling program to stop.. but probably genuinely works fine
                arrived[i]=1;

            //gui update here probs

            i++;
        }
    }


}
