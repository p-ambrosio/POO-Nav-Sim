package project;
    //  DONE?
import utils.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class Port {
    private String name;
    private Ponto position;
    private List<Schedule> queque;

    public Port(String name, Ponto position) {
        this.name = name;
        this.position = position;
        this.queque = new ArrayList<>();
    }

    public void addSchedule(Schedule s) {
        if (s == null) throw new IllegalArgumentException("Schedule nulo");
        queque.add(s);
        queque.sort(Comparator.comparingInt(Schedule::getDepartureTime));
    }

    //getters for tests
    public String getName(){return name;}
    public Ponto getPosition(){return position;}
    public List<Schedule> getQueque(){ return new ArrayList<>(queque);}

}
