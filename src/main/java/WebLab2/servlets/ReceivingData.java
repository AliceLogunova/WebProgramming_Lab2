package WebLab2.servlets;

import java.io.Serializable;

public final class ReceivingData implements Serializable {
    private static float x;
    private static float y;
    private static float R;
    private static String collision;
    private static double executionTime;
    private static String time;

    public ReceivingData(float x, float y, float R, String collision, String time, Double executionTime) {
        this.x = x;
        this.y = y;
        this.R = R;
        this.collision = collision;
        this.time = time;
        this.executionTime = executionTime;
    }

    public static float getX() {
        return x;
    }

    public static float getY() {
        return y;
    }

    public static float getR() {
        return R;
    }

    public static String getCollision() {return collision;}

    public static String getTime() {
        return time;
    }

    public static double getExecutionTime() {
        return executionTime;
    }
}
