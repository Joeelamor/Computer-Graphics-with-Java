
public class ShapeI extends Shape {

    static final Coordinate[][] ShapeI = new Coordinate[][]{
            {
                    new Coordinate(0, 1),
                    new Coordinate(1, 1),
                    new Coordinate(2, 1),
                    new Coordinate(3, 1)
            },
            {
                    new Coordinate(1, 0),
                    new Coordinate(1, 1),
                    new Coordinate(1, 2),
                    new Coordinate(1, 3)
            },
    };

    public ShapeI(Coordinate base, int status) {
        super(base);

        this.type = ShapeType.I;
        this.status = status;
        this.coordinates = ShapeI;
    }

    public ShapeI() {
        super(new Coordinate(4, 0));
        this.type = ShapeType.I;
        this.coordinates = ShapeI;
    }
}
