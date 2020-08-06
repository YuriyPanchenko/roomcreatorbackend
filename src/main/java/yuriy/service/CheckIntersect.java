package yuriy.service;

import yuriy.model.Point;

import java.util.Iterator;
import java.util.LinkedHashSet;

public class CheckIntersect {
    public static boolean checkPointsAtTheIntersectionWalls(LinkedHashSet<Point> points){
        Iterator iterator = points.iterator();
        Point secondPointFirstWall = (Point) iterator.next();
        Point firstPointFirstWall;
        while (iterator.hasNext()){
            firstPointFirstWall = secondPointFirstWall;
            secondPointFirstWall = (Point) iterator.next();
            Iterator otherWalls = iterator;
            Point secondPointSecondWall = (Point) iterator.next();
            Point firstPointSecondWall;
            while (otherWalls.hasNext()){
                firstPointSecondWall = secondPointSecondWall;
                secondPointSecondWall = (Point) otherWalls.next();
                if(isTwoWallsIntersect(firstPointFirstWall, secondPointFirstWall, firstPointSecondWall, secondPointSecondWall) == true)
                    return true;
            }
        }
        return false;
    }

    public static boolean isTwoWallsIntersect(Point firstPointFirstWall, Point secondPointFirstWall, Point firstPointSecondWall, Point secondPointSecondWall){
        double Ua, Ub, numerator_a, numerator_b, denominator;
        double x1 = firstPointFirstWall.getxValue();
        double y1 = firstPointFirstWall.getyValue();
        double x2 = secondPointFirstWall.getxValue();
        double y2 = secondPointFirstWall.getyValue();
        double x3 = firstPointSecondWall.getxValue();
        double y3 = firstPointSecondWall.getyValue();
        double x4 = secondPointSecondWall.getxValue();
        double y4 = secondPointSecondWall.getyValue();
        denominator=(y4-y3)*(x1-x2)-(x4-x3)*(y1-y2);
        if (denominator == 0){
                return false;
        }
        else{
            numerator_a=(x4-x2)*(y4-y3)-(x4-x3)*(y4-y2);
            numerator_b=(x1-x2)*(y4-y2)-(x4-x2)*(y1-y2);
            Ua=numerator_a/denominator;
            Ub=numerator_b/denominator;
            if (Ua >=0 && Ua <=1 && Ub >=0 && Ub <=1){
                if    ((x1 == x3 && y1 == y3)
                        || (x1 == x4 && y1 == y4)
                        || (x2 == x3 && y2 == y3)
                        || (x2 == x4 && y2 == y4))
                    return false;
                else return true;
            }
            else return false;
        }
    }
}
