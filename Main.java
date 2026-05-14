package gui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import project.*;
import utils.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Simulador de Tráfego Marítimo");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());

            MappingPanel mappingPanel = new MappingPanel();
            InfoPanel infoPanel = new InfoPanel();

            RouteGraphing rg = new RouteGraphing();
            Port portA = rg.getPort("A");
            Port portD = rg.getPort("D");
            List<Port> allPorts = new ArrayList<>();
            for(int i = 0; i < 4; i++) {
                allPorts.add(rg.getPort(i));
            }


            Ship ship1 = new Ship(portA, portD, 20.0, 0);
            List<Ship> shipList = new ArrayList<>(Arrays.asList(ship1));


            List<Poligono> fixedObstacles = Arrays.asList(rg.getStaticObstacle());

            MovingObstacle mo1 = new MovingObstacle(new Ponto(0,0), 30.0);
            mo1.positioning(0);
            List<MovingObstacle> movingList = new ArrayList<>(Arrays.asList(mo1));

            List<Route> routeList = new ArrayList<>();
            routeList.add(ship1.getCurrentRoute());

            Vetor current = new Vetor(0, 0);
            mappingPanel.update(
                    routeList,
                    shipList,
                    movingList,
                    fixedObstacles,
                    allPorts,
                    current
            );
            frame.add(mappingPanel, BorderLayout.CENTER);
            frame.add(infoPanel, BorderLayout.EAST);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}