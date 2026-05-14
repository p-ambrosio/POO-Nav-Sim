/**
 * @author a90143 Bruno Simao
 * @version 2 - lab3 09-03-2026
*/

package utils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Ponto {
    private double x,y;
    /**
     * @param x    double number representing x position of the ponto
     * @param y    double number representing x position of the ponto
     */
    public Ponto(double x, double y){
        this.x=x;
        this.y=y;
    }

    /**
     *  @return x getter
     */
    public double getX(){ return x;}
    /**
     *  @return y getter
     */
    public double getY(){ return y;}

    /**
        Compares 2 pontos
        @param that will be compared with this.ponto
        @return     true or false depending if it's equal
     */
    @Override
    public boolean equals(Object  that){
        if (this == that) return true;
        if (!(that instanceof Ponto p)) return false;

        return Double.compare(p.getX(), getX()) == 0 &&
                Double.compare(p.getY(), getY()) == 0;
    }

    /**
     * Function that calculates distance between 2 points
     * @param that  Other point to calculate it with
     * @return      sqrt((x2-x1)^2 + (y2-y1)^2)
     */
    public double dist(Ponto that){
        double res=Math.pow(that.x-this.x,2)+Math.pow(that.y-this.y,2);
        return Math.sqrt(res);
    }

    /**
     * @return returns Ponto as (x,y) format with 2 decimals
     */
    public String toString(){
        return "("+String.format("%.2f", x)+","+String.format("%.2f", y)+")";
    }

    /**
        Calculates the modulo posição of the point with respect to the origin (0,0)
        @return Modulo Posicao of the ponto
     */
    public double moduloPosicao(){
        double res = Math.pow(x,2)+Math.pow(y,2);
        return Math.sqrt(res);
    }
    protected static Ponto[] sort(ArrayList<Ponto> arr){
        List<Ponto> points = new ArrayList<>(arr.stream().toList());
        points.sort(Comparator.comparingDouble(Ponto::getX).thenComparingDouble(Ponto::getY));

        return points.toArray(new Ponto[0]);
    }
}
