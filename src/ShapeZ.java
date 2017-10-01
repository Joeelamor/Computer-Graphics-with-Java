import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ShapeZ extends Shape {

    static final List<List<Coordinate>> ShapeZ = Collections.unmodifiableList(Arrays.asList(
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
    ));

    public ShapeZ(Coordinate base, int status) {
        super(base);

        this.type = ShapeType.Z;
        this.status = status;
        this.coordinates = ShapeZ;
    }

    public ShapeZ() {
        super(new Coordinate(4, -1));
        this.type = ShapeType.Z;
        this.coordinates = ShapeZ;
    }
}