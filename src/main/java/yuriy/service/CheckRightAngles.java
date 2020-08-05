package yuriy.service;

import yuriy.model.Point;

import java.util.Iterator;
import java.util.LinkedHashSet;

public class CheckRightAngles {
    public static boolean s(LinkedHashSet<Point> points){
        Iterator iterator = points.iterator();
        Point currentPoint = (Point) iterator.next();
        Point firstPoint = currentPoint;
        Point frontPoint;

        while (iterator.hasNext()){
            frontPoint = currentPoint;
            currentPoint = (Point) iterator.next();
            if(frontPoint.getxValue() == currentPoint.getxValue() || frontPoint.getyValue() == currentPoint.getyValue())
                continue;
            else return false;
        }
        if(firstPoint.getxValue() == currentPoint.getxValue() || firstPoint.getyValue() == currentPoint.getyValue())
            return false;
        return true;
    }
}
