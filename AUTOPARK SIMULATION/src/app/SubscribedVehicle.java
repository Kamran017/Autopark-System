package app;

public class SubscribedVehicle implements Vehicle {
    private String plate;
    private Subscription subscription;

    public SubscribedVehicle(String plate, Subscription subscription) {
        this.plate = plate;
        this.subscription = subscription;
    }

    @Override
    public String getPlate() {
        return plate;
    }

    @Override
    public Subscription getSubscription() {
        return subscription;
    }

    @Override
    public boolean isSpecial() {
        return false;
    }

    // optional
    public void setPlate(String plate) {
        this.plate = plate;
    }

    // optional
    public void setSubscription(Subscription subscription) {
        this.subscription = subscription;
    }
}
