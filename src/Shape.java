import java.awt.*;

public class Shape {
    Color color;
    int [][]shape = new int[4][2];

    public Shape() {
    }

//    public Shape(Graphics g, int[][] area, Point origin, int length) {
//        this.g = g;
//        this.area = area;
//        this.origin = origin;
//        this.length = length;
//    }

//    public void draw(){
//        for (int []arr : shape) {
//            g.setColor(this.color);
//            g.fillRect(origin.x + arr[0] * length, origin.y + arr[1] * length, length, length);
//            g.setColor(Color.black);
//            g.drawRect(origin.x + arr[0] * length, origin.y + arr[1] * length, length, length);
//        }
//    }
//
//
//    public void reset(Graphics g, Point origin, int length) {
//        this.g = g;
//        this.origin = origin;
//        this.length = length;
//    }

    public Color getColor() {
        return color;
    }

    public int[][] getShape() {
        return shape;
    }

    public void moveLeft() {
    }

    public void moveright() {
    }

    public void rotate() {
    }
}



