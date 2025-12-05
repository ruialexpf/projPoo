package proj;

/**
 * Represents a Lecturer, a specific type of {@link Worker} user.
 * This class holds information unique to lecturers, such as the faculties they are associated with.
 * Lecturers are subject to the standard worker pricing model without any special discounts.
 */
public class Lecturer extends Worker {
    private String faculties;

    /**
     * Constructs a new {@code Lecturer} with their professional details.
     *
     * @param mechNo The lecturer's unique mechanical number.
     * @param paymentMethod The lecturer's preferred payment method.
     * @param contractYear The year the lecturer's contract began.
     * @param faculties A string listing the faculties where the lecturer teaches, typically delimited.
     */
    public Lecturer(String mechNo, String paymentMethod, int contractYear, String faculties){
        super(mechNo, paymentMethod, contractYear);
        this.faculties = faculties;
    }

    /**
     * Sets the faculties where the lecturer teaches.
     *
     * @param faculties A string of faculty names, separated by a delimiter (e.g., "/").
     */
    public void setFaculties(String faculties) {
        this.faculties = faculties;
    }
    /**
     * Gets the faculties where the lecturer teaches.
     *
     * @return A string of faculties.
     */
    public String getFaculties() {
        return faculties;
    }

    /**
     * Gets the specific pricing values for a Worker (Lecturer).
     * @return The second row of the pricing table.
     */
    @Override
    public float[] getValue(){
        return User.priceTable[1];
    }

    /**
     * Returns a string representation of the Lecturer.
     * @return A string detailing the lecturer's information.
     */
    @Override
    public String toString() {
        String[] facs = faculties.split("/");
        String s = "<DOCENTE> " + super.toString() + ", faculdades: ";
        for(String f: facs) s += f + " ";
        return s;
    }
}
