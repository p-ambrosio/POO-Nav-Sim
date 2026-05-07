/**
 * @author a90143 Bruno Simao
 * @version 1 - lab4 23-03-2026
 */
package utils;

import java.util.ArrayList;

public class Circulo {
    private double radius;
    private Ponto center;

    public Circulo(Ponto center, double rad){
        if(rad <= 0){
            IO.println("Circulo:iv");
            System.exit(0);
        }
        radius = rad;
        this.center = center;
    }
    private int sgn(double x){
        return x < 0 ? -1 : 1;
    }

    /**
     * calculates intersecting points using math equation, taking into account the circle's center in the vector space
     * @see <https://mathworld.wolfram.com/Circle-LineIntersection.html>
     * @param r route
     * @return intersecting points sorted
     */
    public Ponto[] findIntersection(Route r) {
        SegmentoReta[] lineSegments = r.turnToLineSegment(false);
        ArrayList<Ponto> intersectPoints = new ArrayList<>();

        double cx = center.getX();
        double cy = center.getY();
        for (SegmentoReta sr : lineSegments) {
            Vetor A = new Vetor(sr.getP());
            Vetor B = sr.getV();

            double x1 = A.getX() - cx;
            double y1 = A.getY() - cy;
            double x2 = B.getX() - cx;
            double y2 = B.getY() - cy;

            double dx = x2 - x1;
            double dy = y2 - y1;

            double dr2 = dx * dx + dy * dy;
            double D = x1 * y2 - x2 * y1;

            double discriminant = radius * radius * dr2 - D * D;

            if (discriminant < 0) {
                continue;
            }
            // if it passed , disc = 0 || disc > 0; if = 0 then it's tangent, and has at minimum 1 point

            double sqrtDisc = Math.sqrt(discriminant);

            double ix1 = (D * dy + sgn(dy) * dx * sqrtDisc) / dr2 + cx;
            double iy1 = (-D * dx + Math.abs(dy) * sqrtDisc) / dr2 + cy;

            intersectPoints.add(new Ponto(ix1,iy1));

            // disc > 0 = intersection, so there are 2 points to be checked
            if (discriminant > 0) {
                double ix2 = (D * dy - sgn(dy) * dx * sqrtDisc) / dr2 + cx;
                double iy2 = (-D * dx - Math.abs(dy) * sqrtDisc) / dr2 + cy;

                intersectPoints.add(new Ponto(ix2,iy2));
            }
        }

        return Ponto.sort(intersectPoints);

    }
}
