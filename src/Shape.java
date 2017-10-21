import java.util.*;

public class Shape {

    public enum ShapeType {
        NULL, I, J, L, S, T, Z, C, x1, x2, x3, x4, x5, x6, x7, x8
    }

    static final Coordinate[] bases = new Coordinate[]{
            new Coordinate(-1, -1),
            // Shape I
            new Coordinate(1, -1),
            // Shape J
            new Coordinate(1, 0),
            // Shape L
            new Coordinate(1, 0),
            // Shape S
            new Coordinate(1, -1),
            // Shape T
            new Coordinate(1, 0),
            // Shape Z
            new Coordinate(1, -1),
            // Shape Cu
            new Coordinate(1, -1),

            // X1
            new Coordinate(2, 0),
            // X2
            new Coordinate(1, 0),
            // X3
            new Coordinate(1, -1),
            // X4
            new Coordinate(2, 0),
            // X5
            new Coordinate(1, 0),
            // X6
            new Coordinate(2, 0),
            // X7
            new Coordinate(2,0),
            // X8
            new Coordinate(1, 0)
    };
    static final List<List<List<Coordinate>>> shapes = Collections.unmodifiableList(Arrays.asList(
            Collections.emptyList(),

            // Shape I
            Arrays.asList(
                    Arrays.asList(
                            new Coordinate(0, 1),
                            new Coordinate(1, 1),
                            new Coordinate(2, 1),
                            new Coordinate(3, 1)
                    ),
                    Arrays.asList(
                            new Coordinate(1, 0),
                            new Coordinate(1, 1),
                            new Coordinate(1, 2),
                            new Coordinate(1, 3)
                    )
            ),

            // Shape J
            Arrays.asList(
                    Arrays.asList(
                            new Coordinate(0, 0),
                            new Coordinate(0, 1),
                            new Coordinate(1, 1),
                            new Coordinate(2, 1)
                    ),
                    Arrays.asList(
                            new Coordinate(1, 0),
                            new Coordinate(2, 0),
                            new Coordinate(1, 1),
                            new Coordinate(1, 2)
                    ),
                    Arrays.asList(
                            new Coordinate(0, 1),
                            new Coordinate(1, 1),
                            new Coordinate(2, 1),
                            new Coordinate(2, 2)
                    ),
                    Arrays.asList(
                            new Coordinate(1, 0),
                            new Coordinate(1, 1),
                            new Coordinate(1, 2),
                            new Coordinate(0, 2)
                    )
            ),

            // Shape L
            Arrays.asList(
                    Arrays.asList(
                            new Coordinate(0, 1),
                            new Coordinate(1, 1),
                            new Coordinate(2, 1),
                            new Coordinate(2, 0)
                    ),
                    Arrays.asList(
                            new Coordinate(1, 0),
                            new Coordinate(1, 1),
                            new Coordinate(1, 2),
                            new Coordinate(2, 2)
                    ),
                    Arrays.asList(
                            new Coordinate(0, 1),
                            new Coordinate(0, 2),
                            new Coordinate(1, 1),
                            new Coordinate(2, 1)
                    ),
                    Arrays.asList(
                            new Coordinate(0, 0),
                            new Coordinate(1, 0),
                            new Coordinate(1, 1),
                            new Coordinate(1, 2)
                    )
            ),

            // Shape S
            Arrays.asList(
                    Arrays.asList(
                            new Coordinate(1, 1),
                            new Coordinate(2, 1),
                            new Coordinate(0, 2),
                            new Coordinate(1, 2)
                    ),
                    Arrays.asList(
                            new Coordinate(0, 0),
                            new Coordinate(0, 1),
                            new Coordinate(1, 1),
                            new Coordinate(1, 2)
                    )
            ),

            // Shape T
            Arrays.asList(
                    Arrays.asList(
                            new Coordinate(0, 1),
                            new Coordinate(1, 1),
                            new Coordinate(2, 1),
                            new Coordinate(1, 0)
                    ),
                    Arrays.asList(
                            new Coordinate(1, 0),
                            new Coordinate(1, 1),
                            new Coordinate(1, 2),
                            new Coordinate(2, 1)
                    ),
                    Arrays.asList(
                            new Coordinate(0, 1),
                            new Coordinate(1, 1),
                            new Coordinate(2, 1),
                            new Coordinate(1, 2)
                    ),
                    Arrays.asList(
                            new Coordinate(1, 0),
                            new Coordinate(1, 1),
                            new Coordinate(1, 2),
                            new Coordinate(0, 1)
                    )
            ),

            // Shape Z
            Arrays.asList(
                    Arrays.asList(
                            new Coordinate(0, 1),
                            new Coordinate(1, 1),
                            new Coordinate(1, 2),
                            new Coordinate(2, 2)
                    ),
                    Arrays.asList(
                            new Coordinate(2, 0),
                            new Coordinate(2, 1),
                            new Coordinate(1, 1),
                            new Coordinate(1, 2)
                    )
            ),

            // Shape Cu
            Arrays.asList(
                    Arrays.asList(
                            new Coordinate(1, 1),
                            new Coordinate(1, 2),
                            new Coordinate(2, 1),
                            new Coordinate(2, 2)
                    )
            ),

            // X1
            Arrays.asList(
                    Arrays.asList(
                            new Coordinate(1, 0),
                            new Coordinate(0, 1),
                            new Coordinate(1, 1)
                    ),
                    Arrays.asList(
                            new Coordinate(1, 0),
                            new Coordinate(2, 1),
                            new Coordinate(1, 1)
                    ),
                    Arrays.asList(
                            new Coordinate(2, 1),
                            new Coordinate(1, 2),
                            new Coordinate(1, 1)
                    ),
                    Arrays.asList(
                            new Coordinate(1, 2),
                            new Coordinate(0, 1),
                            new Coordinate(1, 1)
                    )
            ),

            // X2
            Arrays.asList(
                    Arrays.asList(
                            new Coordinate(0, 0),
                            new Coordinate(1, 1),
                            new Coordinate(2, 1)
                    ),
                    Arrays.asList(
//                            new Coordinate(1, 0),
                            new Coordinate(2, 0),
                            new Coordinate(1, 1),
                            new Coordinate(1, 2)
                    ),
                    Arrays.asList(
                            new Coordinate(0, 1),
                            new Coordinate(1, 1),
//                            new Coordinate(2, 1),
                            new Coordinate(2, 2)
                    ),
                    Arrays.asList(
                            new Coordinate(1, 0),
                            new Coordinate(1, 1),
//                            new Coordinate(1, 2),
                            new Coordinate(0, 2)
                    )
            ),

            // x3
            Arrays.asList(
                    Arrays.asList(
                            new Coordinate(0, 1),
                            new Coordinate(1, 1),
                            new Coordinate(2, 1)
                    ),
                    Arrays.asList(
                            new Coordinate(1, 0),
                            new Coordinate(1, 1),
                            new Coordinate(1, 2)
                    )
            ),
            // x4
            Arrays.asList(
                    Arrays.asList(
                            new Coordinate(1, 0),
                            new Coordinate(0, 1)
                    ),
                    Arrays.asList(
                            new Coordinate(0, 0),
                            new Coordinate(1, 1)
                    )
            ),
            // X5
            Arrays.asList(
                    Arrays.asList(
                            new Coordinate(1, 0),
                            new Coordinate(1, 1)
                    ),
                    Arrays.asList(
                            new Coordinate(2, 1),
                            new Coordinate(1, 1)
                    ),
                    Arrays.asList(
                            new Coordinate(1, 2),
                            new Coordinate(1, 1)
                    ),
                    Arrays.asList(
                            new Coordinate(0, 1),
                            new Coordinate(1, 1)
                    )
            ),

            // X6
            Arrays.asList(
                    Arrays.asList(
                            new Coordinate(0, 1),
                            new Coordinate(2, 1),
                            new Coordinate(1, 0)
                    ),
                    Arrays.asList(
                            new Coordinate(1, 0),
                            new Coordinate(1, 2),
                            new Coordinate(2, 1)
                    ),
                    Arrays.asList(
                            new Coordinate(0, 1),
                            new Coordinate(2, 1),
                            new Coordinate(1, 2)
                    ),
                    Arrays.asList(
                            new Coordinate(1, 0),
                            new Coordinate(1, 2),
                            new Coordinate(0, 1)
                    )
            ),

            // X7
            Arrays.asList(
                    Arrays.asList(
                            new Coordinate(1, 1)
                    )
            ),

            // X8
            Arrays.asList(
                    Arrays.asList(
                            new Coordinate(1, 1),
                            new Coordinate(2, 0),
                            new Coordinate(0, 2)
                    ),
                    Arrays.asList(
                            new Coordinate(1, 1),
                            new Coordinate(0, 0),
                            new Coordinate(2, 2)
                    )
            )
    ));

    final static class Coordinate {
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
    List<List<Coordinate>> coordinates;

    public Shape(ShapeType type) {
        this.type = type;
        this.base = bases[type.ordinal()];
        this.coordinates = Shape.shapes.get(type.ordinal());
    }

    private Shape(Shape shape, Coordinate coordinate) {
        this.type = shape.type;
        this.status = shape.status;
        this.base = coordinate;
        this.coordinates = Shape.shapes.get(type.ordinal());
    }

    private Shape(Shape shape, int status) {
        this.type = shape.type;
        this.status = status;
        this.base = shape.base;
        this.coordinates = Shape.shapes.get(type.ordinal());
    }

    static Shape shapeOf(Shape shape, Coordinate base) {
        return new Shape(shape, base);
    }

    static Shape shapeOf(Shape shape, int status) {
        return new Shape(shape, status);
    }

    public static Shape getRandomShape() {
        Random random = new Random();
        int x = random.nextInt(15) + 1;
        while (Board.set.contains(x))
            x = random.nextInt(15) + 1;
        return new Shape(ShapeType.values()[x]);
    }

    public ShapeType getType() {
        return type;
    }

    public List<Coordinate> getShape() {
//        return shape;
//        return Arrays.asList(
//                new Coordinate(this.base.x + this.coordinates.get(status).get(0).x, this.base.y + this.coordinates.get(status).get(0).y),
//                new Coordinate(this.base.x + this.coordinates.get(status).get(1).x, this.base.y + this.coordinates.get(status).get(1).y),
//                new Coordinate(this.base.x + this.coordinates.get(status).get(2).x, this.base.y + this.coordinates.get(status).get(2).y),
//                new Coordinate(this.base.x + this.coordinates.get(status).get(3).x, this.base.y + this.coordinates.get(status).get(3).y)
//        );
        List<Coordinate> ret = new ArrayList<>();
        this.coordinates.get(status).forEach(c -> ret.add(new Coordinate(this.base.x + c.x, this.base.y + c.y)));
        return ret;
    }


    public Shape moveLeft() {
        return Shape.shapeOf(this, new Coordinate(this.base.x - 1, this.base.y));
    }

    public Shape moveRight() {
        return Shape.shapeOf(this, new Coordinate(this.base.x + 1, this.base.y));
    }

    public Shape rotateClockwise() {
        int status = (this.status + 1) % this.coordinates.size();
        return Shape.shapeOf(this, status);
    }

    public Shape rotateCounterClockwise() {

        int status = (this.status - 1);
        if (status < 0)
            status = this.coordinates.size() - 1;
        return Shape.shapeOf(this, status);
    }

    public Shape moveDown() {
        return Shape.shapeOf(this, new Coordinate(this.base.x, this.base.y + 1));
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



