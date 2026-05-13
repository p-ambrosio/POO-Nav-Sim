package gui;

import project.*;
import utils.*;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MappingPanel extends JPanel {

    //lets hope the size is well defined
    private static final int MODEL = 800;
    private static final int PAD_L= 44;
    private static final int PAD_B = 36;
    private static final int PAD_T = 16;
    private static final int DRAW = 630;

    //We can change later dosen't really matter
    private static final Color[] routeColors= {
            Color.BLACK,
            Color.RED,
            new Color(0, 160, 0),
            new Color(30, 80, 220),
            new Color(200, 100, 0),
            new Color(160, 0, 180)

    };

    private List<Route> routes;
    private List<Ship> ships;
    private List<MovingObstacle> movingObs;
    private List<Poligono> fixedObs;
    private List<Port> ports;
    private Vetor current;
    private RouteGraphing graphing;

    public MappingPanel() {
        int w = PAD_L + DRAW + 10;
        int h = PAD_T + DRAW + PAD_B;
        setPreferredSize(new Dimension(w, h));
        setBackground(new Color(214, 234, 248)); //pretty whiteish blue
    }

    public void update(List<Route> routes, List<Ship> ships, List<MovingObstacle> movingObs, List<Poligono> fixedObs, List<Port> ports, Vetor current) {
        this.routes = routes;   this.ships = ships;
        this.movingObs = movingObs; this.fixedObs = fixedObs;
        this.ports = ports; this.current = current;
        this.graphing = graphing;
        repaint();
    }

    private int sx(double mx) { return PAD_L + (int) Math.round(mx / MODEL * DRAW); }
    private int sy(double my) { return PAD_T + DRAW - (int) Math.round(my / MODEL * DRAW); }
    private int sp(double md) { return Math.max(1, (int) Math.round(md / MODEL * DRAW)); }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        drawGrid(g2);
        drawFixedObstacles(g2);
        drawRoutes(g2);
        drawMovingObstacles(g2);
        drawShips(g2);
        drawPorts(g2);
        drawCurrentBox(g2);
    }

    private void drawGrid(Graphics2D g2) {
        // 8 divisions on each axis
        g2.setColor(new Color(100, 150, 200, 60));
        g2.setStroke(new BasicStroke(0.5f));
        for (int i = 0; i <= 8; i++) {
            int mv = i * 100;
            g2.drawLine(sx(mv), PAD_T, sx(mv), PAD_T + DRAW);
            g2.drawLine(PAD_L, sy(mv), PAD_L + DRAW, sy(mv));
        }
        // Axes
        g2.setColor(new Color(40, 80, 140, 160));
        g2.setStroke(new BasicStroke(1.5f));
        g2.drawLine(PAD_L, PAD_T, PAD_L, PAD_T + DRAW);
        g2.drawLine(PAD_L, PAD_T + DRAW, PAD_L + DRAW, PAD_T + DRAW);
        // Labels
        g2.setColor(new Color(40, 80, 140, 200));
        g2.setFont(new Font("SansSerif", Font.PLAIN, 11));
        for (int i = 0; i <= 8; i++) {
            int mv = i * 100;
            g2.drawString(String.valueOf(i), sx(mv) - 4, PAD_T + DRAW + 16);
            if (i > 0) g2.drawString(String.valueOf(i), PAD_L - 18, sy(mv) + 4);
        }
        g2.setStroke(new BasicStroke(1f));
    }

    private void drawFixedObstacles(Graphics2D g2) {
        if (fixedObs == null) return;
        for (Poligono p : fixedObs) {
            Ponto[] v = p.getVertices();
            int[] xs = new int[v.length], ys = new int[v.length];
            for (int i = 0; i < v.length; i++) { xs[i] = sx(v[i].getX()); ys[i] = sy(v[i].getY()); }
            g2.setColor(new Color(180, 110, 50, 90));
            g2.fillPolygon(xs, ys, v.length);
            g2.setColor(new Color(140, 80, 30));
            g2.setStroke(new BasicStroke(1.5f));
            g2.drawPolygon(xs, ys, v.length);
            g2.setStroke(new BasicStroke(1f));
        }
    }

    private void drawRoutes(Graphics2D g2) {
        if (routes == null) return;
        for (int ri = 0; ri < routes.size(); ri++) {
            g2.setColor(routeColors[ri % routeColors.length]);
            g2.setStroke(new BasicStroke(1.8f));
            Ponto[] pts = routes.get(ri).getPoints();
            for (int i = 0; i < pts.length - 1; i++)
                g2.drawLine(sx(pts[i].getX()), sy(pts[i].getY()),
                        sx(pts[i+1].getX()), sy(pts[i+1].getY()));
            // Waypoint dots
            g2.setColor(new Color(50, 90, 200));
            for (Ponto pt : pts) {
                g2.fillOval(sx(pt.getX()) - 3, sy(pt.getY()) - 3, 6, 6);
            }
        }
        g2.setStroke(new BasicStroke(1f));
    }

    private void drawMovingObstacles(Graphics2D g2) {
        if (movingObs == null) return;
        for (MovingObstacle mo : movingObs) {
            Ponto c = mo.getCenter();
            int cx = sx(c.getX()), cy = sy(c.getY());
            int pr = sp(mo.getRaio());
            g2.setColor(new Color(10, 95, 130, 210));
            g2.fillOval(cx - pr, cy - pr, pr * 2, pr * 2);
            g2.setColor(new Color(5, 70, 100));
            g2.setStroke(new BasicStroke(2f));
            g2.drawOval(cx - pr, cy - pr, pr * 2, pr * 2);
            g2.setStroke(new BasicStroke(1f));
            g2.setColor(new Color(120, 200, 230));
            g2.fillOval(cx - 4, cy - 4, 8, 8);
        }
    }

    private void drawShips(Graphics2D g2) {
        if (ships == null) return;
        for (Ship s : ships) {
            if (s.hasArrived()) continue;
            int cx = sx(s.getPosition().getX());
            int cy = sy(s.getPosition().getY());
            boolean near = false;
            for (Ship other : ships) {
                if (other != s && !other.hasArrived() && s.isNear(other)) { near = true; break; }
            }
            int pr = Math.max(20, sp(1));

            if (near) {
                // Filled semi-transparent yellow-green
                g2.setColor(new Color(190, 225, 30, 110));
                g2.fillOval(cx - pr, cy - pr, pr * 2, pr * 2);
            }
            // Always draw the circle outline
            g2.setColor(near
                    ? new Color(170, 210, 20, 220)
                    : new Color(170, 210, 20, 80));
            g2.setStroke(new BasicStroke(near ? 2f : 1f));
            g2.drawOval(cx - pr, cy - pr, pr * 2, pr * 2);
            g2.setStroke(new BasicStroke(1f));

            // Ship body — small black rounded rectangle
            g2.setColor(s.isWaiting() ? new Color(60, 60, 60) : Color.BLACK);
            g2.fillRoundRect(cx - 8, cy - 4, 16, 8, 3, 3);

            // Trip code label
            g2.setFont(new Font("SansSerif", Font.PLAIN, 10));
            g2.setColor(new Color(20, 40, 100));
            g2.drawString(s.getTripCode(), cx - 6, cy + 16);
        }
    }

    private void drawPorts(Graphics2D g2) {
        if (ports == null) return;
        for (Port p : ports) {
            int cx = sx(p.getPosition().getX());
            int cy = sy(p.getPosition().getY());
            // Blue dot
            g2.setColor(new Color(50, 90, 200));
            g2.fillOval(cx - 5, cy - 5, 10, 10);
            g2.setColor(new Color(20, 50, 140));
            g2.setStroke(new BasicStroke(1.2f));
            g2.drawOval(cx - 5, cy - 5, 10, 10);
            g2.setStroke(new BasicStroke(1f));
            drawPortLabel(g2, p, cx, cy);
        }
    }

    private void drawPortLabel(Graphics2D g2, Port p, int cx, int cy) {
        List<Schedule> q = p.getQueque();
        Font boldF  = new Font("SansSerif", Font.BOLD, 12);
        Font plainF = new Font("SansSerif", Font.PLAIN, 11);
        FontMetrics bfm = g2.getFontMetrics(boldF);
        FontMetrics pfm = g2.getFontMetrics(plainF);

        String title = "Porto " + p.getName();
        int lh = 15, pw = 8, ph = 5;
        int bw = bfm.stringWidth(title);
        for (Schedule s : q) bw = Math.max(bw, pfm.stringWidth(s.toString()));
        bw += pw * 2;
        int bh = ph * 2 + lh + q.size() * lh;
        int bx = cx + 10, by = cy - bh - 6;
        if (bx + bw > PAD_L + DRAW - 4) bx = cx - bw - 10;
        if (by < PAD_T)                  by = cy + 10;

        // Box
        g2.setColor(new Color(255, 205, 185, 240));
        g2.fillRoundRect(bx, by, bw, bh, 10, 10);
        g2.setColor(new Color(210, 80, 60));
        g2.setStroke(new BasicStroke(1.5f));
        g2.drawRoundRect(bx, by, bw, bh, 10, 10);
        g2.setStroke(new BasicStroke(1f));

        // Title
        g2.setFont(boldF);
        g2.setColor(new Color(190, 0, 0));
        g2.drawString(title, bx + pw, by + ph + bfm.getAscent());

        // Schedules
        g2.setFont(plainF);
        g2.setColor(new Color(160, 0, 0));
        for (int i = 0; i < q.size(); i++)
            g2.drawString(q.get(i).toString(), bx + pw,
                    by + ph + lh + pfm.getAscent() + i * lh);
    }

    private void drawCurrentBox(Graphics2D g2) {
        String cx = "X = " + (current == null ? "0" : String.format("%.0f", current.getX()));
        String cy = "Y = " + (current == null ? "0" : String.format("%.0f", current.getY()));
        Font f = new Font("SansSerif", Font.PLAIN, 11);
        g2.setFont(f);
        FontMetrics fm = g2.getFontMetrics(f);
        String[] lines = {"Velocidade", "da corrente", cx, cy};
        int pw = 8, lh = 15;
        int bw = 0;
        for (String l : lines) bw = Math.max(bw, fm.stringWidth(l));
        bw += pw * 2;
        int bh = 6 + lines.length * lh + 4;
        int bx = PAD_L + 4, by = PAD_T + 4;

        g2.setColor(new Color(230, 255, 230, 230));
        g2.fillRoundRect(bx, by, bw, bh, 8, 8);
        g2.setColor(new Color(60, 150, 70));
        g2.setStroke(new BasicStroke(1.2f));
        g2.drawRoundRect(bx, by, bw, bh, 8, 8);
        g2.setStroke(new BasicStroke(1f));

        g2.setColor(new Color(20, 100, 30));
        int y = by + 6 + fm.getAscent();
        for (String l : lines) { g2.drawString(l, bx + pw, y); y += lh; }
    }

}
