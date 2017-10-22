import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class MainArea extends Canvas implements MouseMotionListener, MouseListener, MouseWheelListener {

    private static List<Color> palette = Collections.unmodifiableList(Arrays.asList(
            Color.white,
//            case I: return
            new Color(0, 176, 240),
//            case J: return
            new Color(0, 112, 192),
//            case L: return
            new Color(255, 0, 0),
//            case S: return
            new Color(255, 255, 0),
//            case T: return
            new Color(255, 192, 0),
//            case Z: return
            new Color(112, 48, 160),
//            case C: return
            new Color(0, 176, 80),


            new Color(165, 165, 165),
            new Color(159, 205, 99),
            new Color(208, 152, 150),
            new Color(214, 114, 46),
            new Color(198, 212, 161),
            new Color(146, 137, 90),
            new Color(29, 54, 90),
            new Color(70, 131, 152)
    ));
    private Point mainArea;
    private Point quitPosition;
    private int squareLen;
    private boolean pause = false;
    private boolean cheat = false;

    private Board board;
    private Point rightArea;

    public MainArea(Board board) {
        this.board = board;
        new Thread(board).start();

        addMouseMotionListener(this);
        addMouseListener(this);
        addMouseWheelListener(this);
    }


    public void paint(Graphics g)

    {


        Dimension d = getSize();
        int maxX = d.width - 1, maxY = d.height - 1;

        squareLen = Math.min(d.width / (board.w + 10), d.height / (board.h + 9));

        int leftbound = (d.width - (board.w + 6) * squareLen) / (2 * squareLen);
        int upperbound = (d.height - (board.h - 1) * squareLen) / (2 * squareLen);

        // Set main area position.
        mainArea = new Point((leftbound - 1) * squareLen, upperbound * squareLen);
        // Set right area.
        rightArea = new Point((leftbound + board.w) * squareLen, (upperbound + 1) * squareLen);

        this.drawScore(g, leftbound, upperbound);

        this.drawBoard(g, mainArea, leftbound, upperbound);

        this.drawShape(g, mainArea, board.getCurrentShape());

        this.drawShape(g, rightArea, board.getNextShape());

        this.drawPause(g);

        this.drawQuit(g, leftbound, upperbound);

        this.repaint();
    }


    private void drawShape(Graphics g, Point origin, Shape shape) {

        Color color = board.over ? Color.GRAY : getColor(shape.getType());

        for (Shape.Coordinate cube : shape.getShape()) {
            this.drawRect(g, origin, cube.x, cube.y, color);
        }
    }

    private void drawBoard(Graphics g, Point origin, int leftbound, int upperbound) {

        g.drawRect(leftbound * squareLen, upperbound * squareLen, (board.w - 2) * squareLen, (board.h - 1) * squareLen);
        g.drawRect((leftbound + board.w) * squareLen, upperbound * squareLen, 6 * squareLen, 4 * squareLen);

        int i = 0;
        for (int[] line : board.area) {
            for (int j = 0; j < board.w; j++) {
                if (line[j] == 0 || line[j] == Integer.MAX_VALUE)
                    continue;
                this.drawRect(g, origin, j, i,
                        board.over ? Color.GRAY : getColor(Shape.ShapeType.values()[line[j]]));
            }
            i++;
        }
    }

    private void drawQuit(Graphics g, int leftbound, int upperbound) {
        // Add quit button.
        this.quitPosition = new Point((leftbound + board.w) * squareLen, (upperbound + board.h - 2) * squareLen);
        QuitButton.QuitButton(g, quitPosition, squareLen);
    }

    private void drawPause(Graphics g) {
        // Add pause information.
        if (pause) {
            Point pausePosition = new Point(mainArea.x + ((board.w - 6) / 2) * squareLen, mainArea.y + (board.h - 3) / 2 * squareLen);
            AddPause pause = new AddPause();
            pause.add(g, pausePosition, squareLen);
        }
    }

    private void drawScore(Graphics g, int leftbound, int upperbound) {
        // Add text.
        AddText text = new AddText();
        // Level information.
        Point levelPosition = new Point((leftbound + board.w) * squareLen, (upperbound + 8) * squareLen);
        text.setString(g, "Level:", levelPosition, squareLen);
        text.setValue(g, board.level, levelPosition, squareLen);

        // Lines information.
        Point linesPosition = new Point((leftbound + board.w) * squareLen, (upperbound + 10) * squareLen);
        text.setString(g, "Lines:", linesPosition, squareLen);
        text.setValue(g, board.line, linesPosition, squareLen);

        // Scores information.
        Point scoresPosition = new Point((leftbound + board.w) * squareLen, (upperbound + 12) * squareLen);
        text.setString(g, "Score:", scoresPosition, squareLen);
        text.setValue(g, board.score, scoresPosition, squareLen);
    }

    private void drawRect(Graphics g, Point origin, int x, int y, Color color) {
        g.setColor(color);
        g.fillRect(origin.x + x * squareLen, origin.y + y * squareLen, squareLen, squareLen);
        g.setColor(Color.black);
        g.drawRect(origin.x + x * squareLen, origin.y + y * squareLen, squareLen, squareLen);
    }

    static Color getColor(Shape.ShapeType color) {
        return MainArea.palette.get(color.ordinal());
    }

    public boolean isInsideShape(Point test, List<Shape.Coordinate> coordinates) {
        int i;
        int j;
        boolean result = false;
        for (Shape.Coordinate c : coordinates) {
            Point[] ps = new Point[4];
            ps[0] = new Point(c.x * squareLen + mainArea.x, c.y * squareLen + mainArea.y);
            ps[1] = new Point((c.x + 1) * squareLen + mainArea.x, c.y * squareLen + mainArea.y);
            ps[2] = new Point(c.x * squareLen + mainArea.x, (c.y + 1) * squareLen + mainArea.y);
            ps[3] = new Point((c.x + 1) * squareLen + mainArea.x, (c.y + 1) * squareLen + mainArea.y);
            for (i = 0, j = ps.length - 1; i < ps.length; j = i++) {
                if ((ps[i].y > test.y) != (ps[j].y > test.y) &&
                        (test.x < (ps[j].x - ps[i].x) * (test.y - ps[i].y) / (ps[j].y - ps[i].y) + ps[i].x)) {
                    result = !result;
                }
            }
        }
        return result;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (board.over)
            return;
        int x = e.getX(), y = e.getY();
        boolean MouseInMainArea =
                x >= mainArea.getX() + squareLen &&
                        x <= mainArea.getX() + (board.w - 1) * squareLen &&
                        y >= mainArea.getY() &&
                        y <= mainArea.getY() + (board.h - 1) * squareLen;

        if (pause != MouseInMainArea) {
            pause = MouseInMainArea;
            if (pause)
                this.board.pause();
            else {
                this.board.resume();
                cheat = false;
            }
        }

        if (cheat)
            return;

        boolean isCheated = this.isInsideShape(e.getPoint(), this.board.currentShape.getShape());
        if (isCheated) {
            board.cheat();
            cheat = true;
        }
        this.repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        int x = e.getX(), y = e.getY();

        if (!e.isConsumed()) {

            if (x >= quitPosition.getX() && x <= quitPosition.getX() + squareLen * 14 / 5 &&
                    y >= quitPosition.getY() && y <= quitPosition.getY() + squareLen * 3 / 2) {
                System.exit(0);
            }
            if (x < mainArea.getX() || x > mainArea.getX() + 10 * squareLen ||
                    y < mainArea.getY() || y > mainArea.getY() + 20 * squareLen) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    this.board.moveLeft();

                } else if (e.getButton() == MouseEvent.BUTTON3)
                    this.board.moveRight();
            }
            e.consume();
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        e.consume();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        e.consume();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        e.consume();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        e.consume();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        e.consume();
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        if (pause) {
            e.consume();
        }
        if (!e.isConsumed()) {
            int num = e.getUnitsToScroll();

            if (num > 0)
                for (int i = 0; i < num; i++)
                    this.board.rotateClockwise();
            else
                for (int i = 0; i < Math.abs(num); i++)
                    this.board.rotateCounterClockwise();
            e.consume();
        }

    }
}
