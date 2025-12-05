package proj;

/**
 * Represents a standard bicycle, a type of non-electric vehicle.
 * This class extends {@link Vehicle} and includes properties specific to bicycles,
 * such as whether it is designed for one or two riders.
 */
public class Bicycle extends Vehicle {
    private boolean forTwo;

    /**
     * Constructs a new {@code Bicycle} with a unique identifier, location, and capacity.
     *
     * @param identifier The unique identifier for the bicycle.
     * @param location The current GPS location of the bicycle.
     * @param forTwo {@code true} if the bicycle is for two people, {@code false} otherwise.
     */
    public Bicycle(String identifier, String location, boolean forTwo){
        super(identifier, location);
        this.forTwo = forTwo;
    }
    /**
     * Sets whether the bicycle is designed for two people.
     *
     * @param forTwo True for two people, false for one.
     */
    public void setForTwo(boolean forTwo) {
        this.forTwo = forTwo;
    }

    /**
     * Checks if the bicycle is designed for two people.
     *
     * @return True if the bicycle is for two people.
     */
    public boolean isForTwo() {
        return forTwo;
    }

    /**
     * Calculates the hourly rental rate for this bicycle based on the user's profile.
     * This method implements the second part of a double dispatch pattern, where the {@link User}
     * object determines the final price based on its type (e.g., Student, Worker).
     *
     * @param user The {@link User} who is renting the bicycle.
     * @return The hourly rental rate for the bicycle, specific to the given user.
     */
    @Override
    public double getHourlyRate(User user) {
        return user.getPrice(this);
    }

    /**
     * Returns a string representation of the Bicycle.
     * @return A string detailing the bicycle's capacity and basic info.
     */
    @Override
    public String toString() {
        String noPessoas;
        if(forTwo) noPessoas = "2 pessoas";
        else noPessoas = "1 pessoa";

        return "Bicicleta para " + noPessoas + " [" + super.toString() + "]";
    }
}
