package com.island.engine;

import com.island.factory.AnimalFactory;
import com.island.factory.PlantFactory;
import com.island.island.Cell;
import com.island.island.Island;
import com.island.model.animals.Animal;
import com.island.model.animals.AnimalType;
import com.island.model.plants.Plant;
import com.island.model.plants.PlantType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.BiPredicate;

public class IslandInitializer {

    private final AnimalFactory animalFactory;
    private final PlantFactory plantFactory;
    private final Random random = new Random();

    public IslandInitializer(AnimalFactory animalFactory, PlantFactory plantFactory) {
        this.animalFactory = animalFactory;
        this.plantFactory = plantFactory;
    }

    public void populate(Island island, StartParameters startParameters) {
        populateAnimals(island, startParameters.getAnimalCounts());
        populatePlants(island, startParameters.getPlantCounts());
    }

    private void populateAnimals(Island island, Map<AnimalType, Integer> animalCounts) {
        for (Map.Entry<AnimalType, Integer> entry : animalCounts.entrySet()) {
            AnimalType type = entry.getKey();
            int count = entry.getValue();

            for (int i = 0; i < count; i++) {
                Animal animal = animalFactory.create(type);
                Cell cell = findRandomAvailableCell(island, animal, Cell::hasSpaceFor, "animal " + animal.getType());
                cell.addAnimal(animal);
            }
        }
    }

    private void populatePlants(Island island, Map<PlantType, Integer> plantCounts) {
        for (Map.Entry<PlantType, Integer> entry : plantCounts.entrySet()) {
            PlantType type = entry.getKey();
            int count = entry.getValue();

            for (int i = 0; i < count; i++) {
                Plant plant = plantFactory.create(type);
                Cell cell = findRandomAvailableCell(island, plant, Cell::hasSpaceFor, "plant " + plant.getType());
                cell.addPlant(plant);
            }
        }
    }

    private <E> Cell findRandomAvailableCell(
            Island island,
            E entity,
            BiPredicate<Cell, E> hasSpacePredicate,
            String entityDescription
    ) {
        List<Cell> availableCells = findAvailableCells(island, entity, hasSpacePredicate);

        if (availableCells.isEmpty()) {
            throw new IllegalStateException("No free cell for " + entityDescription);
        }

        return availableCells.get(random.nextInt(availableCells.size()));
    }

    private <E> List<Cell> findAvailableCells(
            Island island,
            E entity,
            BiPredicate<Cell, E> hasSpacePredicate
    ) {
        List<Cell> availableCells = new ArrayList<>();

        for (Cell cell : island.getAllCells()) {
            if (hasSpacePredicate.test(cell, entity)) {
                availableCells.add(cell);
            }
        }

        return availableCells;
    }
}