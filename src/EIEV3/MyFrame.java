package EIEV3;

import java.awt.Color;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Graphics;

class MyFrame extends Frame {
    private static final long serialVersionUID = 1L;
    static final String VALUE_0 = "0";
    static final String VALUE_500 = "500";
    static final String VALUE_1000 = "1000";
    static final String VALUE_1200 = "1200";
    static final int DIVISION_UNIT = 100;
    static final int GAP_X_COORDINATE = 200;
    static final int GAP_Y_COORDINATE = 100;

    public MyFrame() {
        this.setTitle("課題3");
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        this.setLocation(300, 200);
        this.setSize(1600, 1200);
        this.setBackground(Color.white);
    }

    @Override
    public void update(Graphics g) {
        super.update(g);
        paint(g);
    }

    public void paint(Graphics g) {
        // 長方形描画
        for (int i = 0; i < Board.arrR.size(); i++) {
            g.setColor(Board.arrR.get(i).recColor());
            g.fillRect(Board.arrR.get(i).getRX() + GAP_X_COORDINATE, Board.arrR.get(i).getRY() + GAP_Y_COORDINATE,
                    Board.arrR.get(i).getRWidth(), Board.arrR.get(i).getRHeight());
        }
        // 縦線描画
        for (int i = 1; i < Board.bWidth / DIVISION_UNIT; i++) {
            g.setColor(new Color(204, 204, 204));// Light Gray
            g.drawLine(DIVISION_UNIT * i + GAP_X_COORDINATE, GAP_Y_COORDINATE, DIVISION_UNIT * i + GAP_X_COORDINATE,
                    Board.bHeight + GAP_Y_COORDINATE);
        }
        // 横線描画
        for (int j = 1; j < Board.bHeight / DIVISION_UNIT; j++) {
            g.setColor(new Color(204, 204, 204));// Light Gray
            g.drawLine(GAP_X_COORDINATE, DIVISION_UNIT * j + GAP_Y_COORDINATE, Board.bWidth + GAP_X_COORDINATE,
                    DIVISION_UNIT * j + GAP_Y_COORDINATE);
        }
        // ボード描画
        g.setColor(Color.black);
        g.drawRect(GAP_X_COORDINATE, GAP_Y_COORDINATE, Board.bWidth, Board.bHeight);

        // 目盛り描画
        g.drawString(VALUE_0, 190, 95);
        g.drawString(VALUE_500, 690, 95);
        g.drawString(VALUE_1000, 1185, 95);
        g.drawString(VALUE_1200, 1385, 95);
        g.drawString(VALUE_500, 170, 605);
        g.drawString(VALUE_1000, 165, 1105);
    }
}