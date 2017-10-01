import java.awt.*;

public class ShapeI extends Shape {
    public ShapeI(int[][] shape) {
        super(shape);

        this.type = ShapeType.I;
    }

    public ShapeI() {
        this.shape[0][0] = 4;
        this.shape[0][1] = 0;

        this.shape[1][0] = 5;
        this.shape[1][1] = 0;

        this.shape[2][0] = 6;
        this.shape[2][1] = 0;

        this.shape[3][0] = 7;
        this.shape[3][1] = 0;

        this.type = ShapeType.I;
    }

    @Override
    public Shape rotateClockwise() {

        int[][] location = new int[4][2];

        location[0][0] =  this.shape[0][0] + 1;
        location[0][1] =  this.shape[0][1] - 1;

        location[1][0] =  this.shape[1][0];
        location[1][1] =  this.shape[1][1];

        location[2][0] =  this.shape[2][0] - 1;
        location[2][1] =  this.shape[2][1] + 1;

        location[3][0] =  this.shape[3][0] - 2;
        location[3][1] =  this.shape[3][1] + 2;

        return new ShapeI(location);
    }

    @Override
    public Shape rotateCounterClockwise() {
        return null;
    }
}
