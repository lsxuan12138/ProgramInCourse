package seventh;

import java.awt.Graphics;

/**
 * 圆形
 */
public class Circle extends Shape {
	public static final double PAI = 3.141592657;
	private int x;
	private int y;
	private int radius;
	
	public Circle(int x, int y, int radius)
	{
		this.x = x;
		this.y = y;
		this.radius = radius;
	}
	@Override
	public void draw(Graphics g) {
		g.drawOval(x-radius, y-radius, radius*2, radius*2);
	}

	@Override
	public double getArea() {
		return PAI * radius * radius;
	}

	@Override
	public double getPerimeter() {
		return 2* PAI * radius;
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

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}
}
