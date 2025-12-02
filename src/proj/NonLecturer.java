package proj;

public class NonLecturer extends Worker {
    private String service;

    public NonLecturer(String mechNo, String paymentMethod, int contractYear, String service){
        super(mechNo, paymentMethod, contractYear);
        this.service = service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getService() {
        return service;
    }
    
    protected String workerTitle(){
        return "<NAO DOCENTE>";
    }

    @Override
    public String toString() {
        return super.toString() + ", servico: " + service;
    }
}
