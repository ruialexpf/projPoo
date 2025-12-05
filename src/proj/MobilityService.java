package proj;

import java.util.ArrayList;

/**
 * The main class for the Mobility Service application.
 * This class is responsible for initializing the {@link ServiceManager}, loading data from files,
 * processing rentals, and demonstrating the core functionalities of the system.
 */
public class MobilityService {
    /**
     * The main entry point for the application.
     * This method orchestrates the entire process:
     * 1. Creates a {@link ServiceManager}.
     * 2. Loads user and vehicle data from text files.
     * 3. Reads existing rental data from a serialized object file.
     * 4. Prompts for and adds new rentals.
     * 5. Lists all current rentals.
     * 6. Calculates and displays the total revenue.
     * 7. Writes the updated list of rentals back to the object file.
     *
     * @param args Command line arguments (not used in this application).
     */
    public static void main(String[] args) {
        String fileNameUsers = "utilizadores.txt";
        String fileNameVehicles = "veiculos.txt";
        String rentalsObjFile = "alugueres.obj";

        ServiceManager manager = new ServiceManager();
        
        ArrayList<User> users = new ArrayList<>();
        manager.readUsers(fileNameUsers, users);
        for(int i = 0; i < users.size(); i++){
            System.out.println(users.get(i));
        }

        System.out.println();

        ArrayList<Vehicle> vehicles = new ArrayList<>();
        manager.readVehicles(fileNameVehicles, vehicles);
        for(int i = 0; i < vehicles.size(); i++){
            System.out.println(vehicles.get(i));
        }

        System.out.println();

        ArrayList<Rental> rentals = new ArrayList<>();
        manager.readRentals(rentalsObjFile, rentals);
        manager.addRentals(rentals, users, vehicles);
        manager.listRentals(rentals);
        manager.writeRentals(rentalsObjFile, rentals);
        System.out.println("Total Revenue: " + manager.calculateTotalRevenue(rentals));
    }
}