package data;
import java.io.Serializable;

public final class ReceivingData implements Serializable {
    private final boolean collision;
    private final float x;
    private final float y;
    private final float R;
    private final String executionTime;
    private final String time;

    public ReceivingData() {
        this.collision = false;
        this.x = 0;
        this.y = 0;
        this.R = 0;
        this.executionTime = "0";
        this.time = "2004.12.01 12:24:48";
    }

    public ReceivingData(boolean collision, float x, float y, float R, String time, String executionTime) {
        this.collision = collision;
        this.x = x;
        this.y = y;
        this.R = R;
        this.time = time;
        this.executionTime = executionTime;
    }

    public boolean isReceiving() {
        return collision;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getR() {
        return R;
    }

    public String getTime() {
        return time;
    }

    public String getExecutionTime() {
        return executionTime;
    }
}
