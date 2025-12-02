package proj;

import java.io.Serializable;

public class Rental implements Serializable {
    private User user;
    private Vehicle vehicle;
    private String startDate;
    private int startHour;
    private String endDate;
    private int endHour;
    private boolean helmet;
    private boolean light;

    public Rental(User user, Vehicle vehicle, String startDate, int startHour, String endDate, int endHour, boolean helmet, boolean light){
        this.user = user;
        this.vehicle = vehicle;
        this.startDate = startDate;
        this.startHour = startHour;
        this.endDate = endDate;
        this.endHour = endHour;
        this.helmet = helmet;
        this.light = light;
    }

    private int[] dateToInt(String date){
        int[] converted = new int[3];
        if(date.split("/").length != 3){
            System.out.println("Date formatting error");
            int[] errReturn = {-1,-1,-1};
            return errReturn;
        }
        String[] splitDate = date.split("/");

        for(int i = 0; i < splitDate.length; i++){
            converted[i] = Integer.parseInt(splitDate[i]);
        }
        return converted;
    }

    private int rentalDuration(){
        int duration = 0;
        
        int[] start = dateToInt(startDate);
        int[] end = dateToInt(endDate);

        int[] diff = new int[3];
        for(int i = 0; i < diff.length; i++){
            diff[i] = end[i] - start[i];
        }

        duration = (endHour - startHour) + diff[0] * 24 + diff[1] * 24 * 30 + diff[2] * 24 * 365;
        return duration;
    }
    public User getUser() {
        return user;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }
    
    public String getStartDate() {
        return startDate;
    }

    public int getStartHour() {
        return startHour;
    }

    public String getEndDate() {
        return endDate;
    }

    public int getEndHour() {
        return endHour;
    }

    public boolean isHelmet() {
        return helmet;
    }

    public boolean isLight() {
        return light;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setStartHour(int startHour) {
        this.startHour = startHour;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void setEndHour(int endHour) {
        this.endHour = endHour;
    }

    public void setHelmet(boolean helmet) {
        this.helmet = helmet;
    }

    public void setLight(boolean light) {
        this.light = light;
    }

    @Override
    public String toString() {
        String helmMsg = "Capacete: ";
        String lightMsg = "Luz: ";

        if(helmet) helmMsg += "sim";
        else helmMsg += "nao";

        if(light) lightMsg += "sim";
        else lightMsg += "nao";
        return "Utilizador " + user.getMechNo() + " aluga veiculo " + vehicle.getIdentifier() + " ("
        + helmMsg + ", " + lightMsg + " | "  + startDate + ", " + startHour + ":00 - " + endDate + ", " + endHour + ":00"
        + " (duracao: " + rentalDuration() + "h))";
    }
}
