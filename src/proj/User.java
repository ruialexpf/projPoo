package proj;

import java.io.Serializable;

public class User implements Serializable{
    private String mechNo;
    private String payMethod;

    public User(String mechNo, String paymentMethod){
        this.mechNo = mechNo;
        this.payMethod = paymentMethod;
    }

    public String getMechNo() {
        return mechNo;
    }

    public String getPayMethod() {
        return payMethod;
    }

    public void setMechNo(String mechNo) {
        this.mechNo = mechNo;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }

    @Override
    public String toString() {
        return "no: " + mechNo + ", pagamento: " + payMethod;
    }
}
