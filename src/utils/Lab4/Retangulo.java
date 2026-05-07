/**
 * @author a90143 Bruno Simao
 * @version 1 - lab4 23-03-2026
 */

package utils.Lab4;

import utils.*;

import java.util.ArrayList;

public class Retangulo extends Poligono {

    public Retangulo(Ponto[] vert){
        if (vert.length != 4) {
            IO.println("Retangulo:iv");
            System.exit(0);
        }
        double side1 = vert[0].dist(vert[1]);
        double side2 = vert[1].dist(vert[2]);
        double side3 = vert[2].dist(vert[3]);
        double side4 = vert[3].dist(vert[0]);
        double diag1 = vert[0].dist(vert[2]);
        double diag2 = vert[1].dist(vert[3]);

        boolean oppositeSidesEqual = Math.abs(side1 - side3) < 1e-9 && Math.abs(side2 - side4) < 1e-9;
        boolean diagonalsEqual     = Math.abs(diag1 - diag2) < 1e-9;

        if (!oppositeSidesEqual || !diagonalsEqual) {
            IO.println("Retangulo:iv");
            System.exit(0);
        }
        super(vert);
    }

    /**
     * Calls Poligono function to find intersecting points, since a rectangle is a polygon;
     * @param r inputted route
     * @return intersecting points;
     */
    public Ponto[] findIntersection(Route r){
        return super.findIntersection(r);
    }

}
