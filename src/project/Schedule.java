package project;
/**
 * Representa um agendamento de partida de um navio, contendo o instante de partida,
 * o porto de destino e a velocidade de cruzeiro a utilizar na viagem.
 *
 * @author Aashma Pandey-88430, Bruno Simão-80143, Pedro Ambrósio-88589.
 * @version 1.0 - 2025-05-14
 * @inv O tempo de partida é não pode ser negativo, o porto de destino
 * não é nulo e a velocidade é positiva.
 */
public class Schedule {
    private int departureTime;
    private Port destination;
    private double speed;

    /**
     * Constrói um agendamento com o instante de partida, destino e velocidade fornecidos.
     * @param departureTime instante de partida
     * @param destination porto de destino
     * @param speed velocidade do navio
     */
    public Schedule (int departureTime, Port destination, double speed){
        this.departureTime=departureTime;
        this.destination=destination;
        this.speed=speed;
    }

    /**
     * Devolve o instante de partida deste agendamento.
     *
     * @return o tempo de partida em unidades de tempo da simulação
     */
    public int getDepartureTime() {
        return departureTime;
    }

    /**
     * Devolve o porto de destino deste agendamento.
     *
     * @return O Porto de destino.
     */
    public Port getDestination() {
        return destination;
    }

    /**
     * Devolve a velocidade de cruzeiro associada a este agendamento.
     *
     * @return a velocidade.
     */
    public double getSpeed() {
        return speed;
    }

    /**
     * Devolve uma representação textual do agendamento no formato pedido.
     *
     * @return a string formatada com os dados do agendamento
     */
    @Override
    public String toString() {
        return String.format("T=%d, %s, %.0f", departureTime, destination.getName(), speed);
    }
}
