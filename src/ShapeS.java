import java.awt.*;

public class ShapeS extends Shape {
    public ShapeS(int[][] shape) {
        super(shape);
    }

    @Override
    public Shape rotateClockwise() {
        return null;
    }

    @Override
    public Shape rotateCounterClockwise() {
        return null;
    }

    public ShapeS() {
        this.shape[0][0] = 4;
        this.shape[0][1] = 1;

        this.shape[1][0] = 5;
        this.shape[1][1] = 0;

        this.shape[2][0] = 5;
        this.shape[2][1] = 1;

        this.shape[3][0] = 6;
        this.shape[3][1] = 0;

        this.type = ShapeType.S;
    }
}
