import java.util.LinkedList;
import java.util.Queue;

public class Board implements Runnable{

    Queue<Shape> queue;
    Shape currentShape;
    boolean over = false;

    private volatile boolean running = true;
    private volatile boolean paused = false;
    private final Object pauseLock = new Object();

    int[][] area = new int[][] {
            {Integer.MAX_VALUE, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, Integer.MAX_VALUE},
            {Integer.MAX_VALUE, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, Integer.MAX_VALUE},
            {Integer.MAX_VALUE, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, Integer.MAX_VALUE},
            {Integer.MAX_VALUE, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, Integer.MAX_VALUE},
            {Integer.MAX_VALUE, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, Integer.MAX_VALUE},
            {Integer.MAX_VALUE, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, Integer.MAX_VALUE},
            {Integer.MAX_VALUE, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, Integer.MAX_VALUE},
            {Integer.MAX_VALUE, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, Integer.MAX_VALUE},
            {Integer.MAX_VALUE, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, Integer.MAX_VALUE},
            {Integer.MAX_VALUE, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, Integer.MAX_VALUE},
            {Integer.MAX_VALUE, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, Integer.MAX_VALUE},
            {Integer.MAX_VALUE, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, Integer.MAX_VALUE},
            {Integer.MAX_VALUE, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, Integer.MAX_VALUE},
            {Integer.MAX_VALUE, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, Integer.MAX_VALUE},
            {Integer.MAX_VALUE, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, Integer.MAX_VALUE},
            {Integer.MAX_VALUE, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, Integer.MAX_VALUE},
            {Integer.MAX_VALUE, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, Integer.MAX_VALUE},
            {Integer.MAX_VALUE, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, Integer.MAX_VALUE},
            {Integer.MAX_VALUE, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, Integer.MAX_VALUE},
            {Integer.MAX_VALUE, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, Integer.MAX_VALUE},
            {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE},
    };

    public Board() {
//        this.area = new int[21][12];
        this.queue = new LinkedList<>();
        this.getRandomShape();
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

            if (this.over)
                return;

            Shape next = this.getCurrentShape().moveDown();

            if (validate(next))
                currentShape = next;
            else {
                meltin(currentShape);
                currentShape = poll();
                if (!validate(currentShape))
                    this.over = true;
            }
            try {
                Thread.sleep(500);
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

    private boolean validate(Shape shape) {
        for (Shape.Coordinate coordinate: shape.getShape()) {
            if (this.area[coordinate.y][coordinate.x] > 0) {
                return false;
            }
        }
        return true;
    }

    private void meltin(Shape shape) {
        for (Shape.Coordinate coordinate: shape.getShape()) {
            this.area[coordinate.y][coordinate.x] = shape.type.ordinal();
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
}
