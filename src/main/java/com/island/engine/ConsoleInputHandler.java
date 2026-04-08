package com.island.engine;

import com.island.config.SimulationConfig;
import com.island.model.animals.AnimalType;
import com.island.model.Group;
import com.island.model.plants.PlantType;

import java.util.*;
import java.util.function.Function;

public class ConsoleInputHandler {

    private final Scanner scanner = new Scanner(System.in);

    public StartParameters readStartParameters(SimulationConfig config) {
        System.out.println("=== ISLAND SIMULATION ===");

        int width = readPositive("Enter island width: ");
        int height = readPositive("Enter island height: ");
        int totalCells = width * height;

        List<AnimalType> predators = getAnimalByGroup(Group.PREDATOR);
        List<AnimalType> herbivores = getAnimalByGroup(Group.HERBIVORE);
        List<PlantType> plants = List.of(PlantType.GRASS);

        Map<AnimalType, Integer> predatorCounts =
                readGroup("Predators", predators, totalCells,
                        t -> getAnimalMaxPerCell(t, config),
                        this::getAnimalIcon);

        Map<AnimalType, Integer> herbivoreCounts =
                readGroup("Herbivores", herbivores, totalCells,
                        t -> getAnimalMaxPerCell(t, config),
                        this::getAnimalIcon);

        Map<PlantType, Integer> plantCounts =
                readGroup("Plants", plants, totalCells,
                        t -> getPlantMaxPerCell(t, config),
                        this::getPlantIcon);

        Map<AnimalType, Integer> animalCounts = new HashMap<>();
        animalCounts.putAll(predatorCounts);
        animalCounts.putAll(herbivoreCounts);

        return new StartParameters(width, height, animalCounts, plantCounts);
    }

    // 🔥 универсальный generic метод
    private <T> Map<T, Integer> readGroup(
            String title,
            List<T> types,
            int totalCells,
            Function<T, Integer> maxPerCellExtractor,
            Function<T, String> iconExtractor
    ) {
        Map<T, Integer> limits = InputValidator.CapacityHelper.calculateLimits(
                types,
                totalCells,
                maxPerCellExtractor
        );

        System.out.println();
        System.out.println(title + ":");

        for (T type : types) {
            System.out.println(iconExtractor.apply(type) + " " + type + " max: " + limits.get(type));
        }

        System.out.println("Enter " + types.size() + " integers separated by spaces in this order:");
        types.forEach(type -> System.out.print(type + " "));
        System.out.println();

        while (true) {
            try {
                String input = scanner.nextLine();
                return InputValidator.parseAndValidate(input, types, limits);
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
                System.out.println("Try again:");
            }
        }
    }

    private int readPositive(String message) {
        while (true) {
            System.out.print(message);

            try {
                int value = Integer.parseInt(scanner.nextLine().trim());
                if (value <= 0) {
                    System.out.println("Value must be greater than 0.");
                    continue;
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid integer.");
            }
        }
    }

    // 🔥 получаем животных по группе (без хардкода)
    private List<AnimalType> getAnimalByGroup(Group group) {
        return Arrays.stream(AnimalType.values())
                .filter(t -> t.getGroup() == group)
                .toList();
    }

    // 🔥 убрали дублирование
    private int getAnimalMaxPerCell(AnimalType type, SimulationConfig config) {
        return config.getAnimals().get(type.getKey()).getMaxPerCell();
    }

    private int getPlantMaxPerCell(PlantType type, SimulationConfig config) {
        return config.getPlants().get(type.getKey()).getMaxPerCell();
    }

    private String getAnimalIcon(AnimalType type) {
        return switch (type) {
            case WOLF -> "🐺";
            case BOA -> "🐍";
            case FOX -> "🦊";
            case BEAR -> "🐻";
            case EAGLE -> "🦅";
            case HORSE -> "🐎";
            case DEER -> "🦌";
            case RABBIT -> "🐇";
            case MOUSE -> "🐁";
            case GOAT -> "🐐";
            case SHEEP -> "🐑";
            case BOAR -> "🐗";
            case BUFFALO -> "🐃";
            case DUCK -> "🦆";
            case CATERPILLAR -> "🐛";
        };
    }

    private String getPlantIcon(PlantType type) {
        return switch (type) {
            case GRASS -> "🌿";
        };
    }
}