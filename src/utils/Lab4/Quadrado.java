/**
 * @author a90143 Bruno Simao
 * @version 1 - lab4 23-03-2026
 */
package utils.Lab4;

import utils.*;

import java.util.ArrayList;
import java.util.Arrays;

public class Quadrado extends Poligono{

    public Quadrado(Ponto[] vert){
        if(     vert[0].dist(vert[1]) != vert[0].dist(vert[2]) &&
                vert[0].dist(vert[2]) != vert[0].dist(vert[3]) &&
                vert[0].dist(vert[3]) != vert[0].dist(vert[1])){
            IO.println("Quadrado:iv");
            System.exit(0);
        }
        super(vert);
    }

    /**
     * Calls Poligono function to find intersecting points, since a square is a polygon;
     * @param r inputted route
     * @return intersecting points;
     */
    public Ponto[] findIntersection(Route r){
        return super.findIntersection(r);
    }

}
