package seventh;

import java.awt.*;

/**
 * 正方形
 */
public class Square extends Shape{
    private int x;
    private int y;
    private int width;

    public Square(int x, int y, int width) {
        this.x = x;
        this.y = y;
        this.width = width;
    }

    @Override
    public void draw(Graphics g) {
        g.drawRect(x,y,width,width);
    }

    @Override
    public double getArea() {
        return width*width;
    }

    @Override
    public double getPerimeter() {
        return 4*width;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
