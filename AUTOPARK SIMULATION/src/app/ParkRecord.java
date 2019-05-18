package app;

public class ParkRecord {
    private Time enterTime;
    private Time exitTime;
    private Vehicle vehicle;

    public ParkRecord(Time enterTime, Vehicle vehicle) {
        this.enterTime = enterTime;
        this.vehicle = vehicle;
    }

    /**
     * Calculates time between enterTime and exitTime. Fixes time if minutes
     * is more than 60.
     */
    public int getParkingDuration() {
        if (enterTime.getMinute() > 60) {
            enterTime.setHour(enterTime.getHour() + 1);
            enterTime.setMinute(enterTime.getMinute() - 60);
        }
        if (exitTime.getMinute() > 60) {
            exitTime.setHour(exitTime.getHour() + 1);
            exitTime.setMinute(exitTime.getMinute() - 60);
        }

        int result = exitTime.getDifference(enterTime);
        if ((exitTime.getHour() * 60 + exitTime.getMinute()) < (enterTime.getHour() * 60 + enterTime.getMinute()))
            result = 1440 - result; // 24hrs

        return result;
    }

    public Time getEnterTime() {
        return enterTime;
    }

    public void setEnterTime(Time enterTime) {
        this.enterTime = enterTime;
    }

    public Time getExitTime() {
        return exitTime;
    }

    public void setExitTime(Time exitTime) {
        this.exitTime = exitTime;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
}
