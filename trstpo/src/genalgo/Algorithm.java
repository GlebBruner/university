package genalgo;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class Algorithm {

    private static final double MIN_RANGE = -1.28d;
    private static final double MAX_RANGE = 1.28d;
    private static final int GENERATION_SIZE = 400;
    private static final int CHILD_NUM = 400;
    private static final double MUTATION_PROBABILITY = 0.1;

    private List<PairXY> newGeneration(int count) {
        List<PairXY> chromosomes = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            double doubleX = BigDecimal.valueOf(MIN_RANGE + (MAX_RANGE - MIN_RANGE) * random.nextDouble()).setScale(2, RoundingMode.HALF_UP).doubleValue();
            double doubleY = BigDecimal.valueOf(MIN_RANGE + (MAX_RANGE - MIN_RANGE) * random.nextDouble()).setScale(2, RoundingMode.HALF_UP).doubleValue();
            chromosomes.add(new PairXY(doubleX, doubleY));
        }
        return chromosomes;
    }

    private PairXY crossover(PairXY firstParent, PairXY secondParent) {
        while (true) {
            int pointForX = ThreadLocalRandom.current().nextInt(1, firstParent.getBinaryX().length());
            int pointForY = ThreadLocalRandom.current().nextInt(1, firstParent.getBinaryX().length());
            String childX = firstParent.getBinaryX().substring(0, pointForX) + secondParent.getBinaryX().substring(pointForX, secondParent.getBinaryX().length());
            String childY = firstParent.getBinaryY().substring(0, pointForY) + secondParent.getBinaryY().substring(pointForY, secondParent.getBinaryY().length());
            if (AnotherConverter.FromBinaryToDouble(childX) <= MAX_RANGE && AnotherConverter.FromBinaryToDouble(childX) >= MIN_RANGE) {
                if (AnotherConverter.FromBinaryToDouble(childY) <= MAX_RANGE && AnotherConverter.FromBinaryToDouble(childY) >= MIN_RANGE) {
                    return new PairXY(childX, childY);
                }
            }
        }
    }

    private void mutate(List<PairXY> generation, double probability) {
        for (PairXY pair :
                generation) {
            for (int i = 0; i < pair.getBinaryX().length() - 1; i++) {

                if (new Random().nextDouble() <= probability) {
                    StringBuilder forMutation = new StringBuilder(pair.getBinaryX());
                    if (pair.getBinaryX().charAt(i) == '0') {
                        forMutation.setCharAt(i, '1');
                    } else if (pair.getBinaryX().charAt(i) == '1') {
                        forMutation.setCharAt(i, '0');
                    }
                    forMutation.trimToSize();
                    pair.setBinaryX(forMutation.toString());
                }

                if (new Random().nextDouble() <= probability) {
                    StringBuilder forMutation = new StringBuilder(pair.getBinaryY());
                    if (pair.getBinaryY().charAt(i) == '0') {
                        forMutation.setCharAt(i, '1');
                    } else if (pair.getBinaryY().charAt(i) == '1') {
                        forMutation.setCharAt(i, '0');
                    }
                    forMutation.trimToSize();
                    pair.setBinaryY(forMutation.toString());
                }

        }
    }
    }

    private void expandGeneration(List<PairXY> generation, int size) {
        for (int i = 0; i < size; i++) {
            int firstRandom = ThreadLocalRandom.current().nextInt(0, generation.size());
            int secondRandom = ThreadLocalRandom.current().nextInt(0, generation.size());
            PairXY newChild = crossover(generation.get(firstRandom),
                    generation.get(secondRandom));
            generation.add(newChild);
        }
    }

    private Map<PairXY, Double> selection(List<PairXY> allChromosomes) {
        Double fitnessFunc;
        Map<PairXY, Double> pairXYFitnessMap = new HashMap<>();
        for (PairXY p :
                allChromosomes) {
            fitnessFunc = 100 / ( 100*(Math.pow(p.getDoubleX(), 2) - p.getDoubleY() ) +  Math.pow(1 - p.getDoubleX(), 2) + 1);
            pairXYFitnessMap.put(p, fitnessFunc);
        }
        return pairXYFitnessMap.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.naturalOrder())).skip(CHILD_NUM)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> oldValue, LinkedHashMap::new));
    }

    private double getCurrentMax(Map<PairXY, Double> mapGeneration) {
        return (new ArrayList<>(mapGeneration.values())).get(mapGeneration.size() - 1);
    }

    private void geneticAlgorithm() {
        List<PairXY> generation = newGeneration(GENERATION_SIZE);
        int ITERATIONS = 200;
        for (int i = 0; i < ITERATIONS; i++) {
            expandGeneration(generation, CHILD_NUM);
            mutate(generation, MUTATION_PROBABILITY);
            Map<PairXY, Double> selectedGeneration = selection(generation);
            double currentMax = getCurrentMax(selectedGeneration);
            System.out.println("Result - " + currentMax);
            generation = new ArrayList<>(selectedGeneration.keySet());
        }
    }

    static private void allValues () {

        List<Double> allResults = new ArrayList<>();
        double tempI = 0.0d;
        double tempJ = 0.0d;
        Double max = 0.0;
        Double fitnessFunc;
        for (double i = -1.28; i <= 1.29; i=i+0.01) {
            for (double j = -1.28; j <= 1.29; j=j+0.01) {
                fitnessFunc = 100.0 / ( 100.0*(Math.pow(i, 2.0) - j) +  Math.pow(1.0 - i, 2.0) + 1.0);
                allResults.add(fitnessFunc);
                if (fitnessFunc > max) {
                    max = fitnessFunc;
                    tempI = i;
                    tempJ = j;
                }

            }
        }
        Object[] biggestValues =  allResults.stream().sorted(Double::compareTo).skip(66000).toArray(); //we have 66049 elements
        for (Object o :
                biggestValues) {
            System.out.println(o);
        }
        System.out.println("i = " + tempI);
        System.out.println("j = " + tempJ);
        System.out.println(max);
    }

    public static void main(String[] args) {
        Algorithm algorithm = new Algorithm();
        long start = System.currentTimeMillis();
        algorithm.geneticAlgorithm();
        System.out.println(System.currentTimeMillis() - start);
        System.out.println("---------------------------------------");
        Algorithm.allValues();
    }
}