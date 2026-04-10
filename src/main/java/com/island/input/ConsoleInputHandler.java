package com.island.input;

import com.island.config.AnimalConfig;
import com.island.config.PlantConfig;
import com.island.config.SimulationConfig;
import com.island.model.animals.AnimalType;
import com.island.model.plants.PlantType;
import com.island.simulation.StartParameters;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ConsoleInputHandler {

    private static final double PREDATOR_MIN_COEFF = 0.01;
    private static final double PREDATOR_MAX_COEFF = 0.03;

    private static final double HERBIVORE_MIN_COEFF = 0.03;
    private static final double HERBIVORE_MAX_COEFF = 0.08;

    private static final double PLANT_MIN_COEFF = 0.10;
    private static final double PLANT_MAX_COEFF = 0.25;

    private final Scanner scanner = new Scanner(System.in);

    public StartParameters readStartParameters(SimulationConfig config) {
        System.out.println("=== ISLAND SIMULATION ===");

        int width = readPositiveInt("Enter island width: ");
        int height = readPositiveInt("Enter island height: ");

        int totalCells = width * height;

        Map<AnimalType, Integer> animalCounts = new LinkedHashMap<>();
        Map<PlantType, Integer> plantCounts = new LinkedHashMap<>();

        readPredators(config, totalCells, animalCounts);
        readHerbivores(config, totalCells, animalCounts);
        readPlants(config, totalCells, plantCounts);

        return new StartParameters(width, height, animalCounts, plantCounts);
    }

    private void readPredators(SimulationConfig config, int totalCells, Map<AnimalType, Integer> animalCounts) {
        System.out.println("\nPredators:");

        List<AnimalType> predatorTypes = List.of(
                AnimalType.WOLF,
                AnimalType.BOA,
                AnimalType.FOX,
                AnimalType.BEAR,
                AnimalType.EAGLE
        );

        printAnimalRecommendations(config, totalCells, predatorTypes, PREDATOR_MIN_COEFF, PREDATOR_MAX_COEFF);
        animalCounts.putAll(readAnimalGroup(config, totalCells, predatorTypes));
    }

    private void readHerbivores(SimulationConfig config, int totalCells, Map<AnimalType, Integer> animalCounts) {
        System.out.println("\nHerbivores:");

        List<AnimalType> herbivoreTypes = List.of(
                AnimalType.HORSE,
                AnimalType.DEER,
                AnimalType.RABBIT,
                AnimalType.MOUSE,
                AnimalType.GOAT,
                AnimalType.SHEEP,
                AnimalType.BOAR,
                AnimalType.BUFFALO,
                AnimalType.DUCK,
                AnimalType.CATERPILLAR
        );

        printAnimalRecommendations(config, totalCells, herbivoreTypes, HERBIVORE_MIN_COEFF, HERBIVORE_MAX_COEFF);
        animalCounts.putAll(readAnimalGroup(config, totalCells, herbivoreTypes));
    }

    private void readPlants(SimulationConfig config, int totalCells, Map<PlantType, Integer> plantCounts) {
        System.out.println("\nPlants:");

        List<PlantType> plantTypes = List.of(PlantType.GRASS);

        for (PlantType type : plantTypes) {
            PlantConfig plantConfig = config.getPlants().get(type.getKey());
            int globalMax = plantConfig.getMaxPerCell() * totalCells;

            RecommendedRange range = calculateRange(globalMax, PLANT_MIN_COEFF, PLANT_MAX_COEFF);

            System.out.printf("%s %-15s max: %d, recommended: %s%n",
                    type.getIcon(),
                    type.name(),
                    globalMax,
                    formatRange(range));
        }

        Map<PlantType, Integer> limits = InputValidator.CapacityHelper.calculateLimits(
                plantTypes,
                totalCells,
                type -> config.getPlants().get(type.getKey()).getMaxPerCell()
        );

        while (true) {
            System.out.println("Enter " + plantTypes.size() + " integers:");

            String input = scanner.nextLine();

            try {
                plantCounts.putAll(InputValidator.parseAndValidate(input, plantTypes, limits));
                return;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                System.out.println("Try again.");
            }
        }
    }

    private void printAnimalRecommendations(
            SimulationConfig config,
            int totalCells,
            List<AnimalType> types,
            double minCoeff,
            double maxCoeff
    ) {
        for (AnimalType type : types) {
            AnimalConfig animalConfig = config.getAnimals().get(type.getKey());
            int globalMax = animalConfig.getMaxPerCell() * totalCells;

            RecommendedRange range = calculateRange(globalMax, minCoeff, maxCoeff);

            System.out.printf("%s %-15s max: %d, recommended: %s%n",
                    type.getIcon(),
                    type.name(),
                    globalMax,
                    formatRange(range));
        }
    }

    private Map<AnimalType, Integer> readAnimalGroup(
            SimulationConfig config,
            int totalCells,
            List<AnimalType> types
    ) {
        Map<AnimalType, Integer> limits = InputValidator.CapacityHelper.calculateLimits(
                types,
                totalCells,
                type -> config.getAnimals().get(type.getKey()).getMaxPerCell()
        );

        while (true) {
            System.out.println("Enter " + types.size() + " integers:");

            String input = scanner.nextLine();

            try {
                return InputValidator.parseAndValidate(input, types, limits);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                System.out.println("Try again.");
            }
        }
    }

    private int readPositiveInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                int value = Integer.parseInt(scanner.nextLine().trim());

                if (value <= 0) {
                    System.out.println("Value must be greater than 0.");
                    continue;
                }

                return value;
            } catch (NumberFormatException e) {
                System.out.println("Enter a valid positive integer.");
            }
        }
    }

    private RecommendedRange calculateRange(int globalMax, double minCoeff, double maxCoeff) {
        int min = Math.max(1, (int) Math.ceil(globalMax * minCoeff));
        int max = Math.max(min, (int) Math.ceil(globalMax * maxCoeff));
        return new RecommendedRange(min, max);
    }

    private String formatRange(RecommendedRange range) {
        return range.getMin() + " - " + range.getMax();
    }

    private static class RecommendedRange {
        private final int min;
        private final int max;

        public RecommendedRange(int min, int max) {
            this.min = min;
            this.max = max;
        }

        public int getMin() {
            return min;
        }

        public int getMax() {
            return max;
        }
    }
}