package lab2.HunterDogShips;

import java.util.concurrent.Callable;
import java.util.concurrent.ThreadLocalRandom;

public class Ship implements Callable<Object> {

    private static final Integer X_LENGTH = 6;
    private static final Integer Y_LENGTH = 6;

    private Integer id;
    private Integer xCoord;
    private Integer yCoord;



    public Ship(Integer id) {
        this.id = id;
        this.xCoord = ThreadLocalRandom.current().nextInt(0, X_LENGTH + 1);
        this.yCoord = ThreadLocalRandom.current().nextInt(0, Y_LENGTH + 1);
    }

    private void goSomewhere() {
        int goOnXaxis = ThreadLocalRandom.current().nextInt(-1, 2);
        int goOnYaxis = ThreadLocalRandom.current().nextInt(-1, 2);
        setxCoord(getxCoord() + goOnXaxis);
        setyCoord(getyCoord() + goOnYaxis);
    }

    boolean isCurrentCoordsValid() {
        if ((this.xCoord < 0 || this.xCoord > X_LENGTH) || (this.yCoord < 0 || this.yCoord > Y_LENGTH)) {
            return false;
        }
        return true;
    }

    @Override
    public Object call() throws Exception {
        while (true) {
            goSomewhere();
            Thread.sleep(200);
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    Integer getxCoord() {
        return xCoord;
    }

    void setxCoord(Integer xCoord) {
        this.xCoord = xCoord;
    }

    Integer getyCoord() {
        return yCoord;
    }

    void setyCoord(Integer yCoord) {
        this.yCoord = yCoord;
    }

    static Integer getxLength() {
        return X_LENGTH;
    }

    static Integer getyLength() {
        return Y_LENGTH;
    }


}
