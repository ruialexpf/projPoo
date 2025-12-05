package proj;

/**
 * Represents an electric vehicle, serving as a base class for specific types of electric vehicles.
 * It extends {@link Vehicle} to include properties commonelectric vehicles, such as battery level.
 * Subclasses like {@link Ebike} and {@link Scooter} will inherit from this class.
 */
public class Electric extends Vehicle {
    private int batteryLvl;

    /**
     * Constructs a new {@code Electric} vehicle with essential properties.
     *
     * @param identifier The unique identifier for the vehicle.
     * @param location The current GPS location of the vehicle.
     * @param batteryLvl The initial battery level as a percentage (0-100).
     */
    public Electric(String identifier, String location, int batteryLvl){
        super(identifier, location);
        this.batteryLvl = batteryLvl;
    }
    /**
     * Sets the battery level of the vehicle.
     *
     * @param batteryLvl The new battery level (0-100).
     */
    public void setBatteryLvl(int batteryLvl) {
        this.batteryLvl = batteryLvl;
    }

    /**
     * Gets the current battery level of the vehicle.
     *
     * @return The battery level as a percentage.
     */
    public int getBatteryLvl() {
        return batteryLvl;
    }

    /**
     * Returns a string representation of the {@code Electric} vehicle.
     * This implementation delegates to the {@link Vehicle#toString()} method to provide basic vehicle information.
     *
     * @return A string with the vehicle's ID and location.
     */
    @Override
    public String toString() {
        return super.toString();
    }
}
