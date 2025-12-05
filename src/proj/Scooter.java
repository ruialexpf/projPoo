package proj;

/**
 * Represents an electric scooter, a type of {@link Electric} vehicle.
 * This class includes properties specific to scooters, such as whether it has an LCD screen.
 */
public class Scooter extends Electric {
    private boolean lcdScreen;

    /**
     * Constructs a new {@code Scooter} with specified properties.
     *
     * @param identifier The unique identifier for the scooter.
     * @param location The current GPS location of the scooter.
     * @param batteryLvl The initial battery level (percentage).
     * @param lcdScreen {@code true} if the scooter has an LCD screen, {@code false} otherwise.
     */
    public Scooter(String identifier, String location, int batteryLvl, boolean lcdScreen){
        super(identifier, location, batteryLvl);
        this.lcdScreen = lcdScreen;
    }
    /**
     * Sets whether the scooter has an LCD screen.
     *
     * @param lcdScreen True for an LCD screen, false otherwise.
     */
    public void setLcdScreen(boolean lcdScreen) {
        this.lcdScreen = lcdScreen;
    }

    /**
     * Checks if the scooter is equipped with an LCD screen.
     *
     * @return True if the scooter has an LCD screen.
     */
    public boolean isLcdScreen() {
        return lcdScreen;
    }

    /**
     * Calculates the hourly rental rate for this scooter based on the user's profile.
     * This method is part of a double dispatch pattern, allowing the {@link User} object
     * to determine the price based on its specific type.
     *
     * @param user The {@link User} renting the scooter.
     * @return The user-specific hourly rental rate for the scooter.
     */
    @Override
    public double getHourlyRate(User user) {
        return user.getPrice(this);
    }

    /**
     * Returns a string representation of the Scooter.
     * @return A string detailing the scooter's features and status.
     */
    @Override
    public String toString() {
        String msg;
        if(lcdScreen) msg = "com LCD";
        else msg = "sem LCD";

        return "Trotinete " + msg + " [" + super.toString() + "] " + getBatteryLvl() + "%";
    }
}