package project;

import utils.*;

import java.util.List;

/**
 * Representa um navio na simulação marítima, modelado como um círculo móvel.
 * Gere o movimento ao longo de uma rota calculada, a deteção de proximidade
 * com outros navios e o estado de espera para evitar colisões.
 *
 * @author Aashma Pandey-88430, Bruno Simão-80143, Pedro Ambrósio-88589.
 * @version 1.0 - 2025-05-14
 * @inv O raio é sempre 100, a velocidade é positiva, o porto de
 *  partida e de destino não são nulos, a rota não é nula, e a distância
 *  percorrida não é negativa
 */
public class Ship extends Circulo {
    private final String tripCode;
    private Ponto position;
    private final double speed; //constant?
    private final Route currentRoute;
    private int departureTime;
    private boolean isWaiting; //To avoid colisions??
    private boolean arrived;
    private final double radius = 100;
    private final Port startingPort;
    private final Port destinationPort;
    private double elapsedTime =0;
    private boolean isNear=false;
    private final Navegador nav;
    private double travelledDistance = 0;

    RouteGraphing rg = new RouteGraphing();
    /**
     * Constrói um navio com porto de partida, porto de destino, velocidade e
     * instante de partida.
     *
     * @param startingPort porto de partida
     * @param destinationPort porto de destino
     * @param speed velocidade de cruzeiro
     * @param departureTime instante de partida
     */
    public Ship(Port startingPort, Port destinationPort, double speed,
                int departureTime) {

        super(startingPort.getPosition(), 1.0);

        this.speed = speed;
        this.departureTime = departureTime;
        this.startingPort = startingPort;
        this.destinationPort = destinationPort;
        this.isWaiting = false;
        this.arrived = false;
        this.currentRoute = rg.findPath(startingPort,destinationPort);
        this.position = currentRoute.getPoints()[0];
        this.tripCode = startingPort.getName() + departureTime;
        this.nav = new Navegador(this.currentRoute);
    }

    /**
     * Atualiza a posição do navio com base no tempo e no efeito da corrente marítima.
     * Não tem efeito se o navio já chegou ou
     * está em espera. Marca o navio como chegado quando a distância percorrida
     * iguala ou supera a distância total da rota.
     * @param dt intrevalo de tempo
     * @param current vetor que representa a corrente marítima naquele instante
     */
    public void movement(double dt, Vetor current) {
        if (arrived || isWaiting) return;

        double currentEffect = current.moduloPosicao();

        travelledDistance += speed * currentEffect * dt;

        double totalDistance = currentRoute.findDistance();

        // Check if reached destination
        if (travelledDistance >= totalDistance) {
            position =currentRoute.getPoints()[currentRoute.getPoints().length - 1];
            arrived = true;
            return;
        }

        // Update position
        position = nav.positionByDistance(travelledDistance);

        this.center = position;
    }

    /**
     *  Verifica se este navio está suficientemente próximo de outro para que os
     *  seus círculos se toquem ou sobreponham.
     * @param other representa o raio de outro navio a comparar.
     * @return true se a distancia for menor ou igual a soma dos raios, ou false caso contrario.
     */
    public boolean isNear(Ship other) {
        double dx = this.center.getX() - other.center.getX();
        double dy = this.center.getY() - other.center.getY();
        double dist = Math.sqrt(dx * dx + dy * dy);
        return dist <= this.radius+other.radius; // circles touch when dist <= 1+1
    }

    /**
     * Determina se este navio deve ceder a passagem ao outro, com base na
     * comparação lexicográfica dos códigos de viagem. O navio com código
     * lexicograficamente maior espera.
     * @param other o outro navio com quem comparar a prioridade
     * @return true se este navio tiver de esperar, false caso contrario.
     */
    public boolean shouldWaitFor(Ship other) {
        return this.tripCode.compareTo(other.tripCode) > 0;
    }

    /**
     * Decrementa o tempo de partida do navio em uma unidade de tempo.
     */
    public void downDepartureTime(){
        departureTime--;
    }

    /**
     * Coloca o navio em estado de espera, suspendendo o seu movimento.
     */
    public void startWaiting() { isWaiting = true; }

    /**
     * Retira o navio do estado de espera, permitindo retomar o movimento.
     */
    public void stopWaiting()  { isWaiting = false; }

    /**
     * Devolve a posição geográfica atual do navio.
     *
     * @return o Ponto que representa a posição atual do navio
     */
    public Ponto getPosition() {
        return position;
    }

    /**
     * Indica se o navio chegou ao porto de destino.
     *
     * @return true se o navio chegou; false caso contrário
     */
    public boolean hasArrived() {
        return arrived;
    }

    /**
     * Indica se o navio está atualmente em estado de espera.
     *
     * @return true se o navio está à espera; false caso contrário
     */
    public boolean isWaiting() {
        return isWaiting;
    }

    /**
     * Devolve o código único da viagem deste navio.
     *
     * @return o código de viagem como String
     */
    public String getTripCode() {
        return tripCode;
    }

    /**
     * Devolve o instante de partida do navio.
     *
     * @return o tempo de partida em unidades de tempo da simulação
     */
    public int getDepartureTime() {
        return departureTime;
    }

    /**
     * Devolve a velocidade de cruzeiro do navio.
     *
     * @return a velocidade em unidades de distância por unidade de tempo
     */
    public double getSpeed() {
        return speed;
    }

    /**
     * Devolve a rota atual que o navio está a percorrer.
     *
     * @return a Route calculada entre o porto de partida e o de destino
     */
    public Route getCurrentRoute() {
        return currentRoute;
    }

    /**
     * Devolve o porto de partida do navio.
     *
     * @return o Port de onde o navio parte
     */
    public Port getStartingPort() {
        return startingPort;
    }

    /**
     * Devolve o porto de destino do navio.
     *
     * @return o Port para onde o navio se dirige
     */
    public Port getDestinationPort() {
        return destinationPort;
    }
}
