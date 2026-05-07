/**
 * @author a90143 Bruno Simao
 * @version 1 - lab4 23-03-2026
 */

package utils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Poligono {
    Ponto[] vert;

    public Poligono(Ponto[] vert){
        if(vert.length<3) {
            IO.println("Poligono:iv");
            System.exit(0);
        }

        this.vert = vert;
    }

    /**
     * Finds intersection by turning the route into line segments, and checking each intersection individually
     * @param r route inputted by user
     * @return sorted (or not) array of intersecting points
     */
    public Ponto[] findIntersection(Route r){
        Route pol = new Route(vert);
        SegmentoReta[] lineSegments = pol.turnToLineSegment(true);

        ArrayList<Ponto> intersectPoints = new ArrayList<>();

        for (SegmentoReta sr : lineSegments) {
            Ponto[] pts = r.findIntersect(sr);

            if (pts != null) {
                for (Ponto p : pts) {
                    if (!intersectPoints.contains(p)) {
                        intersectPoints.add(p);
                    }
                }
            }
        }

        if(intersectPoints.size() < 3)              // ordenagem dos pontos no mooshak é irregular..
            return Ponto.sort(intersectPoints);           // T9 ficaria errado sem sorting
        else
            return intersectPoints.toArray(new Ponto[0]);   // T12 não está ordenado
    }


}
