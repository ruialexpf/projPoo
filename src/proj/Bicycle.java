package proj;

public class Bicycle extends Vehicle {
    private boolean forTwo;

    public Bicycle(String identifier, String location, boolean forTwo){
        super(identifier, location);
        this.forTwo = forTwo;
    }

    public void setForTwo(boolean forTwo) {
        this.forTwo = forTwo;
    }

    public boolean isForTwo() {
        return forTwo;
    }

    @Override
    public String toString() {
        String noPessoas = new String();
        if(forTwo) noPessoas = "2 pessoas";
        else noPessoas = "1 pessoa";

        return "Bicicleta para " + noPessoas + " [" + super.toString() + "]";
    }
}
