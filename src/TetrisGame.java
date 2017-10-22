import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashSet;
import java.util.Set;

public class TetrisGame extends Frame {

    static TetrisGame g;
    static Board b;
    static Checkbox[] checkboxes = new Checkbox[10];

    public static void main(String[] args) {
        initialize();
        while (true) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.exit(0);
            }
            if (b != null && b.over) {
                break;
            }
        }
        Frame f = new Frame("Game Over");
        f.setLayout(null);
        f.setBounds(550, 300, 400, 200);

        Dimension df = f.getSize();

        int intercept = df.height / 12;
        int left = df.width / 5;

        Label score = new Label("Score: " + b.score, Label.CENTER);
        score.setBounds(left, 2 * intercept, 3 * left, 2 * intercept);
        Label level = new Label("Level: " + b.level, Label.CENTER);
        level.setBounds(left, 5 * intercept, 3 * left, 2 * intercept);
        Label line = new Label("Line: " + b.line, Label.CENTER);
        line.setBounds(left, 8 * intercept, 3 * left, 2 * intercept);
        Button quit = new Button("Quit");
        quit.setBounds(2 * left, (int) (10.5 * intercept), left, intercept);
        quit.addActionListener(e -> System.exit(0));

        f.add(score);
        f.add(level);
        f.add(line);
        f.add(quit);

        f.setVisible(true);
    }

    TetrisGame(int speed, int rol, int score, int w, int h, Set set) {
        super("Tetris Game");
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        setSize(1500, 900);
        b = new Board(speed, rol, score, w, h, set);
        add("Center", new MainArea(b));
        setVisible(true);


    }

    public static void initialize() {
        Frame f = new Frame("Start Tetris Game.");
        f.setLayout(new BorderLayout());

        Panel center = new Panel();
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));

        Panel parameters = new Panel();
        parameters.setLayout(new BoxLayout(parameters, BoxLayout.Y_AXIS));

        int[] defaultValues = new int[]{1, 20, 1, 10, 20};

        Scrollbar[] scrollbars = new Scrollbar[]{
                // s[0] => speed factor (range: 0.1-1.0).
                new Scrollbar(Scrollbar.HORIZONTAL, 1, 1, 1, 21),
                // s[1] => number of rows required for each Level of difficulty (range: 20-50).
                new Scrollbar(Scrollbar.HORIZONTAL, 1, 1, 20, 51),
                // s[2] => scoring factor (range: 1-10).
                new Scrollbar(Scrollbar.HORIZONTAL, 1, 1, 1, 11),
                // s[3] => width of the play board (range 10 - 20)
                new Scrollbar(Scrollbar.HORIZONTAL, 10, 1, 10, 21),
                // s[4] => height of the play board (range 20 - 30)
                new Scrollbar(Scrollbar.HORIZONTAL, 20, 1, 20, 31)
        };

        Label[] labels = new Label[]{
                new Label("Speed Factor      ", Label.LEFT),
                new Label("Rows in each Level", Label.LEFT),
                new Label("Scoring Factor    ", Label.LEFT),
                new Label("Width of Board    ", Label.LEFT),
                new Label("Height of Board   ", Label.LEFT),
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
        int height = 30;
        Font tt = new Font("Monospaced", Font.PLAIN, 12);
        for (int i = 0; i < scrollbars.length; i++) {
            int y = 0;
            final int index = i;
            labels[i].setFont(tt);
            scrollbars[i].setSize(2000, 10);
            values[i].setFont(tt);
            values[i].setText((scrollbars[index].getValue() < 10 ? " " : "") + scrollbars[i].getValue());
            scrollbars[i].addAdjustmentListener(e -> values[index].setText((scrollbars[index].getValue() < 10 ? " " : "") + scrollbars[index].getValue()));
            Panel t = new Panel(new FlowLayout(FlowLayout.CENTER, 20, 0));
            t.add(labels[i], BorderLayout.WEST);
            t.add(scrollbars[i], BorderLayout.CENTER);
            t.add(values[i], BorderLayout.EAST);
            parameters.add(t);
        }

        f.setBounds(500, 350, intercept * 4 + labelWidth + scrollbarWidth + valueWidth, height * 16);


        Panel bottom = new Panel(new FlowLayout());
        Button b = new Button("OK");
        Button d = new Button("Reset");
        Button q = new Button("Quit");

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

            Set<Integer> set = new HashSet<>();

            for (int i = 0; i < 8; i++) {
                if (!checkboxes[i].getState())
                    set.add(i + 8);
            }

            g = new TetrisGame(speed, rol, score, w, h, set);
            f.dispose();
        });

        q.addActionListener(e -> System.exit(0));

        bottom.add(b);
        bottom.add(d);
        bottom.add(q);


        Panel x = new Panel(new GridLayout(2, 8));
        for (int i = 8; i <= 15; i++) {
            Extension ext = new Extension(new Shape(Shape.ShapeType.values()[i]), 15, 80, 46);
            Panel tp = new Panel();
            tp.add(ext);
            x.add(tp);
        }


        for (int i = 0; i < 8; i++) {
            checkboxes[i] = new Checkbox();
            Panel tp = new Panel();
            tp.add(checkboxes[i]);
            x.add(tp);
        }

        Label pc = new Label("Specifying game parameters", Label.CENTER);
        pc.setFont(tt);
        center.add(pc);

        center.add(new Label());

        center.add(parameters);

        center.add(new Label());
        center.add(new Label());
        center.add(new Label());

        Label c = new Label("Choose extended version of Shapes", Label.CENTER);
        c.setFont(tt);
        center.add(c);

        center.add(new Label());

        center.add(x);

        center.add(new Label());

        f.add(center, BorderLayout.CENTER);
        f.add(bottom, BorderLayout.SOUTH);
        f.add(new Label(), BorderLayout.NORTH);
        f.add(new Panel(), BorderLayout.EAST);
        f.add(new Panel(), BorderLayout.WEST);
        f.setVisible(true);
    }
}
