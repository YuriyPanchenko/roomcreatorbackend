package yuriy.model;

public class Point {
    private double xValue;
    private double yValue;

    public Point() {
    }

    public Point(double xValue, double yValue) {
        this.xValue = xValue;
        this.yValue = yValue;
    }

    public double getxValue() {
        return xValue;
    }

    public void setxValue(double xValue) {
        this.xValue = xValue;
    }

    public double getyValue() {
        return yValue;
    }

    public void setyValue(double yValue) {
        this.yValue = yValue;
    }

    @Override
    public String toString() {
        return "Point{" +
                "xValue=" + xValue +
                ", yValue=" + yValue +
                '}';
    }
}
