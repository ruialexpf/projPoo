package proj;

public class Scooter extends Electric {
    private boolean lcdScreen;

    public Scooter(String identifier, String location, int batteryLvl, boolean lcdScreen){
        super(identifier, location, batteryLvl);
        this.lcdScreen = lcdScreen;
    }

    public void setLcdScreen(boolean lcdScreen) {
        this.lcdScreen = lcdScreen;
    }

    public boolean isLcdScreen() {
        return lcdScreen;
    }

    @Override
    public String toString() {
        String msg = new String();
        if(lcdScreen) msg = "com LCD";
        else msg = "sem LCD";

        return "Trotinete " + msg + " [" + super.toString() + "] " + getBatteryLvl() + "%";
    }
}
