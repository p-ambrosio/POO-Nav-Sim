package apps;

import gui.InfoPanel;
import gui.MappingPanel;
import project.*;
import utils.*;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;
import javax.swing.Timer;
/*
    Will act as our main
 */
public class Simulation {

    private static final List<Ship> ships = new ArrayList<>();
    static RouteGraphing graph = new RouteGraphing();
    static MappingPanel mappingPanel = new MappingPanel();
    static InfoPanel infoPanel = new InfoPanel();
    static Vetor current = new Vetor(1, 2);
    public Simulation() {
        Ship[] shipArray= {
                new Ship(graph.getPort("A"), graph.getPort("B"), 1, 0),
                new Ship(graph.getPort("B"), graph.getPort("D"), 1, 100),
                new Ship(graph.getPort("C"), graph.getPort("A"), 1, 50),
                new Ship(graph.getPort("D"), graph.getPort("C"), 1, 150),
        };
        ships.addAll(List.of(shipArray));
    }

    static void main() {
        Simulation s = new Simulation();
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Simulador de Tráfego Marítimo");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());

            List<Port> allPorts = new ArrayList<>();
            for(int i = 0; i < 4; i++) {
                allPorts.add(graph.getPort(i));
            }

            List<Poligono> fixedObstacles = Arrays.asList(graph.getStaticObstacle());

            List<MovingObstacle> movingList = new ArrayList<>(List.of(graph.getMovingObstacle()));

            List<Route> routeList = graph.getRoutes();

            mappingPanel.update(
                    routeList,
                    ships,
                    movingList,
                    fixedObstacles,
                    allPorts,
                    current
            );

            infoPanel.setCurrentListener(v -> {
                mappingPanel.setCurrent(v);
                current=v;
            });
            frame.add(mappingPanel, BorderLayout.CENTER);
            frame.add(infoPanel, BorderLayout.EAST);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

        });

        Timer timer = new Timer(16, e -> {
            s.update(0.95);
            mappingPanel.repaint();

        });

        timer.start();
    }


    public void update(double dt) {

        // reset waiting state
        for (Ship ship : ships) {
            ship.stopWaiting();
        }

        // collision checks
        for (int i = 0; i < ships.size(); i++) {

            for (int j = i + 1; j < ships.size(); j++) {
                Ship a = ships.get(i);
                Ship b = ships.get(j);
                if (a.hasArrived() || b.hasArrived()) {
                    continue;
                }
                if (a.isNear(b)) {
                    if (a.shouldWaitFor(b)) {
                        a.startWaiting();
                    } else {
                        b.startWaiting();
                    }
                }
            }
        }

        // movement phase
        for (Ship ship : ships) {
            if(ship.getDepartureTime()>0) {
                ship.downDepartureTime();
                continue;
            }
            ship.movement(dt,current);
        }

        mappingPanel.repaint();
    }


}
