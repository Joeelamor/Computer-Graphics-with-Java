import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ShapeS extends Shape {

    static final List<List<Coordinate>> ShapeS = Collections.unmodifiableList(Arrays.asList(
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
    ));

    public ShapeS(Coordinate base, int status) {
        super(base);

        this.type = ShapeType.S;
        this.status = status;
        this.coordinates = ShapeS;
    }

    public ShapeS() {
        super(new Coordinate(4, -1));
        this.type = ShapeType.S;
        this.coordinates = ShapeS;
    }
}
