/**
 * @author a90143 Bruno Simao
 * @version 1 - lab4 23-03-2026
 */
package utils.Lab4;

import utils.*;

import java.util.ArrayList;

public class Triangulo extends Poligono {

    public Triangulo(Ponto[] vert){
        if (vert.length != 3) {
            IO.println("Triangulo:iv");
            System.exit(0);
        }

        double area = (vert[1].getX() - vert[0].getX()) * (vert[2].getY() - vert[0].getY())
                - (vert[2].getX() - vert[0].getX()) * (vert[1].getY() - vert[0].getY());

        if (area == 0) {
            IO.println("Triangulo:iv");
            System.exit(0);
        }


        super(vert);
    }

    /**
     * Calls Poligono function to find intersecting points, since a triangle is a polygon;
     * @param r inputted route
     * @return intersecting points;
     */
    public Ponto[] findIntersection(Route r){
        return super.findIntersection(r);
    }
}
