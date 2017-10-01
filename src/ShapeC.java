
public class ShapeC extends Shape {

    static final Coordinate[][] ShapeC = new Coordinate[][]{
            {
                    new Coordinate(0, 0),
                    new Coordinate(0, 1),
                    new Coordinate(1, 0),
                    new Coordinate(1, 1)
            },
    };

    public ShapeC(Coordinate base, int status) {
        super(base);

        this.type = ShapeType.C;
        this.status = status;
        this.coordinates = ShapeC;
    }

    public ShapeC() {
        super(new Coordinate(4, 0));
        this.type = ShapeType.C;
        this.coordinates = ShapeC;
    }
}
