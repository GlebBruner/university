package lab2.birdsV2;

import java.util.concurrent.Callable;
import java.util.concurrent.ThreadLocalRandom;

public class LittleBird implements Callable<Object> {

    private static Plate plateWithFood;
    private int id;
    private int sumFood;

    LittleBird(int id, Plate plate) {
        this.id = id;
        this.sumFood = 0;

        if (LittleBird.plateWithFood == null) {
            LittleBird.plateWithFood = plate;
        }
    }

    private void eatFood() {
        if (plateWithFood.isBusy() || plateWithFood.getOwner() != null) {
            System.out.println("Bird" + getId() + ", tried to eat food but plate is busy now by another bird = " + plateWithFood.getOwner().getId());
        } else {

            if (plateWithFood.getFood() <= 0) {
                System.out.println("Bird " + getId() + ", tried to eat, but there is no food anymore in plate.");
            } else {
                plateWithFood.setBusy(true);
                plateWithFood.setOwner(this);


                int foodToEat = ThreadLocalRandom.current().nextInt(5, 16);
                plateWithFood.setFood(plateWithFood.getFood() - foodToEat);
                this.sumFood += foodToEat;
                System.out.println("Bird " + getId() + " eat " + foodToEat);



                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                plateWithFood.setBusy(false);
                plateWithFood.setOwner(null);
            }
        }
    }


    @Override
    public Object call() throws Exception {
        while (true) {
            eatFood();
            Thread.sleep(ThreadLocalRandom.current().nextInt(500));
        }

    }

    public static Plate getPlateWithFood() {
        return plateWithFood;
    }

    public static void setPlateWithFood(Plate plateWithFood) {
        LittleBird.plateWithFood = plateWithFood;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSumFood() {
        return sumFood;
    }

    public void setSumFood(int sumFood) {
        this.sumFood = sumFood;
    }
}
