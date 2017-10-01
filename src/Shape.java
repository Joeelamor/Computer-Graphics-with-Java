//import java.awt.*;

import java.util.Arrays;
import java.util.Random;
import java.util.TimerTask;

public class Shape {

    public enum ShapeType {
        NULL, I, J, L, S, T, Z, C
    }

    class Coordinate {
        int x;
        int y;

        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }

    //    Color color;
    ShapeType type;
    int[][] shape = new int[4][2];

    public Shape() {
    }

    public Shape(ShapeType type, int[][] shape) {
        this.type = type;
        this.shape = shape;
    }

    public static Shape getRandomShape() {
        Random random = new Random();
        switch (random.nextInt(7)) {
            case 0:
                return (new ShapeCube());
            case 1:
                return (new ShapeI());
            case 2:
                return (new ShapeJ());
            case 3:
                return (new ShapeL());
            case 4:
                return (new ShapeZ());
            case 5:
                return (new ShapeS());
            case 6:
                return (new ShapeT());
        }
        return null;
    }

    public ShapeType getType() {
        return type;
    }

    public int[][] getShape() {
        return shape;
    }

    public Shape moveLeft() {
        int[][] location = new int[4][2];

        location[0][0] =  this.shape[0][0] - 1;
        location[0][1] =  this.shape[0][1];

        location[1][0] =  this.shape[1][0] - 1;
        location[1][1] =  this.shape[1][1];

        location[2][0] =  this.shape[2][0] - 1;
        location[2][1] =  this.shape[2][1];

        location[3][0] =  this.shape[3][0] - 1;
        location[3][1] =  this.shape[3][1];

        return new Shape(this.type, location);
    }

    public Shape moveRight() {
        int[][] location = new int[4][2];

        location[0][0] =  this.shape[0][0] + 1;
        location[0][1] =  this.shape[0][1];

        location[1][0] =  this.shape[1][0] + 1;
        location[1][1] =  this.shape[1][1];

        location[2][0] =  this.shape[2][0] + 1;
        location[2][1] =  this.shape[2][1];

        location[3][0] =  this.shape[3][0] + 1;
        location[3][1] =  this.shape[3][1];

        return new Shape(this.type, location);
    }

    public void rotate() {
    }

    public Shape moveDown() {
        int[][] location = new int[4][2];

        location[0][0] =  this.shape[0][0];
        location[0][1] =  this.shape[0][1] + 1;

        location[1][0] =  this.shape[1][0];
        location[1][1] =  this.shape[1][1] + 1;

        location[2][0] =  this.shape[2][0];
        location[2][1] =  this.shape[2][1] + 1;

        location[3][0] =  this.shape[3][0];
        location[3][1] =  this.shape[3][1] + 1;

        return new Shape(this.type, location);
    }

    @Override
    public String toString() {
        return "Shape{" +
                "type=" + type +
                ", shape=" + Arrays.toString(shape) +
                '}';
    }
}



