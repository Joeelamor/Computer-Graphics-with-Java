import java.awt.*;

public class ShapeCube extends Shape {
    public ShapeCube() {
        this.shape[0][0] = 5;
        this.shape[0][1] = 0;

        this.shape[1][0] = 6;
        this.shape[1][1] = 0;

        this.shape[2][0] = 5;
        this.shape[2][1] = 1;

        this.shape[3][0] = 6;
        this.shape[3][1] = 1;

        this.type = ShapeType.C;
    }

    public ShapeCube(int[][] shape) {
        super(shape);
        this.type = ShapeType.C;
    }

    @Override
    public Shape rotateClockwise() {
        return null;
    }

    @Override
    public Shape rotateCounterClockwise() {
        return null;
    }
}
