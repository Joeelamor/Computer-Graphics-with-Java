import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ShapeI extends Shape {

    static final List<List<Coordinate>> ShapeI = Collections.unmodifiableList(Arrays.asList(
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
    ));

    public ShapeI(Coordinate base, int status) {
        super(base);

        this.type = ShapeType.I;
        this.status = status;
        this.coordinates = ShapeI;
    }

    public ShapeI() {
        super(new Coordinate(4, -1));
        this.type = ShapeType.I;
        this.coordinates = ShapeI;
    }
}
