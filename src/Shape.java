//import java.awt.*;

import java.util.Random;

public abstract class Shape {

    public enum ShapeType {
        NULL, I, J, L, S, T, Z, C
    }

    static class Coordinate {
        int x;
        int y;

        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "(" + x + "," + y + ")";
        }
    }


    //    Color color;
    ShapeType type;
    int status;
    Coordinate base;
    Coordinate[][] coordinates;

    public Shape() {
    }

    public Shape(Coordinate base) {
        this.status = 0;
        this.base = base;
    }

    public Shape(ShapeType type, Coordinate base) {
        this.type = type;
        this.base = base;
    }

    static Shape shapeOf(ShapeType type, Coordinate base, int status) {
        switch (type) {
            case C:
                return new ShapeC(base, status);
            case I:
                return new ShapeI(base, status);
            case J:
                return new ShapeJ(base, status);
            case L:
                return new ShapeL(base, status);
            case S:
                return new ShapeS(base, status);
            case T:
                return new ShapeT(base, status);
            case Z:
                return new ShapeZ(base, status);
        }
        return new ShapeC(base, status);
    }

    public static Shape getRandomShape() {
        Random random = new Random();
        switch (random.nextInt(7)) {
            case 0:
                return (new ShapeC());
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

    public Coordinate[] getShape() {
//        return shape;
        return new Coordinate[]{
                new Coordinate(this.base.x + this.coordinates[status][0].x, this.base.y + this.coordinates[status][0].y),
                new Coordinate(this.base.x + this.coordinates[status][1].x, this.base.y + this.coordinates[status][1].y),
                new Coordinate(this.base.x + this.coordinates[status][2].x, this.base.y + this.coordinates[status][2].y),
                new Coordinate(this.base.x + this.coordinates[status][3].x, this.base.y + this.coordinates[status][3].y),
        };
    }


    public Shape moveLeft() {
        return Shape.shapeOf(this.type, new Coordinate(this.base.x - 1, this.base.y), this.status);
    }

    public Shape moveRight() {
        return Shape.shapeOf(this.type, new Coordinate(this.base.x + 1, this.base.y), this.status);
    }

    public Shape rotateClockwise() {
        int status = (this.status + 1) % this.coordinates.length;
        return Shape.shapeOf(this.type, base, status);
    }

    public Shape rotateCounterClockwise() {

        int status = (this.status - 1);
        if (status < 0)
            status = this.coordinates.length - 1;
        return Shape.shapeOf(this.type, base, status);
    }

    public Shape moveDown() {
        return Shape.shapeOf(this.type, new Coordinate(this.base.x, this.base.y + 1), this.status);
    }

    @Override
    public String toString() {
        return "Shape{" +
                "type=" + type +
                ", status=" + status +
                ", base=" + base +
                '}';
    }
}



