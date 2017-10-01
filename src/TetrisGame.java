import java.awt.*;
import java.awt.event.*;

public class TetrisGame extends Frame {
    public static void main(String[] args) {
        new TetrisGame();
    }

    TetrisGame() {
        super("Tetris Game");
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        setSize(1500, 900);
        add("Center", new MainArea(new Board()));
        setVisible(true);
    }
}
