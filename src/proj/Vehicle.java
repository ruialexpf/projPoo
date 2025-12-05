package proj;

import java.io.Serializable;

/**
 * Represents an vehicle in the mobility service.
 * This class serves as the base for all vehicle types (e.g., {@link Bicycle}, {@link Electric})
 * and holds common properties such as a unique identifier and its current location.
 * It defines an abstract contract for calculating rental costs, which is implemented
 * through a double dispatch mechanism involving the {@link User} class.
 * This class is {@link Serializable} to support object persistence.
 */
public class Vehicle implements Serializable {
    private String identifier;
    private String location;

    /**
     * Constructs a new {@code Vehicle} with a unique identifier and location.
     *
     * @param identifier The unique identifier for the vehicle (e.g., a serial number or registration).
     * @param location The current GPS location of the vehicle as a string.
     */
    public Vehicle(String identifier, String location){
        this.identifier = identifier;
        this.location = location;
    }
    /**
     * Sets the vehicle's unique identifier.
     *
     * @param identifier The new identifier.
     */
    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    /**
     * Sets the vehicle's current GPS location.
     *
     * @param location The new location.
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Gets the vehicle's unique identifier.
     *
     * @return The identifier.
     */
    public String getIdentifier() {
        return identifier;
    }

    /**
     * Gets the vehicle's location.
     *
     * @return The location.
     */
    public String getLocation() {
        return location;
    }

    /**
     * Calculates the base cost of a rental for a specific duration.
     *
     * @param durationInMinutes The duration of the rental in minutes.
     * @return The calculated cost.
     */
    public double calculateCost(int durationInMinutes){
        return 0.0;
    }

    /**
     * Gets the specific hourly rental rate for this vehicle by initiating a double dispatch.
     * It calls the corresponding {@code getPrice} method on the {@link User} object,
     * passing itself as an argument. This allows the {@code User} subclass to determine the
     * correct price based on its own pricing rules and the vehicle's type.
     *
     * @param user The {@link User} who is renting the vehicle.
     * @return The hourly rate in Euros.
     */
    public double getHourlyRate(User user) {
        return 0.0;
    }

    /**
     * Returns a string representation of the Vehicle.
     * @return A string containing the vehicle's ID and location.
     */
    @Override
    public String toString() {
        return "id: " + identifier + ", localizacao: " + location;
    }
}
