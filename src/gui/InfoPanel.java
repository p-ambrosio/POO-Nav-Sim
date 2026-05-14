package gui;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import project.*;
import java.awt.*;

public class InfoPanel extends JPanel {
    private final JTextField fieldX = new JTextField("0", 5);
    private final JTextField fieldY = new JTextField("0", 5);
    private final JLabel timeLbl = new JLabel("T = 0");
    private Runnable onCurrentChanged;

    public InfoPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(160, 760));
        setBorder(BorderFactory.createEmptyBorder(10, 8, 8, 8));
        setBackground(new Color(245, 245, 245));

        add(buildCurrentPanel());
        add(Box.createVerticalStrut(16));
        add(buildTimePanel());
    }

    private JPanel buildCurrentPanel() {
        JPanel p = new JPanel(new GridLayout(4, 2, 4, 4));
        p.setBorder(new TitledBorder("Corrente"));
        p.setBackground(new Color(245, 245, 245));
        p.setMaximumSize(new Dimension(150, 120));

        p.add(new JLabel("X:"));  p.add(fieldX);
        p.add(new JLabel("Y:"));  p.add(fieldY);

        JButton btn = new JButton("Aplicar");
        btn.addActionListener(e -> { if (onCurrentChanged != null) onCurrentChanged.run(); });
        p.add(new JLabel());
        p.add(btn);
        return p;
    }

    private JPanel buildTimePanel() {
        JPanel p = new JPanel();
        p.setBackground(new Color(245, 245, 245));
        timeLbl.setFont(new Font("SansSerif", Font.BOLD, 13));
        p.add(timeLbl);
        return p;
    }

    public void updateTime(int t) {
        timeLbl.setText("T = " + t);
    }

}
