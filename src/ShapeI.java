import java.awt.*;

public class ShapeI extends Shape {
    public ShapeI() {
//        super(g, area, origin, length);
        this.shape[0][0] = 4;
        this.shape[0][1] = 0;

        this.shape[1][0] = 5;
        this.shape[1][1] = 0;

        this.shape[2][0] = 6;
        this.shape[2][1] = 0;

        this.shape[3][0] = 7;
        this.shape[3][1] = 0;
//        this.color = new Color(0, 176, 240);

        this.type = ShapeType.I;
    }

}
