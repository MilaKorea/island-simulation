package com.island.simulation;

import com.island.island.Cell;
import com.island.island.Island;
import com.island.model.IslandEntity;

import com.island.model.animals.AnimalType;

import com.island.model.plants.PlantType;

import java.util.Collection;
import java.util.EnumMap;
import java.util.Map;
import java.util.function.Function;


public class IslandStatistics {

    public void printReport(Island island) {
        Map<AnimalType, Integer> animalStats = countByType(
                island,
                AnimalType.class,
                Cell::getAnimals
        );

        Map<PlantType, Integer> plantStats = countByType(
                island,
                PlantType.class,
                Cell::getPlants
        );

        int totalAnimals = animalStats.values().stream()
                .mapToInt(Integer::intValue)
                .sum();

        int totalPlants = plantStats.values().stream()
                .mapToInt(Integer::intValue)
                .sum();

        System.out.println();
        System.out.println("  ISLAND STATISTICS  ");
        System.out.println("Cells: " + island.getTotalCells());
        System.out.println("Animals: " + totalAnimals);
        System.out.println("Plants: " + totalPlants);

        printAnimalStats(animalStats);
        printPlantStats(plantStats);
    }

    private <T extends Enum<T>, E extends IslandEntity<T>> Map<T, Integer> countByType(
            Island island,
            Class<T> enumClass,
            Function<Cell, Collection<E>> extractor
    ) {
        Map<T, Integer> stats = new EnumMap<>(enumClass);

        for (T type : enumClass.getEnumConstants()) {
            stats.put(type, 0);
        }

        for (Cell cell : island.getAllCells()) {
            for (E entity : extractor.apply(cell)) {
                stats.put(entity.getType(), stats.get(entity.getType()) + 1);
            }
        }

        return stats;
    }

    private void printAnimalStats(Map<AnimalType, Integer> stats) {
        System.out.println();
        System.out.println(" Animals ");

        stats.forEach((type, count) -> {
            if (count > 0) {
                System.out.printf("%s %-15s : %5d%n",
                        type.getIcon(),
                        type,
                        count
                );
            }
        });
    }

    private void printPlantStats(Map<PlantType, Integer> stats) {
        System.out.println();
        System.out.println(" Plants ");

        stats.forEach((type, count) -> {
            if (count > 0) {
                System.out.printf("%s %-15s : %5d%n",
                        type.getIcon(),
                        type,
                        count
                );
            }
        });
    }
}