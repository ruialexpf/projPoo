package proj;

import java.util.ArrayList;

public class Lecturer extends Worker {
    private ArrayList<String> faculties;

    public Lecturer(String mechNo, String paymentMethod, int contractYear, String[] faculties){
        super(mechNo, paymentMethod, contractYear);
        this.faculties = new ArrayList<String>();
        for(int i = 0; i < faculties.length; i++){
            this.faculties.add(faculties[i]);
        }
    }

    public void setFaculties(String[] faculties) {
        for(int i = 0; i < faculties.length; i++){
            this.faculties.add(faculties[i]);
        }
    }

    public ArrayList<String> getFaculties() {
        return faculties;
    }

    protected String workerTitle(){
        return "<DOCENTE>";
    }

    @Override
    public String toString() {
        return super.toString() + ", faculdades: " + faculties;
    }
}
