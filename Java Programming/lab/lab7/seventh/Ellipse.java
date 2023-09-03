package seventh;

import java.awt.*;

/**
 * 椭圆
 */
public class Ellipse extends Circle{
    private int minor;

    public Ellipse(int x, int y, int axis,int minorAxis) {
        super(x, y, axis);
        this.minor = minorAxis;
    }
    @Override
    public void draw(Graphics g) {
        int x = super.getX();
        int y = super.getY();
        int axis = super.getRadius();
        g.drawOval(x-axis, y-minor, axis*2, minor*2);
    }

    @Override
    public double getArea() {
        int axis = super.getRadius();
        return PAI * axis * minor;
    }

    @Override
    public double getPerimeter() {
        int axis = super.getRadius();
        int a=0,b=0;
        if(axis>minor){
            a=axis;
            b=minor;
        }else {
            a=minor;
            b=axis;
        }
        double Q = a+b;
        double h = ((a-b)/(a+b)*(a-b)/(a+b));
        double m = 22/(7*PAI)-1;
        double n =Math.pow((a-b)/a,33.679);
        return PAI*Q*(1+(3*h/(10+Math.sqrt(4-3*h))))*(1+m*n);
    }
}
