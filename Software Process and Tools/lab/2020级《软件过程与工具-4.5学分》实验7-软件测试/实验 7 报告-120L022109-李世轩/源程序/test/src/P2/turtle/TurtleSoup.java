/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P2.turtle;

import java.util.*;

import static org.junit.Assert.assertEquals;

public class TurtleSoup {

    /**
     * Draw a square.
     * 
     * @param turtle the turtle context
     * @param sideLength length of each side
     */
    public static void drawSquare(Turtle turtle, int sideLength) {
        turtle.forward(sideLength);
        turtle.turn(90);
        turtle.forward(sideLength);
        turtle.turn(90);
        turtle.forward(sideLength);
        turtle.turn(90);
        turtle.forward(sideLength);
    }

    /**
     * Determine inside angles of a regular polygon.
     * 
     * There is a simple formula for calculating the inside angles of a polygon;
     * you should derive it and use it here.
     * 
     * @param sides number of sides, where sides must be > 2
     * @return angle in degrees, where 0 <= angle < 360
     */
    public static double calculateRegularPolygonAngle(int sides) {
        return  180-360/(double)sides;
    }

    /**
     * Determine number of sides given the size of interior angles of a regular polygon.
     * 
     * There is a simple formula for this; you should derive it and use it here.
     * Make sure you *properly round* the answer before you return it (see java.lang.Math).
     * HINT: it is easier if you think about the exterior angles.
     * 
     * @param angle size of interior angles in degrees, where 0 < angle < 180
     * @return the integer number of sides
     */
    public static int calculatePolygonSidesFromAngle(double angle) {
        double side = 360/(180-angle);
        int round = (int)Math.round(side);
        if(Math.abs(side-round)<=0.001){
            return round;
        }
        return  -1;

    }

    /**
     * Given the number of sides, draw a regular polygon.
     * 
     * (0,0) is the lower-left corner of the polygon; use only right-hand turns to draw.
     * 
     * @param turtle the turtle context
     * @param sides number of sides of the polygon to draw
     * @param sideLength length of each side
     */
    public static void drawRegularPolygon(Turtle turtle, int sides, int sideLength) {
        double angle = calculateRegularPolygonAngle(sides);
        turtle.forward(sideLength);
        for(int i = 2;i<=sides;i++){
            turtle.turn(180-angle);
            turtle.forward(sideLength);
        }
    }

    /**
     * Given the current direction, current location, and a target location, calculate the Bearing
     * towards the target point.
     * 
     * The return value is the angle input to turn() that would point the turtle in the direction of
     * the target point (targetX,targetY), given that the turtle is already at the point
     * (currentX,currentY) and is facing at angle currentBearing. The angle must be expressed in
     * degrees, where 0 <= angle < 360. 
     *
     * HINT: look at http://en.wikipedia.org/wiki/Atan2 and Java's math libraries
     * 
     * @param currentBearing current direction as clockwise from north
     * @param currentX current location x-coordinate
     * @param currentY current location y-coordinate
     * @param targetX target point x-coordinate
     * @param targetY target point y-coordinate
     * @return adjustment to Bearing (right turn amount) to get to target point,
     *         must be 0 <= angle < 360
     */
    public static double calculateBearingToPoint(double currentBearing, int currentX, int currentY,
                                                 int targetX, int targetY) {

        double angle = Math.toDegrees(Math.atan2(targetX-currentX,targetY-currentY));
        double turn = angle-currentBearing;
        if(turn<0){
            return turn+360;
        }else {
            return turn;
        }

    }

    /**
     * Given a sequence of points, calculate the Bearing adjustments needed to get from each point
     * to the next.
     * 
     * Assumes that the turtle starts at the first point given, facing up (i.e. 0 degrees).
     * For each subsequent point, assumes that the turtle is still facing in the direction it was
     * facing when it moved to the previous point.
     * You should use calculateBearingToPoint() to implement this function.
     * 
     * @param xCoords list of x-coordinates (must be same length as yCoords)
     * @param yCoords list of y-coordinates (must be same length as xCoords)
     * @return list of Bearing adjustments between points, of size 0 if (# of points) == 0,
     *         otherwise of size (# of points) - 1
     */
    public static List<Double> calculateBearings(List<Integer> xCoords, List<Integer> yCoords) {
        List<Double> result = new ArrayList<Double>();
        int cnt = xCoords.size();
        double angle = 0;
        double deltaDegree;
        for (int i = 0; i < cnt-1; i++) {
            deltaDegree=calculateBearingToPoint(angle,xCoords.get(i),yCoords.get(i),xCoords.get(i+1),yCoords.get(i+1));
            result.add(deltaDegree);
            angle+=deltaDegree;
            angle%=360;
        }
        return result;
    }
    
    /**
     * Given a set of points, compute the convex hull, the smallest convex set that contains all the points 
     * in a set of input points. The gift-wrapping algorithm is one simple approach to this problem, and 
     * there are other algorithms too.
     * 
     * @param points a set of points with xCoords and yCoords. It might be empty, contain only 1 point, two points or more.
     * @return minimal subset of the input points that form the vertices of the perimeter of the convex hull
     */
    public static Set<Point> convexHull(Set<Point> points) {
        /**Graham(格拉翰)扫描法
         *1把所有点放在二维坐标系中，则纵坐标最小的点一定是凸包上的点，记为P0。
         * 2把所有点的坐标平移一下，使P0作为原点。
         * 3计算各个点相对于P0的幅角 α ，按从小到大的顺序对各个点排序。当 α 相同时，距离P0比较近的排在前面。假设得到的结果为P1，P2，P3，P4，P5，
         * P6，P7，P8。我们由几何知识可以知道，结果中第一个点P1和最后一个点P8一定是凸包上的点。
         * 4以上，我们已经知道了凸包上的第一个点P0和第二个点P1，我们把它们放在栈里面。现在从步骤3求得的那个结果里，把P1后面的那个点拿出来做当前点，
         * 即P2。接下来开始找第三个点。
         * 5连接栈最上面的两个元素，得到直线L。看当前点是在直线L的右边还是左边。如果在直线的右边就执行步骤6；如果在直线上，或者在直线的左边就执行步骤7。
         * 6如果在右边，则栈顶的那个元素不是凸包上的点，把栈顶元素出栈。执行步骤5。
         * 7当前点是凸包上的点，把它压入栈，执行步骤8。
         * 8检查当前的点是不是步骤3那个结果的最后一个元素。是最后一个元素的话就结束。如果不是的话就把当前点后面那个点做当前点，返回步骤5。
         */
        //当点集内数量少于4个，直接返回本身
        int size = points.size();
        if(size>=0&&size<=3){
            return points;
        }
        Point p0=new Point(Double.MAX_VALUE,Double.MAX_VALUE);

        //取到v1，y最小的点一定在凸包上
        for (Point point:
             points) {
            if(point.y()< p0.y()) {
                p0=point;
            }else if(point.y()== p0.y()) {
                if(point.x()<= p0.x()) {
                    p0 = point;
                }
            }
        }
        //排序
        List<Point> sortPoint =sortPoint(points,p0);
        Point end = sortPoint.get(sortPoint.size()-1);
        Stack<Point> stack = new Stack<>();
        stack.push(p0);
        stack.push(sortPoint.get(0));
        int i = 1;
        Point point = sortPoint.get(i);
        Point p1=p0,p2=sortPoint.get(0);
        while (true) {

            if (pointIsInLeft(p1, p2, point)) {
                p1=p2;
                p2=point;
                stack.push(point);
                if (end == point) {
                    break;
                }else {
                    i++;
                    point=sortPoint.get(i);
                }
            } else {
                stack.pop();
                p2=stack.get(stack.size()-1);
                p1=stack.get(stack.size()-2);
            }
        }
        Set<Point> result = new HashSet<Point>();
        while (!stack.isEmpty()){
            result.add(stack.pop());
        }
        return result;
    }

    /**
     * 判断点point是否在向量直线的左边
     * @param src 向量起点
     * @param dest 向量终点
     * @param point
     * @return true for 如果在直线上，或者在直线的左边 ,otherwise false
     */

    public static boolean pointIsInLeft(Point src,Point dest,Point point){
        double angle = 0,deltaDegree;
        deltaDegree=calculateBearingToPoint(angle,(int) src.x(),(int) src.y(),(int) dest.x(),(int) dest.y());
        angle+=deltaDegree;
        angle%=360;
        deltaDegree=calculateBearingToPoint(angle,(int) dest.x(),(int) dest.y(),(int) point.x(),(int) point.y());
        if(deltaDegree>0&&deltaDegree<180){
            return false;
        } else if (deltaDegree>180&&deltaDegree<360) {
            return true;
        }else {
            return true;
        }
    }
    /**
     * 以pole为极点，按照极角升序排序Point中点
     * @param points
     * @param pole 必须为Point中y最小的点
     * @return 排序后的点
     */
    public static List<Point> sortPoint(Set<Point> points,Point pole){
        Map<Point,Double> map = new HashMap<>(points.size()-1);
        List<Point> result = new ArrayList<>();
        //算出point对应极角
        for (Point point:
             points) {
            if(point==pole){continue;}
            if(point.x()==pole.x()){
                map.put(point,90.0);
                continue;
            }
            double degree;
            if((degree = Math.toDegrees(Math.atan((point.y()-pole.y())/(point.x()-pole.x()))))<0){
                degree+=180;
            }
            map.put(point,degree);
        }
        //按顺序放到result中
        while (!map.isEmpty()) {
            Set<Point> keySet = map.keySet();
            Point min = null;
            for (Point point : keySet) {
                if (min == null) {
                    min = point;
                } else if (map.get(point) < map.get(min)) {
                    min = point;
                } else if (map.get(point).equals(map.get(min))) {
                    if (point.y() - pole.y() <= min.y() - pole.y()) {
                        min = point;
                    }
                }
            }
            result.add(min);
            map.remove(min);
        }
        return result;
    }
    
    /**
     * Draw your personal, custom art.
     * 
     * Many interesting images can be drawn using the simple implementation of a turtle.  For this
     * function, draw something interesting; the complexity can be as little or as much as you want.
     * 
     * @param turtle the turtle context
     */
    public static void drawPersonalArt(Turtle turtle) {
        String[] colors={"RED","ORANGE","YELLOW","GREEN","BLUE","CYAN"};
        int sides = 6;
        for(int i=0;i<100;i++){
            turtle.color(PenColor.valueOf(colors[i%sides]));
            turtle.forward(i*3/sides+i);
            turtle.turn(360/sides+1);
        }

    }

    /**
     * Main method.
     * 
     * This is the method that runs when you run "java TurtleSoup".
     * 
     * @param args unused
     */
    public static void main(String args[]) {
        DrawableTurtle turtle = new DrawableTurtle();
//        drawSquare(turtle, -50);
        drawRegularPolygon(turtle,6,0);
//        drawPersonalArt(turtle);
        // draw the window
        turtle.draw();
    }

}
