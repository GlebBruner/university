package lab2.HunterDogShips;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ThreadLocalRandom;

public class Hunter implements Callable<Object> {

    private Dog pet;
    private List<Ship> shipsHunterWatches;
    private boolean isSleeping;

    private Ship whatShipToCatch;

    public Hunter(Dog pet) {
        this.pet = pet;
        //охотник будет следить за тем же овцами, что и собака
        setShipsHunterWatches(pet.getShipsDogWatches());
        this.isSleeping = true;

    }

    private void catchShip() {

        for (Ship ship :
                getShipsHunterWatches()) {
            if (ship == this.whatShipToCatch) {
                System.out.println("Hunter is chasing - " + ship.getId());
                ship.setxCoord(ThreadLocalRandom.current().nextInt(Ship.getxLength() / 2 - 2, Ship.getxLength() / 2 + 2));
                ship.setyCoord(ThreadLocalRandom.current().nextInt(Ship.getyLength() / 2 - 2, Ship.getyLength() / 2 + 2));
                setWhatShipToCatch(null);
                setSleeping(true);
            }
        }

    }

    @Override
    public Object call() throws Exception {
        while (true) {
            Thread.sleep(200);
            if (!isSleeping) {
                System.out.println("Hunter is AWAKEN");
                catchShip();
            }
        }
    }

    public boolean isSleeping() {
        return isSleeping;
    }

    void setSleeping(boolean sleeping) {
        isSleeping = sleeping;
    }

    public Ship getWhatShipToCatch() {
        return whatShipToCatch;
    }

    void setWhatShipToCatch(Ship whatShipToCatch) {
        this.whatShipToCatch = whatShipToCatch;
    }

    public Dog getPet() {
        return pet;
    }

    public void setPet(Dog pet) {
        this.pet = pet;
    }

    public List<Ship> getShipsHunterWatches() {
        return shipsHunterWatches;
    }

    public void setShipsHunterWatches(List<Ship> shipsHunterWatches) {
        this.shipsHunterWatches = shipsHunterWatches;
    }
}
