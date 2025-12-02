package proj;

public class Electric extends Vehicle {
    private int batteryLvl;

    public Electric(String identifier, String location, int batteryLvl){
        super(identifier, location);
        this.batteryLvl = batteryLvl;
    }

    public void setBatteryLvl(int batteryLvl) {
        this.batteryLvl = batteryLvl;
    }

    public int getBatteryLvl() {
        return batteryLvl;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
