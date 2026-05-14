/**
 * @author a90143 Bruno Simao
 * @version 1 - lab5 07-04-2026
 */

package utils;

import java.util.ArrayList;

public class Navegador extends AutoPilot{

    public Navegador(Route r) {
        super(r);
    }

    /**
     * finds an array of speed vectors of a route using windspeed and linearspeed,
     * to find the time of each specific segment
     * @param windSpeed     inputted by user
     * @param linearSpeed   inputted by user
     * @return  returns array of speed vectors
     */
    public Vetor[] speedArray(Vetor windSpeed, double linearSpeed) {
        ArrayList<Vetor> speedVector = new ArrayList<>();
        SegmentoReta[] srArr = super.r.turnToLineSegment(false);

        for (SegmentoReta sr : srArr) {
            Vetor A = new Vetor(sr.getP());
            Vetor B = sr.getV();
            Vetor r = B.sub(A);

            double segmentTime = r.moduloPosicao() / linearSpeed;

            Vetor speed = r.mult(1.0 / segmentTime).sub(windSpeed);
            speedVector.add(speed);
        }

        return speedVector.toArray(new Vetor[0]);
    }

    /**
     * finds position of conductor in a set time using line interpolation
     * @param time          time inputted by user
     * @param linearSpeed   linear speed inputted by user
     * @return              point position of conductor
     * @see <https://www.cuemath.com/linear-interpolation-formula/>
     */
    public Ponto position(double time, double linearSpeed) {
        double distanceToTravel = time * linearSpeed;
        Ponto[] pts = super.r.getPoints();

        for (int i = 0; i < pts.length - 1; i++) {
            Ponto A = pts[i];
            Ponto B = pts[i + 1];

            double segmentDist = A.dist(B);

            if (distanceToTravel <= segmentDist) {
                double ratio = distanceToTravel / segmentDist;

                double x = A.getX() + ratio * (B.getX() - A.getX());
                double y = A.getY() + ratio * (B.getY() - A.getY());

                return new Ponto(x, y);
            }

            distanceToTravel -= segmentDist;
        }

        return pts[pts.length - 1];
    }

    /**
     * calculates time to take by the route using linear speed
     * @param linearSpeed   Linear speed inputted by user
     * @return              t = B-A/linearspeed
     */
    @Override
    public double time(double linearSpeed) {
        double t = 0;
        SegmentoReta[] srArr = (super.r).turnToLineSegment(false);
        for(SegmentoReta sr : srArr){
            Vetor A = new Vetor(sr.getP());
            Vetor B = sr.getV();

            Vetor calc = ((B.sub(A)));
            t += calc.moduloPosicao()/linearSpeed;
        }

        return t;
    }
    public Ponto positionByDistance(double distanceToTravel) {

        Ponto[] pts = super.r.getPoints();

        for (int i = 0; i < pts.length - 1; i++) {

            Ponto A = pts[i];
            Ponto B = pts[i + 1];

            double segmentDist = A.dist(B);

            if (distanceToTravel <= segmentDist) {

                double ratio = distanceToTravel / segmentDist;

                double x =
                        A.getX()
                                + ratio * (B.getX() - A.getX());

                double y =
                        A.getY()
                                + ratio * (B.getY() - A.getY());

                return new Ponto(x, y);
            }

            distanceToTravel -= segmentDist;
        }

        return pts[pts.length - 1];
    }
}
