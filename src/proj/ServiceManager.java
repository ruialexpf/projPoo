package proj;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Manages the core services of the mobility application.
 * This class handles data loading from files, user interactions for creating rentals,
 * data validation, and business logic such as calculating total revenue. It acts as a
 * central coordinator for the application's operations.
 */
public class ServiceManager {
    /**
     * Default constructor for the {@code ServiceManager}.
     */
    public ServiceManager(){}

    /**
     * Interactively prompts the user to add new rentals to the system.
     * This method guides the user through a console-based interface to input rental details.
     * It validates the existence of the user and vehicle, checks date formats, and ensures
     * all required information is gathered before creating a new {@link Rental} object.
     * The process can be repeated to add multiple rentals in one session.
     *
     * @param rentals The list of {@link Rental} objects to which new rentals will be added.
     * @param users The list of available {@link User}s in the system.
     * @param vehicles The list of available {@link Vehicle}s in the system.
     */
    public void addRentals(ArrayList<Rental> rentals, ArrayList<User> users, ArrayList<Vehicle> vehicles){
        String mechNo;
        String id;
        String startDate;
        int startHour;
        String endDate;
        int endHour;
        String helmetStr = "";
        boolean helmet;
        String lightStr;
        boolean light;

        User u = null;
        Vehicle v = null;
        Rental rent;

        Scanner scanner = new Scanner(System.in);
        while(true){

            if(askForStr("Novo aluguer? (0 = nao; 1 = sim) ", scanner).compareTo("0") == 0){
                break;
            }

            mechNo = askForStr("Numero mecanografico do utilizador: ", scanner);
            for(int i = 0; i < users.size(); i++){
                if(mechNo.compareTo(users.get(i).getMechNo()) == 0){
                    u = users.get(i);
                }
            }
            if(u == null){
                System.out.println("Numero mecanografico invalido");
                continue;
            }
            
            id = askForStr("ID do veiculo: ", scanner);
            for(int i = 0; i < vehicles.size(); i++){
                if(id.compareTo(vehicles.get(i).getIdentifier()) == 0){
                    v = vehicles.get(i);
                }
            }
            if(v == null){
                System.out.println("ID invalida");
                continue;
            }

            startDate = askForStr("Data de inicio(dd/mm/aaaa): ", scanner);
            if(!isValidDate(startDate)){
                System.out.println("Data de incio invalida");
                continue;
            }

            startHour = Integer.parseInt(askForStr("Hora de incio: ", scanner));
            if(startHour < 0 || startHour > 23){
                System.out.println("Hora de inicio invalida");
                continue;
            }

            endDate = askForStr("Data de fim: ", scanner);
            if(!isValidDate(endDate)){
                System.out.println("Data de fim invalida");
                continue;
            }

            endHour = Integer.parseInt(askForStr("Hora de fim: ", scanner));
            if(endHour < 0 || endHour > 23){
                System.out.println("Hora de fim invalida");
                continue;
            }

            helmetStr = askForStr("Capacete? (0 = nao; 1 = sim) ", scanner);
            if(helmetStr.compareTo("0") == 0) helmet = false;
            else helmet = true;

            lightStr = askForStr("Luz? (0 = nao; 1 = sim) ", scanner);
            if(lightStr.compareTo("0") == 0) light = false;
            else light = true;

            rent = new Rental(u, v, startDate, startHour, endDate, endHour, helmet, light);
            rentals.add(rent);
            System.out.println(rent);
            System.out.println();
        }
    }

    /**
     * Validates if a given date string is in a valid format (dd/mm/yyyy) and represents a real date.
     * @param date The date string to validate.
     * @return True if the date is valid, false otherwise.
     */
    private boolean isValidDate(String date){
        String[] dateSplit = date.split("/");
        int[] dateInt = new int[3];

        if(dateSplit.length == 3){
            for(int i = 0; i <dateSplit.length; i++){
                dateInt[i] = Integer.parseInt(dateSplit[i]);
            }
            if(dateInt[1] > 0 && dateInt[1] <= 12){
                if(Arrays.asList(1, 3, 5, 7, 8, 10, 12).contains(dateInt[1]) && dateInt[0] > 0 && dateInt[0] <= 31){
                    return true;
                }
                else if(dateInt[1] == 2 && dateInt[0] > 0 && dateInt[0] <= 29){
                    if(dateInt[2] % 4 == 0){
                        if(dateInt[2] % 100 == 0){
                            if(dateInt[2] % 400 == 0) return true;
                        }
                        else return true;
                    }
                    else if(dateInt[0] <= 28) return true;
                }
                else if(dateInt[0] > 0 && dateInt[0] <= 30) return true;
            }
        }
        return false;
    }

    /**
     * Helper method to display a prompt to the console and read a line of input from the user.
     *
     * @param prompt The message to display to the user.
     * @param scanner The Scanner object to read input.
     * @return The string entered by the user.
     */
    private String askForStr(String prompt, Scanner scanner) {
        System.out.print(prompt);
        String input = scanner.nextLine();
        return input;
    }

    /**
     * Prints all rentals in the provided list to the console using their {@code toString} representation.
     *
     * @param rentals The list of rentals to display.
     */
    public void listRentals(ArrayList<Rental> rentals){
        for(int i = 0; i < rentals.size(); i++){
            System.out.println(rentals.get(i));
        }
    }

    /**
     * Reads user data from a comma-separated text file and populates a list of {@link User} objects.
     * The file format determines the type of user (Student, Lecturer, NonLecturer) to be created.
     * Lines with incorrect formatting are skipped.
     *
     * @param path The path to the user data file.
     * @param dest The list to be populated with User objects.
     */
    public void readUsers(String path, ArrayList<User> dest){
        File f = new File(path);

        if(f.exists() && f.isFile()){
            try (BufferedReader bf = new BufferedReader(new FileReader(f))){
                String line;

                int years;
                Student stud;
                Lecturer lect;
                NonLecturer nonL;
                while((line = bf.readLine()) != null){
                    if(line.split(",").length != 5){
                        System.out.println("Line formatting error!");
                        continue;
                    }
                    String[] params = line.split(",");
                    if(params[0].compareTo("s") == 0){
                        stud = new Student(params[1], params[2], params[3], params[4]);
                        dest.add(stud);
                    }
                    else if(params[0].compareTo("l") == 0){
                        years = Integer.parseInt(params[3]);
                        lect = new Lecturer(params[1], params[2], years, params[4]);
                        dest.add(lect);
                    }
                    else if(params[0].compareTo("n") == 0){
                        years = Integer.parseInt(params[3]);
                        nonL = new NonLecturer(params[1], params[2], years, params[4]);
                        dest.add(nonL);
                    }
                }
            } catch (FileNotFoundException e) {
                System.out.println("File opening error");
            } catch (IOException e) {
                System.out.println("file reading error");
            }
        }
    }

    /**
     * Reads vehicle data from a text file and populates a list of Vehicle objects.
     * @param path The path to the vehicle data file.
     * @param dest The list to be populated with Vehicle objects.
     */
    public void readVehicles(String path, ArrayList<Vehicle> dest){
        File f = new File(path);

        if(f.exists() && f.isFile()){
            try (BufferedReader bf = new BufferedReader(new FileReader(f))){
                String line;

                boolean b;
                int battery;
                Bicycle bic;
                Scooter scoot;
                Ebike eb;
                while((line = bf.readLine()) != null){
                    String[] params = line.split(",");
                    if(params[0].compareTo("b") == 0){
                        if(params.length != 4){
                            System.out.println("Line formatting error!");
                            continue;
                        }
                        b = Boolean.parseBoolean(params[3]);
                        bic = new Bicycle(params[1], params[2], b);
                        dest.add(bic);
                    }
                    else if(params[0].compareTo("t") == 0){
                        if(params.length != 5){
                            System.out.println("Line formatting error!");
                            continue;
                        }
                        battery = Integer.parseInt(params[3]);
                        b = Boolean.parseBoolean(params[4]);
                        scoot = new Scooter(params[1], params[2], battery, b);
                        dest.add(scoot);
                    }
                    else if(params[0].compareTo("e") == 0){
                        if(params.length != 5){
                            System.out.println("Line formatting error!");
                            continue;
                        }
                        battery = Integer.parseInt(params[3]);
                        b = Boolean.parseBoolean(params[4]);
                        eb = new Ebike(params[1], params[2], battery, b);
                        dest.add(eb);
                    }
                }
            } catch (FileNotFoundException e) {
                System.out.println("File opening error");
            } catch (IOException e) {
                System.out.println("file reading error");
            }
        }
    }

    /**
     * Reads serialized Rental objects from a file.
     * @param path The path to the object file.
     * @param dest The list to be populated with Rental objects.
     */
    public void readRentals(String path, ArrayList<Rental> dest){
        File f = new File(path);
        
        if(f.exists() && f.isFile() && f.length()>0){
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))){
                Object obj = ois.readObject();
                ArrayList<?> raw = (ArrayList<?>) obj;
                for(int i = 0; i < raw.size(); i++){
                    dest.add((Rental)raw.get(i));
                }
            } catch (FileNotFoundException e) {
                System.out.println("File opening error");
            }  catch (IOException e) {
                System.out.println("File reading error");
            } catch (ClassNotFoundException e){
                System.out.println("Object conversion error");
            }
        }
    }

    /**
     * Writes a list of Rental objects to a file via serialization.
     * @param path The path to the output file.
     * @param src The list of Rental objects to serialize.
     */
    public void writeRentals(String path, ArrayList<Rental> src){
        File f = new File(path);
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f))){
            oos.writeObject(src);
        } catch(FileNotFoundException ex) {
            System.out.println("File creation error");
        } catch(IOException ex){
            System.out.println("File writing error");
        }
    }


    /**
     * Calculates the final price for a rental.
     * @param user The user renting.
     * @param vehicle The vehicle being rented.
     * @param durationInHours The total duration of the rental in hours.
     * @param hasHelmet Whether a helmet is included.
     * @param hasLight Whether a light is included.
     * @return The final cost in Euros.
     */
    public double calculateRentalPrice(User user, Vehicle vehicle, double durationInHours, boolean hasHelmet, boolean hasLight) {
        double effectiveHours;
        double hourlyRate = vehicle.getHourlyRate(user);
        if(durationInHours >= 24){
            double daycount = durationInHours / 24; //15
            int daycountInt = (int)daycount; //15
            double hours = daycountInt*8; //120
            effectiveHours = (durationInHours - (daycountInt * 24)) + hours; // (durationInHours - 360) + 120 = 125
        }
        else{effectiveHours = durationInHours;}
        double vehicleCost = hourlyRate * effectiveHours;
        int rentalDays = (int)(durationInHours / 24.0);
        double servicesCost = 0;
        rentalDays += (durationInHours % 24 > 0) ? 1 : 0;
        if (hasHelmet) {
            servicesCost += 5.0 * rentalDays;
        }
        if (hasLight) {
            servicesCost += 2.5 * rentalDays;
        }
        double totalCost = vehicleCost + servicesCost;
        return user.applyDiscount(totalCost);
    }

    /**
     * Calculates the total revenue from a list of rentals.
     * @param rentals The list of rentals to process.
     * @return The total revenue in Euros.
     */
    public double calculateTotalRevenue(ArrayList<Rental> rentals) {
        double totalRevenue = 0.0;
        for (Rental rental : rentals) {
            User user = rental.getUser();
            Vehicle vehicle = rental.getVehicle();
            int duration = rental.rentalDuration();
            boolean hasHelmet = rental.isHelmet();
            boolean hasLight = rental.isLight();
            double rentalPrice = calculateRentalPrice(user, vehicle, duration, hasHelmet, hasLight);
            totalRevenue += rentalPrice;
        }
        return totalRevenue;
    }
}