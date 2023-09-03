package P2.turtle;

import junit.framework.TestCase;

public class TurtleSoupTest extends TestCase {

    public void testPointIsInLeft() {
        Point src1 = new Point(0,0);
        Point dest1 = new Point(1,1);
        Point point1 = new Point(2,2);
        assertTrue(TurtleSoup.pointIsInLeft(src1,dest1,point1));

        Point src2 = new Point(0,0);
        Point dest2 = new Point(0,1);
        Point point2 = new Point(0,0.5);
        assertTrue(TurtleSoup.pointIsInLeft(src2,dest2,point2));

        Point src3 = new Point(1,1);
        Point dest3 = new Point(-1,-1.5);
        Point point3 = new Point(0,-3.5);
        assertTrue(TurtleSoup.pointIsInLeft(src3,dest3,point3));

        Point src4 = new Point(0,0);
        Point dest4 = new Point(0,1);
        Point point4 = new Point(-3,0.5);
        assertTrue(TurtleSoup.pointIsInLeft(src4,dest4,point4));


        Point src5 = new Point(1,1);
        Point dest5 = new Point(-7,-8.6);
        Point point5 = new Point(0,6.5);
        assertFalse(TurtleSoup.pointIsInLeft(src5,dest5,point5));

        Point src6 = new Point(1,1);
        Point dest6 = new Point(8,1);
        Point point6 = new Point(-3,0.5);
        assertFalse(TurtleSoup.pointIsInLeft(src6,dest6,point6));
    }
}