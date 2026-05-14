/**
 * @author a90143 Bruno Simao
 * @version 4 - lab3 23-03-2026
 */

package utils;

public class SegmentoReta {
    private Ponto p;
    private Vetor v;
    /**
     * @inv        p != v
     * @param p    Ponto object that will be the first connector of the line segment
     * @param v    Vetor object that will be the 2nd connector of the line segment
     */
    public SegmentoReta(Ponto p, Vetor v){
        this.p = p;
        this.v = v;

        Vetor b = new Vetor(p);
        if(b.equals(v)){
            System.out.println("SegmentoReta:iv");
            System.exit(0);
        }
    }

    public Ponto getP() {
        return p;
    }

    public Vetor getV() {
        return v;
    }

    /**
     * @inv        p1 != p2
     * @param p1   Ponto object that will be the 1st connector of the line segment
     * @param p2   Ponto object that will be the 2nd connector of the line segment
     */
    public SegmentoReta(Ponto p1, Ponto p2){
        this.p = p1;
        this.v = new Vetor(p2);

        if(p1.equals(p2)){
            System.out.println("SegmentoReta:iv");
            System.exit(0);
        }
    }

    /**
     * @inv        p1 != p2
     * @return     Ordem of distance of the segmento de reta
     */
    public SegmentoReta distOrdem(){
        Ponto n = new Ponto(p.getX()+v.getX(),p.getY()+v.getY());

        if(n.moduloPosicao() < p.moduloPosicao()) return new SegmentoReta(n, new Vetor(p));
        else return new SegmentoReta(p, new Vetor(n));
    }

    /**
     * Torna em String
     * @inv        p1 != p2
     * @return     String formatted as "sr((x1,y1),(x2,y2))"
     */
    public String toString(){
        String pString = "("+ String.format("%.2f", p.getX())+","+ String.format("%.2f", p.getY())+")";
        String vString = "("+String.format("%.2f", v.getX())+","+String.format("%.2f", v.getY())+")";
        return "sr("+pString+"; "+vString+")";
    }

    /**
    * Finds the intersecting point between a vector and a line segment
    * @inv        p1 != p2
    * @see        "http://www.paulbourke.net/geometry/pointlineplane/"
    * @param v    Vetor object that will be used to find the intersecting point
    * @return     Ponto intersecting with the segmento reta
     */
    public Ponto intersect(Vetor v){

        double denominator = ((p.getX()-this.v.getX())*(-v.getY())) - ((p.getY()-this.v.getY())*(-v.getX()));
        double a1 = (p.getX()*(-v.getY()) - p.getY()*(-v.getX())) / denominator;

        if(a1 > 0 || a1 < 1) {
            double x = p.getX() + a1 * (this.v.getX() - p.getX());
            double y = p.getY() + a1 * (this.v.getY() - p.getY());

            return new Ponto(x, y);
        }else return null;
    }

    /**
     * @see     <https://paulbourke.net/geometry/pointlineplane/> Intersection point of two line segments in 2 dimensions
     * @param   that - Other line segment to check for intersections
     * @return  intersecting point between 2 line segments
     */
    public Ponto intersect(SegmentoReta that){
        double x1 = this.p.getX();
        double x2 = this.v.getX();
        double x3 = that.p.getX();
        double x4 = that.v.getX();

        double y1 = this.p.getY();
        double y2 = this.v.getY();
        double y3 = that.p.getY();
        double y4 = that.v.getY();

        double den= ((y4 - y3) * (x2 - x1) - (x4 - x3) * (y2 - y1));

        double ua = ((x4-x3)*(y1-y3)-(y4-y3)*(x1-x3))/den;
        double ub = ((x2-x1)*(y1-y3)-(y2-y1)*(x1-x3))/den;

        if(ua < 0 || ua > 1 || ub < 0 || ub > 1){
            return null;
        }

        return new Ponto((x1+(ua*(x2-x1))),y1+(ua*(y2-y1)));
    }
}
