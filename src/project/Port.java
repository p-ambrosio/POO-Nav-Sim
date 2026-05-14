package project;

import utils.Ponto;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Representa um porto marítimo com nome, posição geográfica e uma fila de
 * agendamentos de partida ordenados cronologicamente.
 *
 * @author Aashma Pandey-88430, Bruno Simão-80143, Pedro Ambrósio-88589.
 * @version 1.0 - 2025-05-14
 * @inv O nome não é nulo nem vazio, a posição não é nula, e a fila de
 *      agendamentos está sempre ordenada por tempo de partida crescente.
 */
public class Port {
    private String name;
    private Ponto position;
    private List<Schedule> queque;
    /**
     * Constrói um porto com o nome e posição fornecidos, com fila de agendamentos vazia.
     *
     * @param name     o nome do porto; não deve ser {@code null} nem vazio
     * @param position a posição geográfica do porto; não deve ser {@code null}
     */
    public Port(String name, Ponto position) {
        this.name = name;
        this.position = position;
        this.queque = new ArrayList<>();
    }

    /**
     *Adiciona um agendamento à fila do porto e reordena a fila por tempo de partida crescente.
     * @param s  agendamento a adicionar.
     */
    public void addSchedule(Schedule s) {
        if (s == null) throw new IllegalArgumentException("Schedule nulo");
        queque.add(s);
        queque.sort(Comparator.comparingInt(Schedule::getDepartureTime));
    }

    /**
     * Devolve o nome do porto.
     *
     * @return o nome do porto como string.
     */
    public String getName(){return name;}

    /**
     * Devolve a posição do Porto.
     * @return o ponto que representa a posição do porto.
     */
    public Ponto getPosition(){return position;}

    /**
     * Devolve uma cópia da fila de agendamentos do porto, ordenada por tempo de partida.
     * @return uma nova list com os agendamentos.
     */
    public List<Schedule> getQueque(){ return new ArrayList<>(queque);}

}

