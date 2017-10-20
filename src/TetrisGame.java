import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;

public class TetrisGame extends Frame {

    static TetrisGame g;

    public static void main(String[] args) {
        initialize();
    }

    TetrisGame(int speed, int rol, int score, int w, int h) {
        super("Tetris Game");
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        setSize(1500, 900);
        add("Center", new MainArea(new Board(speed, rol, score, w, h)));
        setVisible(true);
    }

    public static void initialize() {
        Frame f = new Frame("Specifying game parameters.");
//        f.setSize(800, 400);
        f.setLayout(null);

        int[] defaultValues = new int[]{1, 20, 1, 10, 20};

        Scrollbar[] scrollbars = new Scrollbar[]{
                // s[0] => speed factor (range: 0.1-1.0).
                new Scrollbar(Scrollbar.HORIZONTAL, 1, 1, 1, 11),
                // s[1] => number of rows required for each Level of difficulty (range: 20-50).
                new Scrollbar(Scrollbar.HORIZONTAL, 1, 1, 1, 51),
                // s[2] => scoring factor (range: 1-10).
                new Scrollbar(Scrollbar.HORIZONTAL, 1, 1, 1, 11),
                // s[3] => width of the play board (range 10 - 20)
                new Scrollbar(Scrollbar.HORIZONTAL, 10, 1, 10, 21),
                // s[4] => height of the play board (range 20 - 30)
                new Scrollbar(Scrollbar.HORIZONTAL, 20, 1, 20, 31)
        };

        Label[] labels = new Label[]{
                new Label("Speed Factor", Label.LEFT),
                new Label("Rows in each Level", Label.LEFT),
                new Label("Scoring Factor", Label.LEFT),
                new Label("Width of Board", Label.LEFT),
                new Label("Height of Board", Label.LEFT),
        };

        Label[] values = new Label[]{
                new Label("", Label.LEFT),
                new Label("", Label.LEFT),
                new Label("", Label.LEFT),
                new Label("", Label.LEFT),
                new Label("", Label.LEFT),
        };

        int labelWidth = 180;
        int scrollbarWidth = 400;
        int valueWidth = 100;
        int intercept = 20;
        int height = 40;
        for (int i = 0; i < scrollbars.length; i++) {
            int y = height * (i + 1);
            final int index = i;
            labels[i].setBounds(intercept, y, labelWidth, height / 2);
            scrollbars[i].setBounds(intercept * 2 + labelWidth, y, scrollbarWidth, height / 2);
            values[i].setBounds(intercept * 3 + labelWidth + scrollbarWidth, y, valueWidth, height / 2);
            values[i].setText("" + scrollbars[i].getValue());
            scrollbars[i].addAdjustmentListener(e -> values[index].setText("" + scrollbars[index].getValue()));
            f.add(labels[i]);
            f.add(scrollbars[i]);
            f.add(values[i]);
        }

        f.setBounds(500, 500, intercept * 4 + labelWidth + scrollbarWidth + valueWidth, height * 8);

        Button b = new Button("OK");
        b.setBounds(20, height * 7, 80, 30);
        Button d = new Button("Reset");
        d.setBounds(120, height * 7, 80, 30);
        Button q = new Button("Quit");
        q.setBounds(220, height * 7, 80, 30);

        d.addActionListener(e -> {
            for (int i = 0; i < scrollbars.length; i++) {
                scrollbars[i].setValue(defaultValues[i]);
                values[i].setText("" + scrollbars[i].getValue());
            }
        });

        b.addActionListener(e -> {
            int speed = scrollbars[0].getValue();
            int rol = scrollbars[1].getValue();
            int score = scrollbars[2].getValue();
            int w = scrollbars[3].getValue();
            int h = scrollbars[4].getValue();
            g = new TetrisGame(speed, rol, score, w, h);
            f.dispose();
        });

        q.addActionListener(e -> System.exit(0));

        f.add(b);
        f.add(d);
        f.add(q);
        f.setVisible(true);
    }
}
