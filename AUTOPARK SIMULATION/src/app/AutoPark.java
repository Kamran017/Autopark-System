package app;

import java.util.ArrayList;

/**
 *  - All vehicles (including Official Vehicles) are added
 *    to the ParkRecord list whenever they enter to the AutoPark.
 *  - The capacity of the SubscribedVehicle list is assumed to be infinite.
 *  - enlargeCapacity() method enlarges only ParkRecord list.
 */
public class AutoPark {

    private int capacity;
    private ArrayList<SubscribedVehicle> subscribedVehiclesList = new ArrayList<>();
    private ArrayList<ParkRecord> parkRecordsList = new ArrayList<>(capacity);
    private double hourlyFee;
    private double incomeDaily = 0.0;

    public AutoPark(double hourlyFee, int capacity) {
        this.hourlyFee = hourlyFee;
        this.capacity = capacity;
        parkRecordsList.ensureCapacity(capacity);
    }

    /**
     * Searchs for vehicle with plate in the SubscribedVehicles arraylist.
     * @param plate Plate of the subscribed vehicle.
     * @return If vehicle exists return this vehicle. If not returns null.
     */
    public SubscribedVehicle searchVehicle(String plate) {
        for (SubscribedVehicle s: subscribedVehiclesList) {
            if (s.getPlate().equals(plate)) {
                return s;
            }
        }
        return null;
    }

    /**
     * Searches for a vehicle int the ParkRecord[] Arraylist.
     * @param plate Plate of the parked vehicle.
     * @return If vehicle exists return true.
     */
    public boolean isParked(String plate) {
        for (ParkRecord p: parkRecordsList) {
            if (p.getVehicle().getPlate().equals(plate)) return true;
        }
        return false;
    }

    /** If the capacity is full increase it by 1 */
    private void enlargeVehicleArray() {
        capacity += 1;
        parkRecordsList.ensureCapacity(capacity);
    }

    /**
     * Adds new vehicle to the Subscribed Vehicles list.
     * @param vehicle Vehicle to be added to the list
     */
    public boolean addVehicle(SubscribedVehicle vehicle) {
        // check if vehicle exists in the array
        if (searchVehicle(vehicle.getPlate()) != null)
            return false;

        subscribedVehiclesList.add(vehicle);
        return true;
    }

    /**
     * Controls if vehicle exist. Checks if it is a subscribed vehicle, if so
     * add it to ParkRecords[] and return, otherwise create new Vehicle object
     * and store it in the arraylist. Official Vehicles are stored too.
     * @param plate Plate of the vehicle to be added.
     * @param enter Entrance time of the vehicle to the parking.
     * @param isOfficial Distinguishing between regular and official vehicle.
     * @return Success or failure of the entering.
     */
    public boolean vehicleEnters(String plate, Time enter, boolean isOfficial) {
        for (ParkRecord p: parkRecordsList) // check if vehicle already entered
            if (p.getVehicle().getPlate().equals(plate))
                return false;
        if (parkRecordsList.size() == capacity) {
            enlargeVehicleArray();
            System.out.println("Capacity is automatically increased by 1.");
        }
        ParkRecord parkRecord;
        if (searchVehicle(plate) != null) {
            parkRecord = new ParkRecord(enter, searchVehicle(plate));
            parkRecordsList.add(parkRecord);
        } else {
            if (!isOfficial) {
                parkRecord = new ParkRecord(enter, new RegularVehicle(plate));
                parkRecordsList.add(parkRecord);
            } else {
                parkRecord = new ParkRecord(enter, new OfficialVehicle(plate));
                parkRecordsList.add(parkRecord);
            }
        }

        return true;
    }

    /**
     * If vehicle exits, regular and vehicles with
     * invalid subsciption will pay the fee.
     * @param plate Plate of the vehicle that wants to exit.
     * @param exit Time of the exit.
     */
    public boolean vehicleExits(String plate, Time exit) {

        // find the car in the subscriptions list
        for (ParkRecord p: parkRecordsList) {
            if (p.getVehicle().getPlate().equals(plate)) {
                // check if it's subscribed vehicle
                if (subscribedVehiclesList.contains(p.getVehicle())) {
                    // check it's validness
                    if (p.getVehicle().getSubscription().isValid()) {
                        parkRecordsList.remove(p);
                        return true;
                    }
                }
                // check if it's official vehicle
                if (p.getVehicle().isSpecial()) {
                    parkRecordsList.remove(p);
                    return true;
                }
                // calculate the hourly fee if subc. is invalid
                p.setExitTime(exit);
                int durationInMin = p.getParkingDuration();
                int duration = durationInMin / 60;
                // if duration time is more than 0, pay fee
                if (duration > 0) {
                    double income = duration * hourlyFee;
                    incomeDaily += income;
                }

                parkRecordsList.remove(p);
                return true;
            }
        }

        return false;
    }

    /** @return All information about the autopark. */
    @Override
    public String toString() {

        String subslist = "";
        for (SubscribedVehicle s: subscribedVehiclesList)
            subslist += s.getPlate() + " ";

        String parklist = "";
        for (ParkRecord p: parkRecordsList)
            parklist += p.getVehicle().getPlate() + " ";

        String officiallsList = "";
        for (ParkRecord p: parkRecordsList)
            if (p.getVehicle().isSpecial())
                officiallsList += p.getVehicle().getPlate() + " ";
        
        return "AutoPark{" +
                "capacity=" + capacity +
                ", subscribedVehiclesList=" + subslist +
                ", parkRecordsList=" + parklist +
                ", official vehicles=" + officiallsList +
                ", hourlyFee=" + hourlyFee +
                ", incomeDaily=" + incomeDaily +
                '}';
    }
}
