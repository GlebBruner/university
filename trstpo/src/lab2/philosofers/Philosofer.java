package lab2.philosofers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

public class Philosofer implements Callable<String> {

    private static List<Fork> mutualForks = new ArrayList<>();
    private static final Integer HIGH_BOUND = 80;
    private static final Integer LOW_BOUND = 20;

    static {
        for (int i = 0; i < 5; i++) {
            mutualForks.add(new Fork(i, false));
        }
    }

    private Integer id;
    private Integer forksNumber = 0;
    private Fork leftFork;
    private Fork rightFork;
    private Integer hp;
    private Integer mana;

    //true - free
    private boolean isRightHandFree = true;
    private boolean isLeftHandFree = true;

    //neighbours
    private Philosofer leftNeighbour;
    private Philosofer rightNeighbour;

    private Philosofer(Integer id) {
        this.id = id;
        this.hp = ThreadLocalRandom.current().nextInt(0, 101);
        this.mana = ThreadLocalRandom.current().nextInt(0, 101);
    }

    private void doSomething() {
        if (getForksNumber() == 2) {
            System.out.println("Philosofer # " + getId() + " is eating, he has " + getForksNumber() + " forks");

            increaseHP();
            decreaseMana();

        } else {
            System.out.println("Philosofer # " + getId() + " is thinking, he has " + getForksNumber() + " forks");

            decreaseHP();
            increaseMana();
        }
    }

    private void getRightForkInHand() {

        if (isRightHandFree) {

            if (getRightFork().isBusy()) {
                //System.out.println("P" + getId() +  "But this(right) fork has owner now");
            } else {
                this.getRightFork().setBusy(true);
                this.getRightFork().setOwner(this);
                this.forksNumber++;
                this.isRightHandFree = false;
            }


        } //else //System.out.println("P" + getId() + "This(Right) hand has already  fork");


    }

    private void getLeftForkInHand() {

        if (isLeftHandFree) {

            if (getLeftFork().isBusy()) {
                //alert
                //System.out.println("P" + getId() +  "But this(left) fork has owner now");
            } else {
                this.getLeftFork().setBusy(true);
                this.getLeftFork().setOwner(this);
                this.forksNumber++;
                this.isLeftHandFree = false;
            }


        } //else System.out.println( "P" + getId() + "This(Left) hand is already has fork");
    }

    private void freeRightHand () {

        if (!isRightHandFree) {
            this.getRightFork().setBusy(false);
            this.getRightFork().setOwner(null);
            this.forksNumber--;
            this.isRightHandFree = true;
        } else {
            //System.out.println("P" + getId() +  "This(Right) hand doesn't have any forks");
        }


    }

    private void freeLeftHand () {

        if (!isLeftHandFree) {
            this.getLeftFork().setBusy(false);
            this.getLeftFork().setOwner(null);
            this.forksNumber--;
            this.isLeftHandFree = true;
        } else {
            //System.out.println("P" + getId() + "This(Left) hand doesn't have any forks");
        }

    }

    private void increaseHP() {
        this.hp += ThreadLocalRandom.current().nextInt(5,11);
    }
    private void decreaseHP() {
        this.hp -= ThreadLocalRandom.current().nextInt(5,11);
    }
    private void increaseMana () {
        this.mana += ThreadLocalRandom.current().nextInt(5,11);
    }
    private void decreaseMana() {
        this.mana -= ThreadLocalRandom.current().nextInt(5,11);
    }

    @Override
    public String call() throws Exception {

        while (true) {
            Thread.sleep(250);
            System.out.println("Philosofer - " + getId() + ", hp - " + getHp() + " mana - " + getMana());
            if (getHp() <= LOW_BOUND || getMana() >= HIGH_BOUND) { //was ||

                if (getLeftNeighbour().getHp() > getHp() || getRightNeighbour().getHp() > getHp()) {
                    getRightForkInHand();
                    getLeftForkInHand();
                    doSomething();
                } else {
                    doSomething();
                }


            } else {
                if (getHp() >= HIGH_BOUND || getMana() <= LOW_BOUND) { //was ||
                    freeRightHand();
                    freeLeftHand();
                    doSomething();

                } else {
                    if ( (getHp() <= HIGH_BOUND && getHp() >= LOW_BOUND) && (getMana() <= HIGH_BOUND && getMana() >= LOW_BOUND)) { //was ||
                        if (getHp() > getMana()) {
                            freeRightHand();
                            freeLeftHand();
                            doSomething();
                        } else if (getMana() > getHp()) {

                            if (getLeftNeighbour().getHp() > getHp() || getRightNeighbour().getHp() > getHp()) {
                                getRightForkInHand();
                                getLeftForkInHand();
                                doSomething();
                                freeRightHand();
                                freeLeftHand();
                            } else {
                                freeRightHand();
                                freeLeftHand();
                                doSomething();
                            }


                            /*getRightForkInHand();
                            getLeftForkInHand();
                            doSomething();
                            freeRightHand();
                            freeLeftHand();*/

                        } else {
                            if (new Random().nextDouble() <= 0.5) {

                                if (getLeftNeighbour().getHp() > getHp() || getRightNeighbour().getHp() > getHp()) {
                                    getRightForkInHand();
                                    getLeftForkInHand();
                                    doSomething();
                                } else {
                                    doSomething();
                                }

                                /*getRightForkInHand();
                                getLeftForkInHand();
                                doSomething();*/
                            } else {
                                freeRightHand();
                                freeLeftHand();
                                doSomething();
                            }
                        }
                    }
                }
            }
            Thread.sleep(250);
            System.out.println("=======================");
        }

    }

    private static List<Fork> getMutualForks() {
        return mutualForks;
    }

    public static void setMutualForks(List<Fork> mutualForks) {
        Philosofer.mutualForks = mutualForks;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    private Integer getForksNumber() {
        return forksNumber;
    }

    public void setForksNumber(Integer forksNumber) {
        this.forksNumber = forksNumber;
    }

    private Fork getLeftFork() {
        return leftFork;
    }

    private void setLeftFork(Fork leftFork) {
        this.leftFork = leftFork;
    }

    private Fork getRightFork() {
        return rightFork;
    }

    private void setRightFork(Fork rightFork) {
        this.rightFork = rightFork;
    }

    private Integer getHp() {
        return hp;
    }

    public void setHp(Integer hp) {
        this.hp = hp;
    }

    private Integer getMana() {
        return mana;
    }

    public void setMana(Integer mana) {
        this.mana = mana;
    }

    public boolean isRightHandFree() {
        return isRightHandFree;
    }

    public void setRightHandFree(boolean rightHandFree) {
        isRightHandFree = rightHandFree;
    }

    public boolean isLeftHandFree() {
        return isLeftHandFree;
    }

    public void setLeftHandFree(boolean leftHandFree) {
        isLeftHandFree = leftHandFree;
    }

    private Philosofer getLeftNeighbour() {
        return leftNeighbour;
    }

    private void setLeftNeighbour(Philosofer leftNeighbour) {
        this.leftNeighbour = leftNeighbour;
    }

    private Philosofer getRightNeighbour() {
        return rightNeighbour;
    }

    private void setRightNeighbour(Philosofer rightNeighbour) {
        this.rightNeighbour = rightNeighbour;
    }

    public static void main(String[] args) {
        Philosofer philosofer0 = new Philosofer(0);
        philosofer0.setLeftFork(Philosofer.getMutualForks().get(0));
        philosofer0.setRightFork(Philosofer.getMutualForks().get(1));

        Philosofer philosofer1 = new Philosofer(1);
        philosofer1.setLeftFork(Philosofer.getMutualForks().get(1));
        philosofer1.setRightFork(Philosofer.getMutualForks().get(2));

        Philosofer philosofer2 = new Philosofer(2);
        philosofer2.setLeftFork(Philosofer.getMutualForks().get(2));
        philosofer2.setRightFork(Philosofer.getMutualForks().get(3));

        Philosofer philosofer3 = new Philosofer(3);
        philosofer3.setLeftFork(Philosofer.getMutualForks().get(3));
        philosofer3.setRightFork(Philosofer.getMutualForks().get(4));

        Philosofer philosofer4 = new Philosofer(4);
        philosofer4.setLeftFork(Philosofer.getMutualForks().get(4));
        philosofer4.setRightFork(Philosofer.getMutualForks().get(0));

        philosofer0.setLeftNeighbour(philosofer4);
        philosofer0.setRightNeighbour(philosofer1);

        philosofer1.setLeftNeighbour(philosofer0);
        philosofer1.setRightNeighbour(philosofer2);

        philosofer2.setLeftNeighbour(philosofer1);
        philosofer2.setRightNeighbour(philosofer3);

        philosofer3.setLeftNeighbour(philosofer2);
        philosofer3.setRightNeighbour(philosofer4);

        philosofer4.setLeftNeighbour(philosofer3);
        philosofer4.setRightNeighbour(philosofer0);

        ExecutorService executorService = Executors.newFixedThreadPool(5);

        List<Callable<String>> philosofers = new ArrayList<>();
        philosofers.add(philosofer0);
        philosofers.add(philosofer1);
        philosofers.add(philosofer2);
        philosofers.add(philosofer3);
        philosofers.add(philosofer4);

        System.out.println("+++++++++++++++++++++++++++");
        System.out.println(philosofer0.getId() + " hp = " + philosofer0.getHp()+ " mana = " + philosofer0.getMana());
        System.out.println(philosofer1.getId() +  " hp = " + philosofer1.getHp()+ " mana = " + philosofer1.getMana());
        System.out.println(philosofer2.getId() +  " hp = " + philosofer2.getHp()+ " mana = " + philosofer2.getMana());
        System.out.println(philosofer3.getId() +  " hp = " + philosofer3.getHp()+ " mana = " + philosofer3.getMana());
        System.out.println(philosofer4.getId() +  " hp = " + philosofer4.getHp()+ " mana = " + philosofer4.getMana());
        System.out.println("+++++++++++++++++++++++++++");


        try {
            executorService.invokeAll(philosofers);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
