package app;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.InputMismatchException;
import java.util.Scanner;

/** Main class of the program.
 *  Last tested on May 17, 2019.
 *  @author 17011903 Nihad Guluzade, 17011904 Kamran Balayev */
public class Main {

    public static void main(String[] args) {

        Scanner inString = new Scanner(System.in);

        boolean input = false; // control for invalid inputs

        // Arraylist to keep vehicles
        ArrayList<Vehicle> vehicles = new ArrayList<>();

        System.out.println("Autopark notes:");
        System.out.println("Note-1: Capacity will be automatically increased if more vehicles enter.");
        System.out.println("Note-2: Vehicle plates are case-sensitive.");
        System.out.println("Note-3: Standart subscription is 1 month and automatically created. If you want to test" +
                " dates consider running JUNIT test cases.");
        System.out.println("Note-4: To properly calculate daily income program must be on for at least for 1 hour. " +
                "Consider running JUNIT test cases.");

        System.out.println("\nPlease, enter the hourly fee of the autopark: ");
        double hourlyFee = 0.0;
        while (!input) { // control for user input
            try {
                Scanner in = new Scanner(System.in);
                hourlyFee = in.nextDouble();
                input = true;
            } catch (InputMismatchException e) {
                System.out.println("Input type error. Try again.");
            }
        }
        input = false;

        System.out.println("Please, enter the capacity of the autopark: ");
        int capacity = 0;
        while (!input) { // control for user input
            try {
                Scanner in = new Scanner(System.in);
                capacity = in.nextInt();
                input = true;
            } catch (InputMismatchException e) {
                System.out.println("Input type error. Try again.");
            }
        }
        input = false;

        AutoPark autoPark = new AutoPark(hourlyFee, capacity);

        System.out.println("Autopark {hourlyFee = " + hourlyFee + ", capacity = " + capacity + "}");
        int action = -1, type = -1; // choosen option, vehicle type input
        do {

            System.out.println("\nChoose the action:");
            System.out.println("1 -> Create vehicle (not adding to autopark, just creating)");
            System.out.println("2 -> Search for existence of subscribed vehicle");
            System.out.println("3 -> Check if vehicle parked");
            System.out.println("4 -> Enter vehicle to the autopark");
            System.out.println("5 -> Exit vehicle from the autopark");
            System.out.println("6 -> Call autopark description");
            System.out.println("7 -> See the created vehicles");
            System.out.println("8 -> Exit from the program");
            while (!input) { // control for user input
                try {
                    Scanner in = new Scanner(System.in);
                    action = in.nextInt();
                    input = true;
                } catch (InputMismatchException e) {
                    System.out.println("Input type error. Try again.");
                }
            }
            input = false;
            switch (action) {
                case 1: // create vehicle
                    System.out.println("Select vehicle type (1-Regular, 2-Subscribed, 3-Official): ");
                    while (!input) { // control for user input
                        try {
                            Scanner in = new Scanner(System.in);
                            type = in.nextInt();
                            input = true;
                        } catch (InputMismatchException e) {
                            System.out.println("Input type error. Try again.");
                        }
                    }
                    input = false;
                    System.out.println("Enter the plate: ");
                    String plate = inString.nextLine();
                    if (type == 1) {
                        boolean exist = false;
                        for (Vehicle v: vehicles) {
                            if (v.getPlate().equals(plate))
                                exist = true;
                        }
                        if (!exist) {
                            vehicles.add(new RegularVehicle(plate));
                            System.out.println("New regular vehicle has been successfully created.");
                        }
                        else
                            System.out.println("Vehicle with the same plate have already been created.");
                    }
                    else if (type == 2) {
                        // set the end date of subscription, 1 month is standard
                        Date end = new Date(Date.getToday().getDay(),
                                Date.getToday().getMonth() + 1, Date.getToday().getYear());
                        SubscribedVehicle subscribedVehicle = new SubscribedVehicle(plate,
                                new Subscription(Date.getToday(), end, plate));
                        boolean exist = false;
                        for (Vehicle v: vehicles) {
                            if (v.getPlate().equals(plate))
                                exist = true;
                        }
                        if (!exist) {
                            vehicles.add(subscribedVehicle);
                            autoPark.addVehicle(subscribedVehicle);
                            System.out.println("New subscribed vehicle has been successfully created.");
                        } else {
                            System.out.println("Vehicle with the same plate have already been created.");
                            break;
                        }
                        System.out.println("Plate: " + plate + ", Subscription Start Date: "
                                + subscribedVehicle.getSubscription().getBegin().getDay() + "/"
                                + subscribedVehicle.getSubscription().getBegin().getMonth() + "/"
                                + subscribedVehicle.getSubscription().getBegin().getYear() +
                                ", End Date: " + subscribedVehicle.getSubscription().getEnd().getDay()
                                + "/" + subscribedVehicle.getSubscription().getEnd().getMonth()
                                + "/" + subscribedVehicle.getSubscription().getEnd().getYear());
                    }
                    else if (type == 3) {
                        boolean exist = false;
                        for (Vehicle v: vehicles) {
                            if (v.getPlate().equals(plate))
                                exist = true;
                        }
                        if (!exist) {
                            vehicles.add(new OfficialVehicle(plate));
                            System.out.println("New official vehicle has been successfully created");
                        } else {
                            System.out.println("Vehicle with the same plate have alreay been created.");
                        }
                    } else System.out.println("Wrong option of vehicle.");
                    break;
                case 2: // search existence subscribed vehicle
                    System.out.println("Enter the plate to search: ");
                    plate = inString.nextLine();
                    SubscribedVehicle subscribedVehicle = autoPark.searchVehicle(plate);
                    if (subscribedVehicle != null) {
                        System.out.println("Plate: " + plate + ", Subscription Start Date: "
                                + subscribedVehicle.getSubscription().getBegin().getDay() + "/"
                                + subscribedVehicle.getSubscription().getBegin().getMonth() + "/"
                                + subscribedVehicle.getSubscription().getBegin().getYear() +
                                ", End Date: " + subscribedVehicle.getSubscription().getEnd().getDay()
                                + "/" + subscribedVehicle.getSubscription().getEnd().getMonth()
                                + "/" + subscribedVehicle.getSubscription().getEnd().getYear());
                    }
                    else System.out.println("Couldn't find the vehicle with this plate.");
                    break;
                case 3: // check if vehicle parked
                    System.out.println("Enter the plate to check if it's parked: ");
                    plate = inString.nextLine();

                    if (autoPark.isParked(plate)) {
                        System.out.println("Vehicle is parked on our autopark.");
                    }
                    else
                        System.out.println("Vehicle is NOT parked on our autopark.");
                    break;
                case 4: // Enter the vehicle
                    System.out.println("Enter the plate of the vehicle that is to be entered: ");
                    plate = inString.nextLine();
                    boolean official = false, found = false;
                    for (Vehicle v: vehicles) {
                        if (v.getPlate().equals(plate)) {
                            found = true;
                            if (v.isSpecial())
                                official = true;
                        }
                    }
                    if (!found) {
                        System.out.println("Such vehicle is not created.");
                        break;
                    }
                    Calendar calendar = Calendar.getInstance();
                    if (autoPark.vehicleEnters(plate, new Time(calendar.get(Calendar.HOUR_OF_DAY),
                            calendar.get(Calendar.MINUTE)), official)) {
                        System.out.println("Vehicle entered in " + calendar.get(Calendar.HOUR_OF_DAY)
                                        + ":" + calendar.get(Calendar.MINUTE));
                    } else {
                        System.out.println("This vehicle is already in the autopark.");
                    }
                    break;
                case 5: // Exit from autopark
                    System.out.println("Enter the vehicle that is to be exited from the autopark: ");
                    plate = inString.nextLine();
                    calendar = Calendar.getInstance();
                    if (autoPark.vehicleExits(plate, new Time(calendar.get(Calendar.HOUR_OF_DAY),
                            calendar.get(Calendar.MINUTE)))) {
                        System.out.println("Vehicle successfully exited from the autopark in "
                                    + calendar.get(Calendar.HOUR_OF_DAY) + ":"
                                    + calendar.get(Calendar.MINUTE));
                    } else {
                        System.out.println("Vehicle with this plate not parked.");
                    }
                    break;
                case 6: // toString() method
                    System.out.println(autoPark.toString());
                    break;
                case 7: // see created vehicles
                    System.out.println("Created vehicles: ");
                    for (Vehicle v: vehicles)
                        System.out.print(v.getPlate() + " ");
                    break;
                case 8:
                    System.out.println("Program exit called.");
                    break;
                default:
                    System.out.println("Wrong option. Try again.");
                    break;
            }
        } while (action != 8);

        inString.close();

    }

}
