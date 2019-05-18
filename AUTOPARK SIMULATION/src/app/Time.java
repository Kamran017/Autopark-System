package app;

public class Time {
    private int hour;
    private int minute;

    public Time(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
    }

    /**
     * Calculates the differences between two times.
     * @return Absolute value of differences.
     */
    public int getDifference(Time other) {
        return (Math.abs(((hour * 60 + minute) - (other.hour * 60 + other.minute))));
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }
}
