package proj;

public class Worker extends User {
    private int contractYear;

    public Worker(String mechNo, String paymentMethod, int contractYear){
        super(mechNo, paymentMethod);
        this.contractYear = contractYear;
    }

    public int getContractYear() {
        return contractYear;
    }

    public void setContractYear(int contractYear) {
        this.contractYear = contractYear;
    }

    protected String workerTitle(){
        return "<FUNCIONARIO>";
    }

    @Override
    public String toString() {
        return workerTitle() + " " + super.toString() + ", ano de contrato: " + contractYear; 
    }
}
