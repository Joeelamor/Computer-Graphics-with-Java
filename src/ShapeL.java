import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ShapeL extends Shape {

    static final List<List<Coordinate>> ShapeL = Collections.unmodifiableList(Arrays.asList(
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
    ));


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
