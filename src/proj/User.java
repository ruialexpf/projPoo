package proj;

import java.io.Serializable;

/**
 * Represents an user of the mobility service.
 * This class serves as the base for all user types (e.g., {@link Student}, {@link Worker}) and holds
 * common properties like a mechanical number and payment method. It defines an abstract contract
 * for price calculation and the application of discounts, which must be implemented by subclasses.
 * This class is {@link Serializable} to support object persistence.
 */
public class User implements Serializable {

    private String mechNo;
    private String payMethod;
    protected static float[][] priceTable = {
        {1.0f, 2.0f, 1.0f, 1.1f, 1.25f, 1.5f},
        {2.0f, 3.0f, 1.5f, 1.6f, 1.75f, 2.0f}
    };

    /**
     * Constructs a new {@code User} with a mechanical number and payment method.
     *
     * @param mechNo The user's unique mechanical number (e.g., student or employee ID).
     * @param paymentMethod The user's chosen payment method (e.g., "Credit Card", "PayPal").
     */
    public User(String mechNo, String paymentMethod){
        this.mechNo = mechNo;
        this.payMethod = paymentMethod;
    }

    /**
     * Gets the user's mechanical number.
     *
     * @return The user's unique mechanical number as a string.
     */
    public String getMechNo() {
        return mechNo;
    }

    /**
     * Gets the user's payment method.
     *
     * @return The payment method.
     */
    public String getPayMethod() {
        return payMethod;
    }

    /**
     * A generic method to retrieve pricing values. Subclasses should override this
     * to return the correct pricing tier from the {@code values} table.
     *
     * @return A float array representing a row from the pricing table.
     */
    public float[] getValue(){
        return null;
    }

    /**
     * Sets the user's mechanical number.
     * @param mechNo The new mechanical number.
     */
    public void setMechNo(String mechNo) {
        this.mechNo = mechNo;
    }

    /**
     * Sets the user's payment method.
     * @param payMethod The new payment method.
     */
    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }

    /**
     * Gets the hourly rental rate for a Bicycle based on this user's pricing table.
     * @param bicycle The bicycle being rented.
     * @return The cost per hour in Euros.
     */
    public double getPrice(Bicycle bicycle) {
        return 0.0;
    }

    /**
     * Gets the hourly rental rate for a Scooter based on this user's pricing table.
     * @param scooter The scooter being rented.
     * @return The cost per hour in Euros.
     */
    public double getPrice(Scooter scooter) {
        return 0.0;
    }

    /**
     * Gets the hourly rental rate for an E-bike based on this user's pricing table.
     * @param ebike The e-bike being rented.
     * @return The cost per hour in Euros.
     */
    public double getPrice(Ebike ebike) {
        return 0.0;
    }

    /**
     * Applies a final discount to the total cost.
     * @param totalCost The cost before the discount.
     * @return The final cost after applying the discount.
     */
    public double applyDiscount(double totalCost) {
        return totalCost; 
    }

    /**
     * Returns a string representation of the User.
     * @return A string containing the user's mechanical number and payment method.
     */
    @Override
    public String toString() {
        return "no: " + mechNo + ", pagamento: " + payMethod;
    }
}
