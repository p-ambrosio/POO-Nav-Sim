/**
 * @author a90143 Bruno Simao
 * @version 2.0 - lab5 07-04-2026
 */

package utils;

public class AutoPilot {
    private Ponto A,B;
    Route r;
    /**
     * @inv A != B (it stays in place otherwise)
     * @param A         Starting point
     * @param B         Finishing point
     */
    public AutoPilot(Ponto A, Ponto B){
        this.A = A;
        this.B = B;
        if(A.equals(B)){
            System.out.println("SegmentoReta:iv");
            System.exit(0);
        }
    }

    public AutoPilot(Route r){
        this.r = r;
    }

    /**
     * Does calculations on how much speed the plane would need to go to dislocate within a certain time
     * @inv A != B (it stays in place otherwise)
     * @param windSpeed lateral wind speed
     * @param time      time
     * @return          necessary vectorial velocity for the plane to move from point A to B
     */
    public Vetor speed(Vetor windSpeed, double time){
        Vetor A = new Vetor(this.A);
        Vetor B = new Vetor(this.B);
        Vetor r = B.sub(A);

        return ((r).mult(Math.pow(time,-1))).sub(windSpeed);
    }

    /**
     * Does calculations on how much time it would take the plane to dislocate
     * @inv A != B (it stays in place otherwise)
     * @param linearSpeed   Linear speed of the plane
     * @return              time needed for the plane to cross that space (r)/linearspeed
     */
    public double time(double linearSpeed){
        Vetor A = new Vetor(this.A);
        Vetor B = new Vetor(this.B);
        Vetor calc = ((B.sub(A)));
        return calc.moduloPosicao()/linearSpeed;
    }
}
