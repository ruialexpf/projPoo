package proj;

import java.io.Serializable;

/**
 * Represents a single rental event, associating a {@link User} with a {@link Vehicle}.
 * This class captures all details of a rental, including the user, vehicle, rental period,
 * and any optional extras like a helmet or light. It is serializable to allow for persistent storage.
 */
public class Rental implements Serializable {
    private User user;
    private Vehicle vehicle;
    private String startDate;
    private int startHour;
    private String endDate;
    private int endHour;
    private boolean helmet;
    private boolean light;

    /**
     * Constructs a new {@code Rental} object with all necessary details.
     *
     * @param user The {@link User} who made the rental.
     * @param vehicle The {@link Vehicle} that was rented.
     * @param startDate The start date of the rental in "dd/mm/yyyy" format.
     * @param startHour The hour the rental started (0-23).
     * @param endDate The end date of the rental in "dd/mm/yyyy" format.
     * @param endHour The hour the rental ended (0-23).
     * @param helmet {@code true} if a helmet was included, {@code false} otherwise.
     * @param light {@code true} if a light was included, {@code false} otherwise.
     */
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

    /**
     * Converts a date string from "dd/mm/yyyy" format to an integer array.
     *
     * @param date The date string to convert.
     * @return An integer array of the form {@code [day, month, year]}.
     */
    private int[] dateToInt(String date){
        int[] converted = new int[3];
        String[] splitDate = date.split("/");

        for(int i = 0; i < splitDate.length; i++){
            converted[i] = Integer.parseInt(splitDate[i]);
        }
        return converted;
    }

    /**
     * Calculates the total duration of the rental in hours.
     * This is a simplified calculation that assumes 30 days per month and 365 days per year.
     *
     * @return The duration in hours.
     */
    public int rentalDuration(){
        int[] start = dateToInt(startDate);
        int[] end = dateToInt(endDate);

        int[] diff = new int[3];
        for(int i = 0; i < diff.length; i++){
            diff[i] = end[i] - start[i];
        }

        return (endHour - startHour) + diff[0] * 24 + diff[1] * 24 * 30 + diff[2] * 24 * 365;
    }

    /**
     * Gets the user associated with the rental.
     * @return The User object.
     */
    public User getUser() {
        return user;
    }

    /**
     * Gets the vehicle associated with this rental.
     *
     * @return The Vehicle object.
     */
    public Vehicle getVehicle() {
        return vehicle;
    }

    /**
     * Gets the start date of the rental.
     *
     * @return The start date string.
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * Gets the start hour of the rental.
     *
     * @return The start hour.
     */
    public int getStartHour() {
        return startHour;
    }

    /**
     * Gets the end date of the rental.
     *
     * @return The end date string.
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * Gets the end hour of the rental.
     *
     * @return The end hour.
     */
    public int getEndHour() {
        return endHour;
    }

    /**
     * Checks if a helmet was included in the rental.
     *
     * @return True if a helmet was included.
     */
    public boolean isHelmet() {
        return helmet;
    }

    /**
     * Checks if a light was included in the rental.
     *
     * @return True if a light was included.
     */
    public boolean isLight() {
        return light;
    }

    /**
     * Sets the user for this rental.
     *
     * @param user The new user.
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Sets the vehicle for this rental.
     *
     * @param vehicle The new vehicle.
     */
    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    /**
     * Sets the start date of the rental.
     *
     * @param startDate The new start date.
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    /**
     * Sets the start hour of the rental.
     *
     * @param startHour The new start hour (0-23).
     */
    public void setStartHour(int startHour) {
        this.startHour = startHour;
    }

    /**
     * Sets the end date of the rental.
     *
     * @param endDate The new end date in "dd/mm/yyyy" format.
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    /**
     * Sets the end hour of the rental.
     *
     * @param endHour The new end hour (0-23).
     */
    public void setEndHour(int endHour) {
        this.endHour = endHour;
    }

    /**
     * Sets whether a helmet was included in the rental.
     *
     * @param helmet True if a helmet was included.
     */
    public void setHelmet(boolean helmet) {
        this.helmet = helmet;
    }

    /**
     * Sets whether a light was included in the rental.
     *
     * @param light True if a light was included.
     */
    public void setLight(boolean light) {
        this.light = light;
    }

    /**
     * Returns a string representation of the Rental.
     * @return A string summarizing the rental details.
     */
    @Override
    public String toString() {
        ServiceManager sm = new ServiceManager();
        String helmMsg = "Capacete: ";
        String lightMsg = "Luz: ";
        
        int durationInHours = rentalDuration();

        if(helmet) helmMsg += "sim";
        else helmMsg += "nao";

        if(light) lightMsg += "sim";
        else lightMsg += "nao";
        return "Utilizador " + user.getMechNo() + " aluga veiculo " + vehicle.getIdentifier() + " ("
        + helmMsg + ", " + lightMsg + " | "  + startDate + ", " + startHour + ":00 - " + endDate + ", " + endHour + ":00"
        + " (duracao: " + durationInHours + "h))" + "preco: " + sm.calculateRentalPrice(user, vehicle, durationInHours, helmet, light) + " euros.";
    }
}
