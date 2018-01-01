package lab2.birdsV2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

public class BigBird implements Callable<Object> {

    private Plate plateWithFood;

    public BigBird(Plate plateWithFood) {
        this.plateWithFood = plateWithFood;
    }

    private void watchPlate() {
        if (plateWithFood.getFood() <= 0) {
            plateWithFood.setBusy(true);
            int foodtoFill = ThreadLocalRandom.current().nextInt(300, 601);
            plateWithFood.setFood(foodtoFill);
            System.out.println("Mother is filling food on " + foodtoFill);
            plateWithFood.setBusy(false);
        } else {
            System.out.println("There is " + plateWithFood.getFood() + " in plate...I'm waiting until it is empty");
        }
    }


    @Override
    public Object call() throws Exception {
        while (true) {
            watchPlate();
            Thread.sleep(200);
        }
    }



    public Plate getPlateWithFood() {
        return plateWithFood;
    }

    public void setPlateWithFood(Plate plateWithFood) {
        this.plateWithFood = plateWithFood;
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(6);
        Plate plate = new Plate();
        BigBird bigBird = new BigBird(plate);
        LittleBird littleBird1 = new LittleBird(1, plate);
        LittleBird littleBird2 = new LittleBird(2, plate);
        LittleBird littleBird3 = new LittleBird(3, plate);
        LittleBird littleBird4 = new LittleBird(4, plate);
        LittleBird littleBird5 = new LittleBird(5, plate);
        List<Callable<Object>> allBirds = new ArrayList<>();
        allBirds.add(littleBird1);
        allBirds.add(littleBird2);
        allBirds.add(littleBird3);
        allBirds.add(littleBird4);
        allBirds.add(littleBird5);
        allBirds.add(bigBird);
        try {
            executorService.invokeAll(allBirds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
