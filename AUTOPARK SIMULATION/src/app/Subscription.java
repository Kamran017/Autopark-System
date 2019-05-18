package app;

/** Subscriptions for vehicles are created for 1 month automatically. */
public class Subscription {
    private Date begin;
    private Date end;
    private SubscribedVehicle vehicle;

    public Subscription(Date begin, Date end, String plate) {
        this.begin = begin;
        this.end = end;
        vehicle = new SubscribedVehicle(plate, this);
    }

    /**
     * Checks if subscription beginning date is before and end
     * date is after, or begin/end date is equals with the today's date.
     * @return If so return true.
     */
    public boolean isValid() {
        if (begin.isBeforeThan(Date.getToday()) && end.isAfterThan(Date.getToday())
            || begin.isEqualsWith(Date.getToday()) || end.isEqualsWith(Date.getToday()))
            return true;
        return false;
    }

    // optional method

    public Date getBegin() {
        return begin;
    }

    public Date getEnd() {
        return end;
    }

    public SubscribedVehicle getVehicle() {
        return vehicle;
    }

    public void setBegin(Date begin) {
        this.begin = begin;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public void setVehicle(SubscribedVehicle vehicle) {
        this.vehicle = vehicle;
    }
}
