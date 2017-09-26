package Q2;

import java.awt.*;

public class ShapeL extends ShapeTransformation {
    public void draw(Graphics g, Point origin, int length) {
        g.setColor(new Color(255, 0, 0));
        g.fillRect(origin.x, origin.y, 3 * length, length);
        g.fillRect(origin.x + 2 * length, origin.y - length, length, length);
        g.setColor(Color.black);
        g.drawRect(origin.x, origin.y, length, length);
        g.drawRect(origin.x + length, origin.y, length, length);
        g.drawRect(origin.x + 2 * length, origin.y, length, length);
        g.drawRect(origin.x + 2 * length, origin.y - length, length, length);
    }
}
