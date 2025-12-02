package proj;

public class Ebike extends Electric {
    private boolean removableBat;

    public Ebike(String identifier, String location, int batteryLvl, boolean removableBat){
        super(identifier, location, batteryLvl);
        this.removableBat = removableBat;
    }

    public void setRemovableBat(boolean removableBat) {
        this.removableBat = removableBat;
    }

    public boolean isRemovableBat() {
        return removableBat;
    }

    @Override
    public String toString() {
        String msg = new String();
        if(removableBat) msg = "com bateria removivel";
        else msg = "com bateria fixa";

        return "E-bike " + msg + " [" + super.toString() + "] " + getBatteryLvl() + "%";
    }
}
