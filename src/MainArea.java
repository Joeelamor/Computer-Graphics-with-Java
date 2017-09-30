import java.awt.*;
import java.awt.event.*;

public class MainArea extends Canvas implements MouseMotionListener, MouseListener {

    private Point startMainErea;
    private Point startRightErea;
    private Point quitPosition;
    private int squareLen;
    private boolean pause = false;
    private int[][]area = new int[20][10];
    private Shape currentShape = null, nextShape = null;



    public void paint(Graphics g)

    {
        Dimension d = getSize();
        int maxX = d.width - 1, maxY = d.height - 1;
        squareLen = Math.min(maxX / 50, maxY / 30);

        // Set main area positon.
        startMainErea = new Point(16 * squareLen, 5 * squareLen);
        g.drawRect(16 * squareLen, 5 * squareLen, 10 * squareLen, 20 * squareLen);

        // Set right area.
        startRightErea = new Point(25 * squareLen, 6 * squareLen);
        g.drawRect(27 * squareLen, 5 * squareLen, 6 * squareLen, 4 * squareLen);


        Util.getRandomShape(g, area, startRightErea, squareLen);


        if (currentShape == null)
            currentShape = Util.getCurrentShape();
//        currentShape.reset(g, startMainErea, squareLen);
//        currentShape.draw();
        this.drawShape(g, startMainErea, currentShape.getShape(), currentShape.getColor());


        if (nextShape == null)
            nextShape = Util.getNextShape();
//        nextShape.reset(g, startRightErea, squareLen);
        this.drawShape(g, startRightErea, nextShape.getShape(), nextShape.getColor());


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
    }

    public void drawShape(Graphics g, Point origin, int[][] shape, Color color){
        for (int []arr : shape) {
            g.setColor(color);
            g.fillRect(origin.x + arr[0] * squareLen, origin.y + arr[1] * squareLen, squareLen, squareLen);
            g.setColor(Color.black);
            g.drawRect(origin.x + arr[0] * squareLen, origin.y + arr[1] * squareLen, squareLen, squareLen);
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
}
