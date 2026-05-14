/**
 * @author a90143 Bruno Simao
 * @version 1 - lab4 23-03-2026
 */

package utils;

import java.util.ArrayList;

public class Route {
    private Ponto[] pts;

    /**
     * Route constructor
     * @param rota  Array of points representing a route
     */
    public Route(Ponto[] rota){
        this.pts =rota;
    }

    /**
     * Points getter
     * @return array of points (Ponto[])
     */
    public Ponto[] getPoints(){
        return pts;
    }

    /**
     * Function that returns the total distance of a route
     * @return  sum of distance between each point
     */
    public double findDistance(){
        double res=0;
        for(int i = 0; i< pts.length-1; i++){
            res+= pts[i].dist(pts[i+1]);
        }
        return res;
    }

    public SegmentoReta[] turnToLineSegment(boolean closed){
        int size = closed ? pts.length : pts.length - 1;
        SegmentoReta[] segments = new SegmentoReta[size];
        for(int i = 0; i < pts.length - 1; i++)
            segments[i] = new SegmentoReta(pts[i], pts[i+1]);
        if(closed)
            segments[pts.length-1] = new SegmentoReta(pts[pts.length-1], pts[0]);

        return segments;
    }

    /**
     * Function that finds intersecting points between a route and a line segment
     * @param sr    Line segment that will be compared with to find for intersections
     * @return      Returns an array of intersecting Pontos
     */
    public Ponto[] findIntersect(SegmentoReta sr){
        ArrayList<Ponto> intersectPoints = new ArrayList<>();
        SegmentoReta[] connectingPoints = turnToLineSegment(false);
        for(SegmentoReta n : connectingPoints) {
            if(sr.intersect(n) != null) {
                intersectPoints.add(sr.intersect(n));
            }
        }

        if(intersectPoints.isEmpty()){
            return null;
        }
        return intersectPoints.toArray(new Ponto[0]);
    }

}
