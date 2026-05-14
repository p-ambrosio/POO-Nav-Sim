package gui;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import utils.Vetor;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.function.Consumer;

public class InfoPanel extends JPanel {
    private final JTextField fieldX = new JTextField("1", 5);
    private final JTextField fieldY = new JTextField("2", 5);
    private Consumer<Vetor> currentListener;

    public InfoPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(160, 760));
        setBorder(BorderFactory.createEmptyBorder(10, 8, 8, 8));
        setBackground(new Color(245, 245, 245));

        add(buildCurrentPanel());
        add(Box.createVerticalStrut(16));
    }

    private JPanel buildCurrentPanel() {
        JPanel p = new JPanel(new GridLayout(4, 2, 4, 4));
        p.setBorder(new TitledBorder("Corrente"));
        p.setBackground(new Color(245, 245, 245));
        p.setMaximumSize(new Dimension(150, 120));

        p.add(new JLabel("X:"));  p.add(fieldX);
        p.add(new JLabel("Y:"));  p.add(fieldY);

        JButton btn = new JButton("Aplicar");
        btn.addActionListener(this::actionPerformed);
        p.add(new JLabel());
        p.add(btn);
        return p;
    }
    public void setCurrentListener(java.util.function.Consumer<Vetor> listener) {
        this.currentListener = listener;
    }
    private void actionPerformed(ActionEvent e)
    {

        Vetor v = new Vetor(
                Integer.parseInt(fieldX.getText()),
                Integer.parseInt(fieldY.getText())
        );

        if(currentListener != null) {
            currentListener.accept(v);
        }
    }


}
