package proj;

/**
 * Represents an electric bike (E-bike), a type of electric vehicle.
 * This class extends {@link Electric} and adds properties specific to E-bikes,
 * such as whether the battery is removable.
 */
public class Ebike extends Electric {
    private boolean removableBat;

    /**
     * Constructs a new {@code Ebike} with specified properties.
     *
     * @param identifier The unique identifier for the E-bike.
     * @param location The current GPS location of the E-bike.
     * @param batteryLvl The initial battery level (percentage).
     * @param removableBat {@code true} if the E-bike has a removable battery, {@code false} otherwise.
     */
    public Ebike(String identifier, String location, int batteryLvl, boolean removableBat){
        super(identifier, location, batteryLvl);
        this.removableBat = removableBat;
    }
    /**
     * Sets whether the E-bike's battery is removable.
     *
     * @param removableBat True for a removable battery, false for a fixed one.
     */
    public void setRemovableBat(boolean removableBat) {
        this.removableBat = removableBat;
    }

    /**
     * Checks if the E-bike's battery is removable.
     *
     * @return True if the battery is removable.
     */
    public boolean isRemovableBat() {
        return removableBat;
    }

    /**
     * Calculates the hourly rental rate for this E-bike based on the user's profile.
     * This method is part of a double dispatch pattern, allowing the {@link User} object
     * to determine the price based on its specific type.
     *
     * @param user The {@link User} renting the E-bike.
     * @return The user-specific hourly rental rate for the E-bike.
     */
    @Override
    public double getHourlyRate(User user) {
        return user.getPrice(this);
    }

    /**
     * Returns a string representation of the E-bike.
     * @return A string detailing the e-bike's features and status.
     */
    @Override
    public String toString() {
        String msg;
        if(removableBat) msg = "com bateria removivel";
        else msg = "com bateria fixa";

        return "E-bike " + msg + " [" + super.toString() + "] " + getBatteryLvl() + "%";
    }
}
