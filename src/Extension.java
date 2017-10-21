import java.awt.*;

public class Extension extends Canvas {

    Shape shape;
    int squareLen;

    public Extension(Shape shape, int squareLen, int width, int height) {
        this.shape = shape;
        this.squareLen = squareLen;
        setSize(width, height);
    }

    private void drawShape(Graphics g) {

        Color color = MainArea.getColor(shape.getType());

        for (Shape.Coordinate cube : shape.getShape()) {
            this.drawRect(g, new Point(0, 0), cube.x, cube.y, color);
        }
    }

    private void drawRect(Graphics g, Point origin, int x, int y, Color color) {
        g.setColor(color);
        g.fillRect((origin.x + x) * squareLen, (origin.y + y) * squareLen, squareLen, squareLen);
        g.setColor(Color.black);
        g.drawRect((origin.x + x) * squareLen, (origin.y + y) * squareLen, squareLen, squareLen);
    }

    @Override
    public void paint(Graphics g) {
        drawShape(g);
    }
}
