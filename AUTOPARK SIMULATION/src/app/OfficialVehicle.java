package app;

public class OfficialVehicle implements Vehicle {
    private String plate;
    private Subscription subscription;

    public OfficialVehicle(String plate) {
        this.plate = plate;
    }

    @Override
    public Subscription getSubscription() {
        return subscription;
    }

    @Override
    public String getPlate() {
        return plate;
    }

    @Override
    public boolean isSpecial() {
        return true;
    }
}
