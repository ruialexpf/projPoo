package proj;

public class Student extends User {
    private String course;
    private String pole;

    public Student(String mechNo, String paymentMethod, String course, String pole){
        super(mechNo, paymentMethod);
        this.course = course;
        this.pole = pole;
    }

    public String getCourse() {
        return course;
    }

    public String getPole() {
        return pole;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public void setPole(String pole) {
        this.pole = pole;
    }

    @Override
    public String toString() {
        return "<ESTUDANTE> " + super.toString() + ", curso: " + course + ", polo: " + pole;
    }
}
