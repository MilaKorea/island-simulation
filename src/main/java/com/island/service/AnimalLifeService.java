package com.island.service;

import com.island.config.SimulationConfig;
import com.island.factory.AnimalFactory;
import com.island.island.Cell;
import com.island.island.Island;
import com.island.model.animals.Animal;
import com.island.model.animals.AnimalType;
import com.island.model.animals.herbivores.Herbivore;
import com.island.model.plants.Plant;
import com.island.simulation.AnimalMove;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class AnimalLifeService {

    private static final String PLANT_KEY = "PLANT";

    private final AnimalFactory animalFactory;
    private final int reproductionChance;
    private final double starvationRate;
    private final Map<String, Map<String, Integer>> foodMap;

    public AnimalLifeService(SimulationConfig config, AnimalFactory animalFactory) {
        this.animalFactory = animalFactory;
        this.reproductionChance = config.getReproductionChance();
        this.starvationRate = config.getStarvationRate();
        this.foodMap = config.getFood();
    }

    public void moveAnimals(Island island) {
        List<AnimalMove> moves = new ArrayList<>();

        for (Cell cell : island.getAllCells()) {
            List<Animal> animals = new ArrayList<>(cell.getAnimals());

            for (Animal animal : animals) {
                if (!animal.isAlive()) {
                    continue;
                }

                Cell targetCell = chooseCell(island, cell, animal);
                if (targetCell != cell) {
                    moves.add(new AnimalMove(animal, cell, targetCell));
                }
            }
        }

        applyMoves(moves);
    }

    public void feedAnimals(Island island) {
        for (Cell cell : island.getAllCells()) {
            List<Animal> animals = new ArrayList<>(cell.getAnimals());

            for (Animal animal : animals) {
                if (!animal.isAlive() || !animal.isHungry()) {
                    continue;
                }

                if (animal instanceof Herbivore) {
                    eatPlant(cell, animal);
                } else {
                    eatAnimal(cell, animal);
                }
            }
        }
    }

    public void reproduceAnimals(Island island) {
        for (Cell cell : island.getAllCells()) {
            List<Animal> animals = new ArrayList<>(cell.getAnimals());

            for (Animal animal : animals) {
                if (!canReproduce(animal)) {
                    continue;
                }

                if (!hasPair(cell, animal.getType())) {
                    continue;
                }

                if (!hasFoodForReproduction(cell, animal)) {
                    continue;
                }

                Animal baby = animalFactory.create(animal.getType());

                if (cell.hasSpaceFor(baby) && isSuccessful(reproductionChance)) {
                    cell.addAnimal(baby);
                }
            }
        }
    }

    public void starveAnimals(Island island) {
        for (Cell cell : island.getAllCells()) {
            for (Animal animal : cell.getAnimals()) {
                animal.starve(starvationRate);
            }
        }
    }

    private Cell chooseCell(Island island, Cell currentCell, Animal animal) {
        if (animal.getSpeed() == 0) {
            return currentCell;
        }

        List<Cell> cellsInRange = island.getAvailableCells(currentCell, animal.getSpeed(), true);
        List<Cell> availableCells = new ArrayList<>();

        for (Cell cell : cellsInRange) {
            if (cell == currentCell || cell.hasSpaceFor(animal)) {
                availableCells.add(cell);
            }
        }

        if (availableCells.isEmpty()) {
            return currentCell;
        }

        int randomIndex = ThreadLocalRandom.current().nextInt(availableCells.size());
        return availableCells.get(randomIndex);
    }

    private void applyMoves(List<AnimalMove> moves) {
        for (AnimalMove move : moves) {
            Animal animal = move.getAnimal();
            Cell from = move.getFrom();
            Cell to = move.getTo();

            if (!animal.isAlive()) {
                continue;
            }

            if (!from.getAnimals().contains(animal)) {
                continue;
            }

            if (!to.hasSpaceFor(animal)) {
                continue;
            }

            from.removeAnimal(animal);
            to.addAnimal(animal);
        }
    }

    private void eatAnimal(Cell cell, Animal hunter) {
        List<Animal> animals = new ArrayList<>(cell.getAnimals());

        for (Animal victim : animals) {
            if (!victim.isAlive()) {
                continue;
            }

            if (victim == hunter) {
                continue;
            }

            int chance = getAnimalFoodChance(hunter.getType(), victim.getType());
            if (chance <= 0) {
                continue;
            }

            if (isSuccessful(chance)) {
                victim.die();
                hunter.restoreFood(victim.getWeight());
                return;
            }
        }
    }

    private void eatPlant(Cell cell, Animal animal) {
        int chance = getPlantFoodChance(animal.getType());
        if (chance <= 0) {
            return;
        }

        List<Plant> plants = new ArrayList<>(cell.getPlants());

        for (Plant plant : plants) {
            if (!plant.isAlive()) {
                continue;
            }

            if (isSuccessful(chance)) {
                plant.die();
                animal.restoreFood(plant.getWeight());
                return;
            }
        }
    }

    private boolean canReproduce(Animal animal) {
        return animal.isAlive() && !animal.isHungry();
    }

    private boolean hasPair(Cell cell, AnimalType type) {
        int count = 0;

        for (Animal animal : cell.getAnimals()) {
            if (animal.isAlive() && animal.getType() == type && !animal.isHungry()) {
                count++;
            }
        }

        return count >= 2;
    }

    private boolean hasFoodForReproduction(Cell cell, Animal animal) {
        if (animal instanceof Herbivore) {
            return hasPlants(cell);
        }

        return hasPrey(cell, animal.getType());
    }

    private boolean hasPlants(Cell cell) {
        for (Plant plant : cell.getPlants()) {
            if (plant.isAlive()) {
                return true;
            }
        }
        return false;
    }

    private boolean hasPrey(Cell cell, AnimalType hunterType) {
        for (Animal animal : cell.getAnimals()) {
            if (!animal.isAlive()) {
                continue;
            }

            if (getAnimalFoodChance(hunterType, animal.getType()) > 0) {
                return true;
            }
        }

        return false;
    }

    private int getAnimalFoodChance(AnimalType hunter, AnimalType victim) {
        Map<String, Integer> hunterFoodMap = foodMap.get(hunter.name());
        if (hunterFoodMap == null) {
            return 0;
        }

        return hunterFoodMap.getOrDefault(victim.name(), 0);
    }

    private int getPlantFoodChance(AnimalType animalType) {
        Map<String, Integer> animalFoodMap = foodMap.get(animalType.name());
        if (animalFoodMap == null) {
            return 0;
        }

        return animalFoodMap.getOrDefault(PLANT_KEY, 0);
    }

    private boolean isSuccessful(int chance) {
        return ThreadLocalRandom.current().nextInt(100) < chance;
    }
}