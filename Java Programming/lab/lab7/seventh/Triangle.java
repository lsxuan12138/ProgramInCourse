package seventh;

import java.awt.Graphics;

/**
 * 三角形
 */
public class Triangle extends Shape {
	private int[] x = new int[3];
	private int[] y = new int[3];
	
	public Triangle(int x1, int y1, int x2, int y2, int x3, int y3)
	{
		x[0] = x1; x[1] = x2; x[2] = x3;
		y[0] = y1; y[1] = y2; y[2] = y3;
	}
	
	@Override
	public void draw(Graphics g) {
		g.drawPolygon(x, y, x.length);
	}

	@Override
	public double getArea() {
		int[] ab = {x[1]-x[0],y[1]-y[0]};
		int[] ac = {x[2]-x[0],y[2]-y[0]};
//		double s = 0.5*(ac[0]*ab[1]-ac[1]*ab[0]);
//		if (s<0)return -s;
//		return s;
		return Math.abs(0.5*(ac[0]*ab[1]-ac[1]*ab[0]));
	}

	@Override
	public double getPerimeter() {
		int[] ab = {x[1]-x[0],y[1]-y[0]};
		int[] ac = {x[2]-x[0],y[2]-y[0]};
		int[] bc = {x[2]-x[1],y[2]-y[1]};
		double a =Math.sqrt(bc[0]*bc[0]+bc[1]*bc[1]);
		double b =Math.sqrt(ac[0]*ac[0]+ac[1]*ac[1]);
		double c =Math.sqrt(ab[0]*ab[0]+ab[1]*ab[1]);
		return a+b+c;
	}

}
