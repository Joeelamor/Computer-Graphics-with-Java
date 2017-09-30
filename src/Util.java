import sun.security.provider.SHA;

import java.awt.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class Util {
    static Shape currentShape, nextShape;
    static Queue<Shape> q = new LinkedList<>();

    public static void getRandomShape(Graphics g, int[][] area, Point origin, int length) {
        while (q.size() < 100) {
            Random random = new Random();
            switch (random.nextInt(7)) {
                case 0:
                    q.offer(new ShapeCube(g, area, origin, length)) ;
                case 1:
                    q.offer(new ShapeI(g, area, origin, length));
                case 2:
                    q.offer(new ShapeJ(g, area, origin, length));
                case 3:
                    q.offer(new ShapeL(g, area, origin, length));
                case 4:
                    q.offer(new ShapeZ(g, area, origin, length));
                case 5:
                    q.offer(new ShapeS(g, area, origin, length));
                case 6:
                    q.offer(new ShapeT(g, area, origin, length));
            }
        }
//        if (q.size() > 2) {
//            currentShape = q.poll();
//            nextShape = q.peek();
//        }
    }

    public static Shape getCurrentShape() {
        return q.poll();
    }

    public static Shape getNextShape() {
        return q.peek();
    }
}
