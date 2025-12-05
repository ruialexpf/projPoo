package proj;

/**
 * Represents a Student user in the mobility service.
 * This class extends {@link User} and includes details specific to students,
 * such as their course and university pole. Students have their own pricing model for vehicle rentals.
 */
public class Student extends User {
    private String course;
    private String pole;

    /**
     * Constructs a new {@code Student} user with academic details.
     *
     * @param mechNo The student's unique mechanical number.
     * @param paymentMethod The student's preferred payment method.
     * @param course The student's course of study.
     * @param pole The university pole the student is primarily associated with (e.g., "Polo I").
     */
    public Student(String mechNo, String paymentMethod, String course, String pole){
        super(mechNo, paymentMethod);
        this.course = course;
        this.pole = pole;
    }

    /**
     * Gets the student's course of study.
     *
     * @return The course name.
     */
    public String getCourse() {
        return course;
    }
    /**
     * Gets the university pole the student is associated with.
     *
     * @return The pole name (e.g., "Polo I").
     */
    public String getPole() {
        return pole;
    }

    /**
     * Sets the student's course of study.
     *
     * @param course The new course name.
     */
    public void setCourse(String course) {
        this.course = course;
    }

    /**
     * Sets the university pole for the student.
     *
     * @param pole The new pole name (e.g., "Polo II").
     */
    public void setPole(String pole) {
        this.pole = pole;
    }
    /**
     * Retrieves the pricing values specific to a {@code Student}.
     * This method returns the first row of the pricing table, which contains student-specific rates.
     *
     * @return The first row of the pricing table.
     */
    @Override
    public float[] getValue(){
        return User.priceTable[0];
    }

    /**
     * Returns a string representation of the Student.
     * @return A string detailing the student's information.
     */
    @Override
    public String toString() {
        return "<ESTUDANTE> " + super.toString() + ", curso: " + course + ", polo: " + pole;
    }

    /**
     * Gets the hourly rental rate for a {@link Bicycle} based on the student price table.
     * The price varies depending on whether the bicycle is for one or two people.
     *
     * @param bicycle The {@link Bicycle} to be priced.
     * @return €1.00/hr for one seat, €2.00/hr for two seats.
     */
    @Override
    public double getPrice(Bicycle bicycle) {
        return bicycle.isForTwo() ? 2.00 : 1.00;
    }

    /**
     * Gets the hourly rental rate for a {@link Scooter} based on the student price table.
     * The price depends on whether the scooter is equipped with an LCD screen.
     *
     * @param scooter The scooter to price.
     * @return €1.00/hr for no LCD, €1.10/hr with LCD.
     */
    @Override
    public double getPrice(Scooter scooter) {
        return scooter.isLcdScreen() ? 1.10 : 1.00;
    }

    /**
     * Gets the hourly rental rate for an {@link Ebike} based on the student price table.
     * The price is determined by whether the e-bike has a removable battery.
     *
     * @param ebike The e-bike to price.
     * @return €1.25/hr for fixed battery, €1.50/hr for removable.
     */
    @Override
    public double getPrice(Ebike ebike) {
        return ebike.isRemovableBat() ? 1.50 : 1.25;
    }
}
