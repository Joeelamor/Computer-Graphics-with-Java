import java.awt.*;

public class ShapeZ extends Shape {
    public ShapeZ(Graphics g, int[][] area, Point origin, int length) {
//        super(g, area, origin, length);
        this.shape[0][0] = 3;
        this.shape[0][1] = 0;

        this.shape[1][0] = 4;
        this.shape[1][1] = 0;

        this.shape[2][0] = 4;
        this.shape[2][1] = 1;

        this.shape[3][0] = 5;
        this.shape[3][1] = 1;
        this.color = new Color(112, 48, 160);
    }
}