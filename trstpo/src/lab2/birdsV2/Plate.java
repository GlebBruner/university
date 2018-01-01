package lab2.birdsV2;

public class Plate {

    private int food;
    private boolean isBusy;

    private LittleBird owner;

    public Plate() {
        this.food = 500;
        this.isBusy = false;
    }

    int getFood() {
        return food;
    }

    void setFood(int food) {
        this.food = food;
    }

    boolean isBusy() {
        return isBusy;
    }

    void setBusy(boolean busy) {
        isBusy = busy;
    }

    LittleBird getOwner() {
        return owner;
    }

    void setOwner(LittleBird owner) {
        this.owner = owner;
    }
}
