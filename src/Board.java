import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class Board implements Runnable {

    Queue<Shape> queue;
    Shape currentShape;
    boolean over = false;

    private volatile boolean running = true;
    private volatile boolean paused = false;
    private final Object pauseLock = new Object();

    int w;
    int h;
    LinkedList<int[]> area;

    int level = 1;
    int line = 0;
    int lineOfCurrentLevel = 0;
    int scoreFactor = 0;
    final int rol;
    int score = 0;
    int speed;
    static Set set;

    public Board(int speed, int rol, int scoreFactor, int w, int h, Set set) {
        this.w = w + 2;
        this.h = h + 1;
        this.rol = rol;
        this.speed = speed;
        this.scoreFactor = scoreFactor;
        this.area = new LinkedList<>();
        this.queue = new LinkedList<>();
        Board.set = set;
        this.getRandomShape();
        initialize();
    }

    private void initialize() {

        int[] line;

        for (int i = 0; i < this.h - 1; i++) {
            line = new int[this.w];

            line[0] = line[this.w - 1] = Integer.MAX_VALUE;
            for (int j = 1; j <= this.w - 2; j++)
                line[j] = 0;

            this.area.add(line);
        }

        line = new int[this.w];
        for (int i = 0; i < this.w; i++)
            line[i] = Integer.MAX_VALUE;
        this.area.add(line);
    }


    public void getRandomShape() {
        while (this.queue.size() < 100) {
            this.queue.offer(Shape.getRandomShape());
        }
    }

    private Shape poll() {
        Shape cur = this.queue.poll();
        if (this.queue.isEmpty())
            this.getRandomShape();

        int shift = (this.w - 4) / 2 - 1;
        for (int i = 1; i <= shift; i++)
            cur = cur.moveRight();
        return cur;
    }

    public Shape getCurrentShape() {
        if (currentShape == null)
            currentShape = this.poll();
        return currentShape;
    }

    public Shape getNextShape() {
        return this.queue.peek();
    }

    public void run() {
        int cleaned;

        while (running) {
            synchronized (pauseLock) {
                if (!running) {
                    break;
                }
                if (paused) {
                    try {
                        pauseLock.wait();
                    } catch (InterruptedException ex) {
                        break;
                    }
                    if (!running) {
                        break;
                    }
                }
            }

            if (this.over) {
                return;
            }

            Shape next = this.getCurrentShape().moveDown();

            if (validate(next))
                currentShape = next;
            else {
                meltIn(currentShape);
                cleaned = cleanUp();
                line += cleaned;
                lineOfCurrentLevel += cleaned;
                if (lineOfCurrentLevel > rol) {
                    level++;
                    lineOfCurrentLevel = 0;
                }
                score += level * scoreFactor * cleaned;
                currentShape = poll();

                if (!validate(currentShape))
                    this.over = true;
            }
            try {
                Thread.sleep((long) (500 / (1 + ((float) level * (float) speed / 10.0))));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void stop() {
        running = false;
    }

    public void pause() {
        paused = true;
    }

    public void resume() {
        synchronized (pauseLock) {
            paused = false;
            pauseLock.notifyAll();
        }
    }

    private int cleanUp() {
        int ret = 0;
        Iterator<int[]> iter = this.area.iterator();

        int[] cur;

        int l = 1;
        while (iter.hasNext() && l < h) {
            cur = iter.next();
            if (checkLine(cur)) {
                ret++;
                iter.remove();
            }
            l++;
        }

        for (int i = 1; i <= ret; i++) {
            cur = new int[this.w];

            cur[0] = cur[this.w - 1] = Integer.MAX_VALUE;
            for (int j = 1; j <= this.w - 2; j++)
                cur[j] = 0;

            this.area.addFirst(cur);
        }

        return ret;
    }

    private boolean checkLine(int[] line) {
        for (int i : line)
            if (i == 0)
                return false;
        return true;
    }

    private boolean validate(Shape shape) {
        for (Shape.Coordinate coordinate : shape.getShape()) {
            if (coordinate.y < 0)
                return false;
            if (this.area.get(coordinate.y)[coordinate.x] > 0) {
                return false;
            }
        }
        return true;
    }

    private void meltIn(Shape shape) {
        for (Shape.Coordinate coordinate : shape.getShape()) {
            this.area.get(coordinate.y)[coordinate.x] = shape.type.ordinal();
        }
    }

    public void moveRight() {

        Shape next = this.getCurrentShape().moveRight();

        if (validate(next))
            this.currentShape = next;

    }

    public void moveLeft() {

        Shape next = this.getCurrentShape().moveLeft();

        if (validate(next))
            this.currentShape = next;
    }

    public void rotateClockwise() {

        Shape next = this.currentShape.rotateClockwise();

        if (validate(next))
            this.currentShape = next;
    }

    public void rotateCounterClockwise() {

        Shape next = this.currentShape.rotateCounterClockwise();

        if (validate(next))
            this.currentShape = next;
    }

    void cheat() {
        Shape nextDiff = poll();

        while (nextDiff.type == currentShape.type)
            nextDiff = poll();

        currentShape = Shape.shapeOf(nextDiff, currentShape.base);
        this.score = this.score - this.level * this.scoreFactor;
        if (this.score <= 0)
            this.score = 0;
    }
}
