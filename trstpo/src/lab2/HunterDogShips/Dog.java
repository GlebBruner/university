package lab2.HunterDogShips;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

public class Dog implements Callable<Object> {

    private Hunter owner;
    private List<Ship> shipsDogWatches;

    private void catchShip(Ship ship, Ship freeShipForHunter) {

        try {
            Thread.sleep(200); //was 500
            if (!freeShipForHunter.isCurrentCoordsValid()) {
                System.out.println(" dog is trying to wake up hunter. Hunter needs to catch SHIP #" + freeShipForHunter.getId()
                        + " coords: x = " + freeShipForHunter.getxCoord() + " y = "
                        + freeShipForHunter.getyCoord());
                getOwner().setWhatShipToCatch(freeShipForHunter);
                getOwner().setSleeping(false);
            }
            ship.setxCoord(ThreadLocalRandom.current().nextInt(Ship.getxLength() / 2 - 2, Ship.getxLength() / 2 + 2));
            ship.setyCoord(ThreadLocalRandom.current().nextInt(Ship.getyLength() / 2 - 2, Ship.getyLength() / 2 + 2));
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void shipsMonitoring() {
        for (int i = 0; i < shipsDogWatches.size(); i++) {
            System.out.println("Dog: monitoring - " + getShipsDogWatches().get(i).getId() + ", coords: x = " + getShipsDogWatches().get(i).getxCoord()
                    + ", y = " + getShipsDogWatches().get(i).getyCoord());
            if (!getShipsDogWatches().get(i).isCurrentCoordsValid()) {
                System.out.println("Dog is chasing - " + getShipsDogWatches().get(i).getId());
                catchShip(getShipsDogWatches().get(i), getShipsDogWatches().get( shipsDogWatches.size() - 1 - i));
            }
        }
    }

    @Override
    public Object call() throws Exception {
        while (true) {
            Thread.sleep(200);
            System.out.println("-------------------------------------------");
            shipsMonitoring();
        }
    }

    private Hunter getOwner() {
        return owner;
    }

    public void setOwner(Hunter owner) {
        this.owner = owner;
    }

    List<Ship> getShipsDogWatches() {
        return shipsDogWatches;
    }

    public void setShipsDogWatches(List<Ship> shipsDogWatches) {
        this.shipsDogWatches = shipsDogWatches;
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(4);


        Ship ship1 = new Ship(1);
        Ship ship2 = new Ship(2);
        List<Ship> ships = new ArrayList<>();
        ships.add(ship1);
        ships.add(ship2);
        Dog dog = new Dog();
        dog.setShipsDogWatches(ships);
        Hunter hunter = new Hunter(dog);
        dog.setOwner(hunter);

        List<Callable<Object>> allThreads = new ArrayList<>();
        allThreads.add(ship1);
        allThreads.add(ship2);
        allThreads.add(dog);
        allThreads.add(hunter);

        try {
            executorService.invokeAll(allThreads);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
