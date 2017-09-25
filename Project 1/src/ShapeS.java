package Q2;

import java.awt.*;

public class ShapeS extends ShapeTransformation {
    public void draw(Graphics g, Point origin, int length) {
        g.setColor(new Color(255, 255, 0));
        g.fillRect(origin.x + length, origin.y -length, 2 * length, length);
        g.fillRect(origin.x, origin.y, 2 * length, length);
        g.setColor(Color.black);
        g.drawRect(origin.x + length, origin.y - length, length, length);
        g.drawRect(origin.x, origin.y, length, length);
        g.drawRect(origin.x + 2 * length, origin.y - length, length, length);
        g.drawRect(origin.x + length, origin.y, length, length);
    }
}
