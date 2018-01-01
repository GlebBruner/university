package lab2.philosofers;

import lab2.philosofers.Philosofer;

public class Fork {

    private Integer id;
    //занята ли эта вилка. true - занята
    private boolean isBusy = false;
    private Philosofer owner;

    Fork() {

    }

    public Fork(Integer id, boolean isBusy, Philosofer owner) {
        this.id = id;
        this.isBusy = isBusy;
        this.owner = owner;
    }

    public Fork(Integer id, boolean isBusy) {
        this.id = id;
        this.isBusy = isBusy;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isBusy() {
        return isBusy;
    }

    public void setBusy(boolean busy) {
        isBusy = busy;
    }

    public Philosofer getOwner() {
        return owner;
    }

    public void setOwner(Philosofer owner) {
        this.owner = owner;
    }




}
