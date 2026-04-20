package project;
import utils.*;
/*
    The ideia is this being the interface of all the geometric figures and having one seprated class that extends to circulo
    since those will be the only moving ones and the default is well static.
 */
public interface Obstacle {
    default boolean intersectsRoute(Route r) {
        return !r.intersectsFigura((Figura) this).isEmpty();
    }
}
