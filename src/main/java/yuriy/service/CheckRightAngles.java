package yuriy.service;

import yuriy.model.Point;

import java.util.Iterator;
import java.util.LinkedHashSet;

public class CheckRightAngles {
    public static boolean DoPointsContainNotRightAngles(LinkedHashSet<Point> points){
        Iterator iterator = points.iterator();
        Point currentPoint = (Point) iterator.next();
        Point firstPoint = currentPoint;
        Point backPoint = (Point) iterator.next();
        Point frontPoint;

        while (iterator.hasNext()){
            frontPoint = currentPoint;
            currentPoint = backPoint;
            backPoint = (Point) iterator.next();
            if(((frontPoint.getxValue() == currentPoint.getxValue()) &&(currentPoint.getxValue() == backPoint.getxValue()) ||
                    ((frontPoint.getyValue() == currentPoint.getyValue()) &&(currentPoint.getyValue() == backPoint.getyValue()))))
                return true;
            if(frontPoint.getxValue() == currentPoint.getxValue() || frontPoint.getyValue() == currentPoint.getyValue())
                continue;
            else return true;
        }
        if(((currentPoint.getxValue() == backPoint.getxValue()) && (backPoint.getxValue() == firstPoint.getxValue())) ||
                ((currentPoint.getyValue() == backPoint.getyValue()) && backPoint.getyValue() == firstPoint.getyValue()))
            return true;

        if((currentPoint.getxValue() != backPoint.getxValue()) && (currentPoint.getyValue() != backPoint.getyValue()))
            return true;
        if((backPoint.getxValue() != firstPoint.getxValue()) && (backPoint.getyValue() != firstPoint.getyValue()))
            return true;
        return false;
    }
}
