import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ShapeC extends Shape {

    static final List<List<Coordinate>> ShapeC = Collections.unmodifiableList(Arrays.asList(
            Arrays.asList(
                    new Coordinate(1, 1),
                    new Coordinate(1, 2),
                    new Coordinate(2, 1),
                    new Coordinate(2, 2)
            )
    ));

    public ShapeC(Coordinate base, int status) {
        super(base);

        this.type = ShapeType.C;
        this.status = status;
        this.coordinates = ShapeC;
    }

    public ShapeC() {
        super(new Coordinate(4, -1));
        this.type = ShapeType.C;
        this.coordinates = ShapeC;
    }
}
