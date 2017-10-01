import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ShapeT extends Shape {

    static final List<List<Coordinate>> ShapeT = Collections.unmodifiableList(Arrays.asList(
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
    ));

    public ShapeT(Coordinate base, int status) {
        super(base);

        this.type = ShapeType.T;
        this.status = status;
        this.coordinates = ShapeT;
    }

    public ShapeT() {
        super(new Coordinate(4, 0));
        this.type = ShapeType.T;
        this.coordinates = ShapeT;
    }
}
