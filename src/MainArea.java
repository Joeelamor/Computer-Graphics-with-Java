package Q2;

import java.awt.*;
import java.awt.event.*;

public class MainArea extends Canvas implements MouseMotionListener, MouseListener {

    public Point startPause;
    public Point quitPosition;
    public int squreLen;
    public boolean hide = true;

    public void paint(Graphics g)

    {
        Dimension d = getSize();
        int maxX = d.width - 1, maxY = d.height - 1;
        squreLen = Math.min(maxX / 50, maxY / 30);

        // Set main area positon.
        startPause = new Point(16 * squreLen, 5 * squreLen);
        g.drawRect(16 * squreLen, 5 * squreLen, 10 * squreLen, 20 * squreLen);

        // Draw top green shape.
        Point originTop = new Point(20 * squreLen, 7 * squreLen);
        ShapeCube cube = new ShapeCube();
        cube.draw(g, originTop, squreLen);

        // Draw bottom yellow shape.
        Point originBottomS = new Point(22 * squreLen, 24 * squreLen);
        ShapeS shapeS = new ShapeS();
        shapeS.draw(g, originBottomS, squreLen);

        // Draw bottem blue shape.
        Point originBottomJ = new Point(24 * squreLen, 24 * squreLen);
        ShapeJ shapeJ = new ShapeJ();
        shapeJ.draw(g, originBottomJ, squreLen);

        // Set right area.
        g.drawRect(27 * squreLen, 6 * squreLen, 5 * squreLen, 4 * squreLen);

        // Draw right red shape.
        Point originRightL = new Point(28 * squreLen, 8 * squreLen);
        ShapeL shapeL = new ShapeL();
        shapeL.draw(g, originRightL, squreLen);

        // Add text.
        AddText text = new AddText();
        // Level information.
        Point levelPosition = new Point(27 * squreLen, 14 * squreLen);
        text.setString(g, "Level:", levelPosition, squreLen);
        text.setValue(g, 1, levelPosition, squreLen);

        // Lines information.
        Point linesPosition = new Point(27 * squreLen, 16 * squreLen);
        text.setString(g, "Lines:", linesPosition, squreLen);
        text.setValue(g, 0, linesPosition, squreLen);

        // Scores information.
        Point scoresPosition = new Point(27 * squreLen, 18 * squreLen);
        text.setString(g, "Score:", scoresPosition, squreLen);
        text.setValue(g, 0, scoresPosition, squreLen);

        // Add pause information.
        if (!hide) {
            Point pausePosition = new Point(18 * squreLen, 14 * squreLen);
            AddPause pause = new AddPause();
            pause.add(g, pausePosition, squreLen);
        }


        // Add quit button.
        quitPosition = new Point(27 * squreLen, 24 * squreLen);
        QuitButton button = new QuitButton();
        button.QuitButton(g, quitPosition, squreLen);

        addMouseMotionListener(this);
        addMouseListener(this);
    }


    @Override
    public void mouseMoved(MouseEvent e) {
        int x = e.getX(), y = e.getY();
        if (x >= startPause.getX() && x <= startPause.getX() + 10 * squreLen &&
                y >= startPause.getY() && y <= startPause.getY() + 20 * squreLen) {
            hide = false;
            this.repaint();
        } else {
            hide = true;
            this.repaint();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX(), y = e.getY();
        if (x >= quitPosition.getX() && x <= quitPosition.getX() + squreLen * 14 / 5 &&
                y >= quitPosition.getY() && y <= quitPosition.getY() + squreLen * 3 / 2) {
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
