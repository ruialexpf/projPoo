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
import java.util.Scanner;

public class MobilityService {
    public static void main(String[] args) {
        String fileNameUsers = "utilizadores.txt";
        String fileNameVehicles = "veiculos.txt";
        String rentalsObjFile = "alugueres.obj";
        
        ArrayList<User> users = new ArrayList<User>();
        readUsers(fileNameUsers, users);
        for(int i = 0; i < users.size(); i++){
            System.out.println(users.get(i));
        }

        System.out.println();

        ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();
        readVehicles(fileNameVehicles, vehicles);
        for(int i = 0; i < vehicles.size(); i++){
            System.out.println(vehicles.get(i));
        }

        System.out.println();

        ArrayList<Rental> rentals = new ArrayList<Rental>();
        readRentals(rentalsObjFile, rentals);
        addRentals(rentals, users, vehicles);
        listRentals(rentals);
        writeRentals(rentalsObjFile, rentals);
    }

    private static void addRentals(ArrayList<Rental> rentals, ArrayList<User> users, ArrayList<Vehicle> vehicles){
        int cnt = 0;

        String mechNo;
        String id;
        String startDate;
        int startHour;
        String endDate;
        int endHour;
        boolean helmet = false;
        String helmetStr;
        boolean light = false;
        String lightStr;

        User u;
        Vehicle v;
        Rental rent;
        boolean found = false;

        Scanner scanner = new Scanner(System.in);
        while(cnt < users.size()){
            found = false;

            System.out.println("Novo aluguer");
            mechNo = askForStr("Numero mecanografico do utilizador: ", scanner);
            id = askForStr("ID do veiculo: ", scanner);
            startDate = askForStr("Data de inicio(dd/mm/aaaa): ", scanner);
            startHour = Integer.parseInt(askForStr("Hora de incio: ", scanner));
            endDate = askForStr("Data de fim: ", scanner);
            endHour = Integer.parseInt(askForStr("Hora de fim: ", scanner));

            helmetStr = askForStr("Capacete? (0 = nao; 1 = sim) ", scanner);
            if(helmetStr.compareTo("0") == 0) helmet = false;
            else helmet = true;

            lightStr = askForStr("Luz? (0 = nao; 1 = sim) ", scanner);
            if(lightStr.compareTo("0") == 0) light = false;
            else light = true;


            for(int i = 0; i < users.size(); i++){
                for(int j = 0; j < vehicles.size(); j++){
                    u = users.get(i);
                    v = vehicles.get(j);
                    if(mechNo.compareTo(u.getMechNo()) == 0 && id.compareTo(v.getIdentifier()) == 0){
                        rent = new Rental(u, v, startDate, startHour, endDate, endHour, helmet, light);
                        rentals.add(rent);
                        System.out.println(rent);
                        found = true;
                    }
                }
            }

            if(found){
                cnt += 1;
            }
            else{
                System.out.println("Numero mecanografico ou ID nao encontrados");
            }
            System.out.println();
            if(cnt != users.size() && askForStr("Continuar com novo aluguer? (0 = nao; 1 = sim) ", scanner).compareTo("0") == 0){
                break;
            }
        }
        scanner.close();
    }

    private static String askForStr(String prompt, Scanner scanner) {
        System.out.print(prompt);
        String input = scanner.nextLine();
        return input;
    }

    private static void listRentals(ArrayList<Rental> rentals){
        double total = 0;
        for(int i = 0; i < rentals.size(); i++){
            System.out.println(rentals.get(i));
           // total +=  rentals.get(i).getPrice();
        }
        System.out.println("Total: " + total);
    }

    private static void readUsers(String path, ArrayList<User> dest){
        File f = new File(path);

        if(f.exists() && f.isFile()){
            FileReader fr;
            try {
                fr = new FileReader(f);
                BufferedReader bf = new BufferedReader(fr);
                String line;
                String[] params = new String[5];

                int years;
                String[] facult;
                Student stud;
                Lecturer lect;
                NonLecturer nonL;
                while((line = bf.readLine()) != null){
                    if(line.split(",").length != 5){
                        System.out.println("Line formatting error!");
                        continue;
                    }
                    params = line.split(",");
                    if(params[0].compareTo("s") == 0){
                        stud = new Student(params[1], params[2], params[3], params[4]);
                        dest.add(stud);
                    }
                    else if(params[0].compareTo("l") == 0){
                        years = Integer.parseInt(params[3]);
                        facult = params[4].split("/");
                        lect = new Lecturer(params[1], params[2], years, facult);
                        dest.add(lect);
                    }
                    else if(params[0].compareTo("n") == 0){
                        years = Integer.parseInt(params[3]);
                        nonL = new NonLecturer(params[1], params[2], years, params[4]);
                        dest.add(nonL);
                    }
                }
                bf.close();
            } catch (FileNotFoundException e) {
                System.out.println("File opening error");
            } catch (IOException e) {
                System.out.println("file reading error");
            }
        }
    }

    private static void readVehicles(String path, ArrayList<Vehicle> dest){
        File f = new File(path);

        if(f.exists() && f.isFile()){
            FileReader fr;
            try {
                fr = new FileReader(f);
                BufferedReader bf = new BufferedReader(fr);
                String line;
                String[] params = new String[5];

                boolean b;
                int battery;
                Bicycle bic;
                Scooter scoot;
                Ebike eb;
                while((line = bf.readLine()) != null){
                    params = line.split(",");
                    if(params[0].compareTo("b") == 0){
                        if(params.length != 4){
                            System.out.println("Line formatting error!");
                            continue;
                        }
                        b = Boolean.valueOf(params[3]);
                        bic = new Bicycle(params[1], params[2], b);
                        dest.add(bic);
                    }
                    else if(params[0].compareTo("t") == 0){
                        if(params.length != 5){
                            System.out.println("Line formatting error!");
                            continue;
                        }
                        battery = Integer.parseInt(params[3]);
                        b = Boolean.valueOf(params[4]);
                        scoot = new Scooter(params[1], params[2], battery, b);
                        dest.add(scoot);
                    }
                    else if(params[0].compareTo("e") == 0){
                        if(params.length != 5){
                            System.out.println("Line formatting error!");
                            continue;
                        }
                        battery = Integer.parseInt(params[3]);
                        b = Boolean.valueOf(params[4]);
                        eb = new Ebike(params[1], params[2], battery, b);
                        dest.add(eb);
                    }
                }
                bf.close();
            } catch (FileNotFoundException e) {
                System.out.println("File opening error");
            } catch (IOException e) {
                System.out.println("file reading error");
            }
        }
    }

    private static void readRentals(String path, ArrayList<Rental> dest){
        File f = new File(path);
        
        if(f.exists() && f.isFile() && f.length()>0){
            try {
            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Object obj = ois.readObject();
            ArrayList<?> raw = (ArrayList<?>) obj;
            for(int i = 0; i < raw.size(); i++){
                dest.add((Rental)raw.get(i));
            }
            ois.close();
            } catch (FileNotFoundException e) {
                System.out.println("File opening error");
            }  catch (IOException e) {
                System.out.println("File reading error");
            } catch (ClassNotFoundException e){
                System.out.println("Object conversion error");
            }
        }
    }

    private static void writeRentals(String path, ArrayList<Rental> src){
        File f = new File(path);
        try{
            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(src);
            oos.close();
        } catch(FileNotFoundException ex) {
            System.out.println("File creation error");
        } catch(IOException ex){
            System.out.println("File writing error");
        }
    }
}
