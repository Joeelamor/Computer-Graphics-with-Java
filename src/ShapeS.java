
public class ShapeS extends Shape {

    static final Coordinate[][] ShapeS = new Coordinate[][]{
            {
                    new Coordinate(1, 1),
                    new Coordinate(2, 1),
                    new Coordinate(0, 2),
                    new Coordinate(1, 2)
            },
            {
                    new Coordinate(0, 0),
                    new Coordinate(0, 1),
                    new Coordinate(1, 1),
                    new Coordinate(1, 2)
            },
    };

    public ShapeS(Coordinate base, int status) {
        super(base);

        this.type = ShapeType.S;
        this.status = status;
        this.coordinates = ShapeS;
    }

    public ShapeS() {
        super(new Coordinate(4, 0));
        this.type = ShapeType.S;
        this.coordinates = ShapeS;
    }
}
