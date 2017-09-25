package Q2;

import java.awt.*;

public class ShapeJ extends ShapeTransformation {
    public void draw(Graphics g, Point origin, int length) {
        g.setColor(new Color(0, 112, 192));
        g.fillRect(origin.x + length, origin.y - 2 * length, length, 2 * length);
        g.fillRect(origin.x, origin.y, 2 * length, length);
        g.setColor(Color.black);
        g.drawRect(origin.x + length, origin.y - 2 * length, length, length);
        g.drawRect(origin.x, origin.y, length, length);
        g.drawRect(origin.x + length, origin.y - length, length, length);
        g.drawRect(origin.x + length, origin.y, length, length);
    }
}
