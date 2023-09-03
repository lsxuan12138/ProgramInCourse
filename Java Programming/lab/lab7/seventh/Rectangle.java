package seventh;

import java.awt.Graphics;

/**
 * 矩形
 */
public class Rectangle extends Square {
	private int height;
	
	public Rectangle(int x, int y, int width, int height) {
		super(x,y,width);
		this.height = height;
	}

	@Override
	public void draw(Graphics g) {
		g.drawRect(super.getX(), super.getY(),super.getWidth(), height);
	}
	@Override
	public double getArea() {
		return super.getWidth()*height;
	}

	@Override
	public double getPerimeter() {
		return 2*(super.getWidth()+height);
	}
}
