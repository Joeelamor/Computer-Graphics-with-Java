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
            new Color(0, 176, 80)
    ));
    private Point startMainArea;
    private Point quitPosition;
    private int squareLen;
    private boolean pause = false;

    private Board board;
    private Point startRightArea;

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
        squareLen = Math.min(maxX / 50, maxY / 30);

        // Set main area position.
        startMainArea = new Point(15 * squareLen, 5 * squareLen);
        // Set right area.
        startRightArea = new Point(24 * squareLen, 6 * squareLen);

        this.drawScore(g);

        this.drawBoard(g, startMainArea);

        this.drawShape(g, startMainArea, board.getCurrentShape());

        this.drawShape(g, startRightArea, board.getNextShape());

        this.drawPause(g);

        this.drawQuit(g);

        this.repaint();
    }

    private void drawShape(Graphics g, Point origin, Shape shape) {

        Color color = this.getColor(shape.getType());

        for (Shape.Coordinate cube : shape.getShape()) {
            this.drawRect(g, origin, cube.x, cube.y, color);
        }
    }

    private void drawBoard(Graphics g, Point origin) {

        g.drawRect(16 * squareLen, 5 * squareLen, 10 * squareLen, 20 * squareLen);
        g.drawRect(27 * squareLen, 5 * squareLen, 6 * squareLen, 4 * squareLen);

        for (int i = 0; i < 21; i++) {
            for (int j = 0; j < 12; j++) {
                if (board.area[i][j] == 0 || board.area[i][j] == Integer.MAX_VALUE)
                    continue;
                this.drawRect(g, origin, j, i, getColor(Shape.ShapeType.values()[board.area[i][j]]));
            }
        }
    }

    private void drawQuit(Graphics g) {
        // Add quit button.
        this.quitPosition = new Point(27 * squareLen, 24 * squareLen);
        QuitButton.QuitButton(g, quitPosition, squareLen);
    }

    private void drawPause(Graphics g) {
        // Add pause information.
        if (pause) {
            Point pausePosition = new Point(18 * squareLen, 14 * squareLen);
            AddPause pause = new AddPause();
            pause.add(g, pausePosition, squareLen);
        }
    }

    private void drawScore(Graphics g) {
        // Add text.
        AddText text = new AddText();
        // Level information.
        Point levelPosition = new Point(27 * squareLen, 14 * squareLen);
        text.setString(g, "Level:", levelPosition, squareLen);
        text.setValue(g, 1, levelPosition, squareLen);

        // Lines information.
        Point linesPosition = new Point(27 * squareLen, 16 * squareLen);
        text.setString(g, "Lines:", linesPosition, squareLen);
        text.setValue(g, 0, linesPosition, squareLen);

        // Scores information.
        Point scoresPosition = new Point(27 * squareLen, 18 * squareLen);
        text.setString(g, "Score:", scoresPosition, squareLen);
        text.setValue(g, 0, scoresPosition, squareLen);
    }

    private void drawRect(Graphics g, Point origin, int x, int y, Color color) {
        g.setColor(color);
        g.fillRect(origin.x + x * squareLen, origin.y + y * squareLen, squareLen, squareLen);
        g.setColor(Color.black);
        g.drawRect(origin.x + x * squareLen, origin.y + y * squareLen, squareLen, squareLen);
    }

    private Color getColor(Shape.ShapeType color) {
        return MainArea.palette.get(color.ordinal());
    }


    @Override
    public void mouseMoved(MouseEvent e) {
        int x = e.getX(), y = e.getY();
        boolean MouseInMainArea = x >= startMainArea.getX() + squareLen && x <= startMainArea.getX() + squareLen + 10 * squareLen &&
                y >= startMainArea.getY() && y <= startMainArea.getY() + 20 * squareLen;

        if (pause != MouseInMainArea) {
            pause = MouseInMainArea;
            if (pause)
                this.board.pause();
            else
                this.board.resume();
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
            if (x < startMainArea.getX() || x > startMainArea.getX() + 10 * squareLen ||
                    y < startMainArea.getY() || y > startMainArea.getY() + 20 * squareLen) {
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
