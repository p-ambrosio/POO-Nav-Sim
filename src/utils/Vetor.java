/**
* @author a90143 Bruno Simao
* @version 3 - lab3 09-03-2026
*/
package utils;

public class Vetor {

    private double x,y;

    /**
     * @param a     represents point that will be turned into a vector
     */
    public Vetor(Ponto a){
        x = a.getX();
        y = a.getY();
    }
    /**
     * @inv         this.moduloPosicao != 0
    *  @param x     double number representing x position of the vector
    *  @param y     double number representing y position of the vector
    */
    public Vetor(double x, double y){
        this.x=x; this.y=y;
        if(moduloPosicao() == 0){
            System.out.println("Vetor:iv");
            System.exit(0);
        }
    }

    /**
     * @return      x as double
     */
    public double getX(){ return x;}

    /**
     * @return      y as double
     */
    public double getY(){ return y;}


    /**
     * Checks       cossine similarity between 2 vectors
     * @inv         this.moduloPosicao != 0
     * @param that  representing other vector to do compare the cossine with
     * @return      <v1,v2> / |v1| * |v2|
     */
    public double cossineSimilarity(Vetor that){
        double uv = this.produtoInterno(that);
        double denominator = this.moduloPosicao()*that.moduloPosicao();

        return uv/denominator;
    }

    /**
     * Produto interno between 2 vectors
     * @inv         this.moduloPosicao != 0
     * @param that  representing other vector to do Internal product with
     * @return      x1*x2 + y1*y2
     */
    public double produtoInterno(Vetor that){
        return this.x*that.x + this.y*that.y;
    }

    /**
     * Compares 2 vectors
     * @inv         this.moduloPosicao != 0
     * @param that  representing other vector to compare with
     * @return      true or false
     */
    @Override
    public boolean equals(Object that){
        if (this == that) return true;
        if (!(that instanceof Vetor)) return false;

        Vetor p = (Vetor) that;
        return Double.compare(p.getX(), getX()) == 0 &&
                Double.compare(p.getY(), getY()) == 0;
    }

    /**
     * Calculates the modulo posição of the point with respect to the origin (0,0)
     * @return      |v| = sqrt(x^2+ y^2)
     */
    public double moduloPosicao(){
        double res = Math.pow(x,2)+Math.pow(y,2);
        return Math.sqrt(res);
    }

    /**
     * Multiplies a vector by scalar d
     * @inv         this.moduloPosicao != 0
     * @param d     scalar number to be multiplied with
     * @return      new vector resultant of the multiplication
     */
    public Vetor mult(double d){
        return new Vetor(this.x*d, this.y*d);
    }

    /**
     * Creates a vector resultant of the sum of 2 vectors
     * @inv         this.moduloPosicao != 0
     * @param v     vector to sum this.vector with
     * @return      new vector resultant of sum
     */
    public Vetor add(Vetor v){
        return new Vetor(this.x+v.getX(), this.y+v.getY());
    }

    /**
     * Creates a vector resultant of the subtraction of 2 vectors
     * @inv         this.moduloPosicao != 0
     * @param v     vector to subtraction this.vector with
     * @return      new vector resultant of subtraction
     */
    public Vetor sub(Vetor v){
        return new Vetor(this.x-v.getX(), this.y-v.getY());
    }

    /**
     * @inv         this.moduloPosicao != 0
     * @return vector formatted as "[x,y]"
     */
    public String toString(){
        return "["+String.format("%.2f", x)+","+String.format("%.2f", y)+"]";
    }

    /**
     * Checks intersecting point between a vector and a segmento de reta
     * @inv         this.moduloPosicao != 0
     * @param v     represents segmento reta to check intersection with
     * @return      intersecting point
     */
    public Ponto intersect(SegmentoReta v){
        return v.intersect(this);
    }

}
