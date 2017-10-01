
public class ShapeL extends Shape {

    static final Coordinate[][] ShapeL = new Coordinate[][]{
            {
                    new Coordinate(0, 1),
                    new Coordinate(1, 1),
                    new Coordinate(2, 1),
                    new Coordinate(2, 0)
            },
            {
                    new Coordinate(1, 0),
                    new Coordinate(1, 1),
                    new Coordinate(1, 2),
                    new Coordinate(2, 2)
            },
            {
                    new Coordinate(0, 1),
                    new Coordinate(0, 2),
                    new Coordinate(1, 1),
                    new Coordinate(2, 1)
            },
            {
                    new Coordinate(0, 0),
                    new Coordinate(1, 0),
                    new Coordinate(1, 1),
                    new Coordinate(1, 2)
            },
    };


    public ShapeL(Coordinate base, int status) {
        super(base);

        this.type = ShapeType.L;
        this.status = status;
        this.coordinates = ShapeL;
    }

    public ShapeL() {
        super(new Coordinate(4, 0));
        this.type = ShapeType.L;
        this.coordinates = ShapeL;
    }
}
