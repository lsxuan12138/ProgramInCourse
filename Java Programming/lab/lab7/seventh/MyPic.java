package seventh;

/**
 * 测试程序
 */
public class MyPic {
	public static void main(String[] args) 
	{
		Picture pic = new Picture(420,300);
		Circle c1 = new Circle(320,40,80);
		Square s1 = new Square(100, 100, 100);
		Rectangle r1 = new Rectangle(100, 50, 100, 100);
		Triangle t1 = new Triangle(100, 100, 200, 100, 150, 50);
		Line l1 = new Line(0,205,400,205);
		Circle c2 = new Circle(200,200,50);
		Ellipse c3 = new Ellipse(200,200,50,30);
		pic.add(c1);
		System.out.println("c1的周长："+c1.getPerimeter()+"  面积："+c1.getArea());
		pic.add(s1);
		System.out.println("s1的周长："+s1.getPerimeter()+"  面积："+s1.getArea());
		pic.add(r1);
		System.out.println("r1的周长："+r1.getPerimeter()+"  面积："+r1.getArea());
		pic.add(t1);
		System.out.println("t1的周长："+t1.getPerimeter()+"  面积："+t1.getArea());
		pic.add(l1);
		System.out.println("l1的周长："+l1.getPerimeter()+"  面积："+l1.getArea());
		pic.add(c2);
		System.out.println("c2的周长："+c2.getPerimeter()+"  面积："+c2.getArea());
		pic.add(c3);
		System.out.println("c3的周长："+c3.getPerimeter()+"  面积："+c3.getArea());
		pic.draw();	
	}
}
