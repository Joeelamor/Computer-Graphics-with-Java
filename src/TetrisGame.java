package Q2;

import java.awt.*;
import java.awt.event.*;

public class TetrisGame extends Frame {
    public static void main(String[] args) {
        new TetrisGame();
    }

    TetrisGame() {
        super("Q2");
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        setSize(1500, 900);
        add("Center", new MainArea());
        setVisible(true);
    }
}