package yuriy.service;

import yuriy.model.Point;

import java.util.Iterator;
import java.util.LinkedHashSet;

public class CheckRightDirect {
    public static boolean checkRightDirect(LinkedHashSet<Point> points){
        Iterator iterator = points.iterator();
        Point frontPoint;
        Point currentPoint = (Point) iterator.next();
        Point firstPoint = new Point(currentPoint.getxValue(), currentPoint.getyValue());
        Point mainPoint = insidePoint(points);
        double rotationAngle = 0;
        while (iterator.hasNext()){
            frontPoint = currentPoint;
            currentPoint = (Point) iterator.next();
            rotationAngle += findL(frontPoint, currentPoint, mainPoint);
        }

        rotationAngle += findL(currentPoint, firstPoint, mainPoint);
        if(rotationAngle < 0)
            return true;
        else return false;
    }
    //we calculate the side by the Pythagorean theorem
    public static double getSide(Point a, Point b){
        return Math.sqrt((a.getxValue() - b.getxValue())*(a.getxValue() - b.getxValue())
                + (a.getyValue() - b.getyValue())*(a.getyValue() - b.getyValue()));
    }
    //we calculate the angle by the cosine theorem
    public static double getL(double a, double b, double c){
        return  Math.acos((a*a - b*b - c*c) / (2*b*c));
    }
    //checking direct and return angle
    public static double findL(Point frontPoint, Point currentPoint, Point mainPoint){
        double a = getSide(frontPoint, currentPoint);
        double b = getSide(frontPoint, mainPoint);
        double c = getSide(currentPoint, mainPoint);
        if (currentPoint.getxValue() == frontPoint.getxValue()) {
            if (currentPoint.getyValue() > frontPoint.getyValue()) {
                if (currentPoint.getxValue() > mainPoint.getxValue()) {
                    return getL(a, b, c);
                } else return -getL(a, b, c);
            } else {
                if (currentPoint.getxValue() > mainPoint.getxValue()) {
                    return -getL(a, b, c);

                } else return getL(a, b, c);
            }
        } else {
            if (currentPoint.getxValue() > frontPoint.getxValue()) {
                if (currentPoint.getyValue() > mainPoint.getyValue()) {
                    return -getL(a, b, c);
                } else return getL(a, b, c);
            } else {
                if (currentPoint.getyValue() > mainPoint.getyValue()) {
                    return getL(a, b, c);
                } else return -getL(a, b, c);
            }
        }
    }
    //search a point that belongs to a shape
    public static Point insidePoint(LinkedHashSet<Point> points){
        Iterator iterator = points.iterator();
        Point frontPoint = (Point) iterator.next();
        Point firstPoint = new Point(frontPoint.getxValue(), frontPoint.getyValue());
        Point currentPoint = (Point) iterator.next();

        //supposed points (one of them must contains in room)


        Point supposedPoint1 = new Point();
        Point supposedPoint2 = new Point();

        //sides[0] = number of sides on the left
        //sides[1] = number of sides from above
        //sides[2] = number of sides on the right
        //sides[3] = number of sides from below
        int sides[] = {0, 0, 0, 0};

        if(frontPoint.getxValue() == currentPoint.getxValue()){
            supposedPoint1.setxValue(frontPoint.getxValue() + 0.5);
            supposedPoint2.setxValue(frontPoint.getxValue() - 0.5);
            sides[0] +=1;
            if(frontPoint.getyValue() < currentPoint.getyValue()){
                supposedPoint1.setyValue(frontPoint.getxValue() + 0.5);
                supposedPoint2.setyValue(frontPoint.getxValue() + 0.5);
            }
            else{
                supposedPoint1.setyValue(frontPoint.getxValue() - 0.5);
                supposedPoint2.setyValue(frontPoint.getxValue() - 0.5);
            }
        }
        else
        {
            supposedPoint1.setyValue(frontPoint.getyValue() + 0.5);
            supposedPoint2.setyValue(frontPoint.getyValue() - 0.5);
            sides[1] +=1;
            if(frontPoint.getxValue() < currentPoint.getxValue()){
                supposedPoint1.setxValue(frontPoint.getxValue() + 0.5);
                supposedPoint2.setxValue(frontPoint.getxValue() + 0.5);
            }
            else{
                supposedPoint1.setxValue(frontPoint.getxValue() - 0.5);
                supposedPoint2.setxValue(frontPoint.getxValue() - 0.5);
            }
        }
        while (iterator.hasNext()){
            frontPoint = currentPoint;
            currentPoint = (Point) iterator.next();
            sides = addSide(frontPoint, currentPoint, supposedPoint1, sides);
        }

        sides = addSide(currentPoint, firstPoint, supposedPoint1, sides);
        boolean isFirstPointInside = true;
        for (int i: sides
        ) {
            if(i%2 == 0){
                isFirstPointInside = false;
                break;
            }
        }

        if(isFirstPointInside == true)
            return supposedPoint1;
        else return supposedPoint2;

    }

    public static int[] addSide(Point frontPoint, Point currentPoint, Point supposedPoint1, int[] sides){

        if(frontPoint.getxValue() == currentPoint.getxValue()){
            if((supposedPoint1.getyValue()< currentPoint.getyValue() && supposedPoint1.getyValue() > frontPoint.getyValue())
                    || (supposedPoint1.getyValue()> currentPoint.getyValue() && supposedPoint1.getyValue() < frontPoint.getyValue())){
                if(currentPoint.getxValue() > supposedPoint1.getyValue())
                    sides[2] += 1;
                else sides[0] +=1;
            }
        }
        else {
            if((supposedPoint1.getxValue()< currentPoint.getxValue() && supposedPoint1.getxValue() > frontPoint.getxValue())
                    || (supposedPoint1.getxValue()> currentPoint.getxValue() && supposedPoint1.getxValue() < frontPoint.getxValue())){
                if(currentPoint.getyValue() > supposedPoint1.getyValue())
                    sides[1] += 1;
                else sides[3] +=1;
            }
        }
        return sides;
    }
}
