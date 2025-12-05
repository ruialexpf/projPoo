package proj;

/**
 * Represents an Worker user in the mobility service, extending the {@link User} class.
 * This class serves as a base for different types of employees, such as {@link Lecturer} and
 * {@link NonLecturer}, and adds properties common to all workers, like the contract start year.
 * It also implements the pricing logic for all worker types.
 */
public class Worker extends User {
    private int contractYear;

    /**
     * Constructs a new {@code Worker} with their professional details.
     *
     * @param mechNo The worker's unique mechanical number.
     * @param paymentMethod The worker's preferred payment method.
     * @param contractYear The year the worker's contract began.
     */
    public Worker(String mechNo, String paymentMethod, int contractYear){
        super(mechNo, paymentMethod);
        this.contractYear = contractYear;
    }

    /**
     * Gets the year the worker's contract started.
     *
     * @return The contract start year.
     */
    public int getContractYear() {
        return contractYear;
    }
    /**
     * Sets the worker's contract start year.
     *
     * @param contractYear The new contract year.
     */
    public void setContractYear(int contractYear) {
        this.contractYear = contractYear;
    }

    /**
     * Retrieves the pricing values applicable to all {@code Worker} users.
     * This method returns the second row of the pricing table, which contains worker-specific rates.
     *
     * @return The second row of the pricing table.
     */
    @Override
    public float[] getValue(){
        return User.priceTable[1];
    }

    /**
     * Returns a string representation of the Worker.
     * @return A string containing the worker's information.
     */
    @Override
    public String toString() {
        return super.toString() + ", ano de contrato: " + contractYear;
    }

    /**
     * Returns the hourly rate for a bicycle based on the worker price table.
     * @param bicycle The bicycle to price.
     * @return €2.00/hr for one seat, €3.00/hr for two seats.
     */
    @Override
    public double getPrice(Bicycle bicycle) {
        return bicycle.isForTwo() ? 3.00 : 2.00;
    }

    /**
     * Returns the hourly rate for a scooter based on the worker price table.
     * @param scooter The scooter to price.
     * @return €2.50/hr for no LCD, €2.60/hr with LCD.
     */
    @Override
    public double getPrice(Scooter scooter) {
        return scooter.isLcdScreen() ? 2.60 : 2.50;
    }

    /**
     * Returns the hourly rate for an e-bike based on the worker price table.
     * @param ebike The e-bike to price.
     * @return €2.75/hr for fixed battery, €3.00/hr for removable.
     */
    @Override
    public double getPrice(Ebike ebike) {
        return ebike.isRemovableBat() ? 3.00 : 2.75;
    }
}
