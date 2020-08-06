package yuriy.service;

import yuriy.model.Point;

import java.util.LinkedHashSet;

public class CheckRoomService {
    public static String message(LinkedHashSet<Point> points){
        String message = "";
        if(CheckRightAngles.DoPointsContainNotRightAngles(points))
            message += "Illegal, Room walls must create right angles.\n";
        if(CheckIntersect.checkPointsAtTheIntersectionWalls(points))
            message += "Illegal, Room walls do not intersect.\n";
        if(CheckRightDirect.checkRightDirect(points))
            message += "Illegal, the points are counterclockwise.";
        if(message.length() == 0)
            return "Legal.";
        else return message;
    }
}
