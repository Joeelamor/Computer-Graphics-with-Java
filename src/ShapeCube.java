package Q2;

import java.awt.*;

public class ShapeCube extends ShapeTransformation {
    public void draw(Graphics g, Point origin, int length) {
        g.setColor(new Color(0, 176, 80));
        g.fillRect(origin.x, origin.y, 2 * length, 2 * length);
        g.setColor(Color.black);
        g.drawRect(origin.x, origin.y, length, length);
        g.drawRect(origin.x + length, origin.y, length, length);
        g.drawRect(origin.x, origin.y + length, length, length);
        g.drawRect(origin.x + length, origin.y + length, length, length);
    }
}
