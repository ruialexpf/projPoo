package proj;

/**
 * Represents a Non-Lecturer, a specific type of {@link Worker} user.
 * This class is for employees who are not academic staff. They are associated with a specific service
 * department and are eligible for a 50% discount on all rentals.
 */
public class NonLecturer extends Worker {
    private String service;

    /**
     * Constructs a new {@code NonLecturer} with their professional details.
     *
     * @param mechNo The worker's unique mechanical number.
     * @param paymentMethod The worker's preferred payment method.
     * @param contractYear The year the worker's contract began.
     * @param service The service department the worker belongs to (e.g., "IT", "HR").
     */
    public NonLecturer(String mechNo, String paymentMethod, int contractYear, String service){
        super(mechNo, paymentMethod, contractYear);
        this.service = service;
    }

    /**
     * Sets the service department for the non-lecturing worker.
     *
     * @param service The new service department name.
     */
    public void setService(String service) {
        this.service = service;
    }
    /**
     * Gets the service department of the non-lecturing worker.
     *
     * @return The service department name.
     */
    public String getService() {
        return service;
    }

    /**
     * Retrieves the pricing values applicable to all {@link Worker} users.
     * This method returns the specific row from the pricing table that corresponds to workers.
     *
     * @return The second row of the pricing table.
     */
    @Override
    public float[] getValue(){
        return User.priceTable[1];
    }

    /**
     * Returns a string representation of the NonLecturer.
     * @return A string detailing the worker's information.
     */
    @Override
    public String toString() {
        return "<NAO DOCENTE> " + super.toString() + ", servico: " + service;
    }

    /**
     * Applies a 50% discount to the final rental cost for non-lecturing staff.
     * @param totalCost The cost before the discount.
     * @return The final cost after applying the 50% discount.
     */
    @Override
    public double applyDiscount(double totalCost) {
        return totalCost * 0.50;
    }
}
