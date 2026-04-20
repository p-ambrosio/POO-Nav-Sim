package project;

import utils.*;

import java.util.ArrayList;
import java.util.List;


public class Port {
    private String name;
    private Ponto position;
    private List<Schedule> departures;

    public Port(String name, Ponto position) {
        this.name = name;
        this.position = position;
        this.departures = new ArrayList<>();
    }


}
