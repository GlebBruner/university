package lab2.birds;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

public class Bird implements Callable<Object> {

    private static Integer food = 500;

    private Integer id;
    private Integer summaryFoodEaten;

    public void eatFood () {

        int foodToEat = ThreadLocalRandom.current().nextInt(5, 21);
        System.out.println("Bird " + getId() + " eat " + foodToEat);
        this.summaryFoodEaten += foodToEat;
        setFood(getFood() - foodToEat);
        if (getFood() <= 0) {
            System.out.println("Bird " + getId() + " eat last food");
            setFood(500);
        }
    }

    public void sleep () {
        try {
            Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 2500));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object call() throws Exception {
        while (true) {
            eatFood();
            sleep();
        }
    }


    public Bird(Integer id) {
        this.id = id;
        this.summaryFoodEaten = 0;
    }

    public static Integer getFood() {
        return food;
    }

    public static void setFood(Integer food) {
        Bird.food = food;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        Bird bird0 = new Bird(0);
        Bird bird1 = new Bird(1);
        Bird bird2 = new Bird(2);
        Bird bird3 = new Bird(3);
        Bird bird4 = new Bird(4);

        List<Callable<Object>> birds = new ArrayList<>();
        birds.add(bird0);
        birds.add(bird1);
        birds.add(bird2);
        birds.add(bird3);
        birds.add(bird4);

        try {
            executorService.invokeAll(birds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
