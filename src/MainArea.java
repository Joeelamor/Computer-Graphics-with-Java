import java.awt.*;
import java.awt.event.*;

public class MainArea extends Canvas implements MouseMotionListener, MouseListener, MouseWheelListener {

    private long mouseClickTime = 0;
    private long mouseScrollTime = 0;

    private Point startMainErea;
    private Point startRightErea;
    private Point quitPosition;
    private int squareLen;
    private boolean pause = false;
    private Shape currentShape = null, nextShape = null;

    private Board board;
    private Thread boardThread;

    public MainArea(Board board) {
        this.board = board;
//        this.board.run();
        this.boardThread = new Thread(board);
        this.boardThread.start();
    }


    public void paint(Graphics g)

    {
        Dimension d = getSize();
        int maxX = d.width - 1, maxY = d.height - 1;
        squareLen = Math.min(maxX / 50, maxY / 30);

        // Set main area positon.
        startMainErea = new Point(15 * squareLen, 5 * squareLen);
        g.drawRect(16 * squareLen, 5 * squareLen, 10 * squareLen, 20 * squareLen);

        // Set right area.
        startRightErea = new Point(24 * squareLen, 6 * squareLen);
        g.drawRect(27 * squareLen, 5 * squareLen, 6 * squareLen, 4 * squareLen);

        this.drawBoard(g, startMainErea);


//        Util.getRandomShape(g, area, startRightErea, squareLen);


//        if (currentShape == null)
//            currentShape = Util.getCurrentShape();
//        currentShape.reset(g, startMainErea, squareLen);
//        currentShape.draw();
        this.currentShape = board.getCurrentShape();
        this.drawShape(g, startMainErea, currentShape.getShape(), currentShape.getType());


//        if (nextShape == null)
//            nextShape = Util.getNextShape();
//        nextShape.reset(g, startRightErea, squareLen);
        this.nextShape = board.getNextShape();
        this.drawShape(g, startRightErea, nextShape.getShape(), nextShape.getType());


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

        // Add pause information.
        if (pause) {
            Point pausePosition = new Point(18 * squareLen, 14 * squareLen);
            AddPause pause = new AddPause();
            pause.add(g, pausePosition, squareLen);
        }


        // Add quit button.
        quitPosition = new Point(27 * squareLen, 24 * squareLen);
        QuitButton button = new QuitButton();
        button.QuitButton(g, quitPosition, squareLen);

        addMouseMotionListener(this);
        addMouseListener(this);
        addMouseWheelListener(this);

        this.repaint();
    }

    public void drawShape(Graphics g, Point origin, Shape.Coordinate[] cubes, Shape.ShapeType type){

        Color color;

        color = this.getColor(type);

        for (Shape.Coordinate cube : cubes) {
            this.drawRect(g, origin, cube.x, cube.y, color);
        }
    }

    private void drawBoard(Graphics g, Point origin) {
        for (int i = 0; i < 21; i++) {
            for (int j = 0; j < 12; j++) {
                if (board.area[i][j] == 0 || board.area[i][j] == Integer.MAX_VALUE)
                    continue;
                this.drawRect(g, origin, j, i, getColor(Shape.ShapeType.values()[board.area[i][j]]));
            }
        }
    }

    private void drawRect(Graphics g, Point origin, int x, int y, Color color) {
        g.setColor(color);
        g.fillRect(origin.x + x * squareLen, origin.y + y * squareLen, squareLen, squareLen);
        g.setColor(Color.black);
        g.drawRect(origin.x + x * squareLen, origin.y + y * squareLen, squareLen, squareLen);
    }

    private Color getColor(Shape.ShapeType color) {
        switch (color) {
            case C: return new Color(0, 176, 80);
            case I: return new Color(0, 176, 240);
            case J: return new Color(0, 112, 192);
            case L: return new Color(255, 0, 0);
            case S: return new Color(255, 255, 0);
            case T: return new Color(255, 192, 0);
            case Z: return new Color(112, 48, 160);
            default: return Color.black;
        }
    }


    @Override
    public void mouseMoved(MouseEvent e) {
        int x = e.getX(), y = e.getY();
        boolean MouseInMainAre = x >= startMainErea.getX() && x <= startMainErea.getX() + 10 * squareLen &&
                y >= startMainErea.getY() && y <= startMainErea.getY() + 20 * squareLen;
//        if (x >= startMainErea.getX() && x <= startMainErea.getX() + 10 * squareLen &&
//                y >= startMainErea.getY() && y <= startMainErea.getY() + 20 * squareLen) {
//            pause = false;
//            this.repaint();
//        } else {
//            pause = true;
//        }

        if (pause != MouseInMainAre) {
            pause = MouseInMainAre;
            if (pause) {
//                this.board.
                this.board.pause();

            } else
                this.board.resume();
            this.repaint();
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {

        int x = e.getX(), y = e.getY();
        if (x >= quitPosition.getX() && x <= quitPosition.getX() + squareLen * 14 / 5 &&
                y >= quitPosition.getY() && y <= quitPosition.getY() + squareLen * 3 / 2) {
            System.exit(0);
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

        if (this.mouseClickTime < e.getWhen()) {
            int x = e.getX(), y = e.getY();
            if (x < startMainErea.getX() || x > startMainErea.getX() + 10 * squareLen ||
                    y < startMainErea.getY() || y > startMainErea.getY() + 20 * squareLen) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    this.board.moveLeft();

                } else if (e.getButton() == MouseEvent.BUTTON3)
                    this.board.moveRight();
            }
            this.mouseClickTime = e.getWhen();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        if (this.mouseScrollTime < e.getWhen()) {
            int num = e.getUnitsToScroll();

            System.out.println(num);
            if (num > 0)
                for (int i = 0; i < num; i++)
                    this.board.rotateClockwise();
            else
                for (int i = 0; i < Math.abs(num); i++)
                    this.board.rotateCounterClockwise();
            this.mouseScrollTime = e.getWhen();
        }
    }
}
