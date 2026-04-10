package com.island.simulation;

import com.island.island.Cell;
import com.island.island.Island;
import com.island.model.animals.Animal;
import com.island.model.animals.herbivores.Herbivore;
import com.island.model.plants.Plant;
import com.island.service.AnimalLifeService;
import com.island.service.PlantLifeService;

public class SimulationEngine {

    private final Island island;
    private final IslandStatistics statistics;
    private final AnimalLifeService animalLifeService;
    private final PlantLifeService plantLifeService;

    public SimulationEngine(
            Island island,
            IslandStatistics statistics,
            AnimalLifeService animalLifeService,
            PlantLifeService plantLifeService
    ) {
        this.island = island;
        this.statistics = statistics;
        this.animalLifeService = animalLifeService;
        this.plantLifeService = plantLifeService;
    }

    public synchronized void runAnimalStep(int step) {
        System.out.println();
        System.out.println("ANIMAL STEP: " + step);

        animalLifeService.moveAnimals(island);
        animalLifeService.feedAnimals(island);
        animalLifeService.reproduceAnimals(island);
        animalLifeService.starveAnimals(island);

        removeDead();
    }

    public synchronized void runPlantStep() {
        plantLifeService.growPlants(island);
        removeDead();
    }

    public synchronized void printStatistics() {
        statistics.printReport(island);
    }

    public synchronized boolean isFinished(int currentStep, int maxSteps) {
        if (currentStep >= maxSteps) {
            return true;
        }

        if (!hasAliveAnimals()) {
            return true;
        }

        if (!hasAliveHerbivores()) {
            System.out.println("No herbivores left. Ecosystem cannot continue.");
            return true;
        }

        return false;
    }

    private boolean hasAliveAnimals() {
        for (Cell cell : island.getAllCells()) {
            for (Animal animal : cell.getAnimals()) {
                if (animal.isAlive()) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean hasAliveHerbivores() {
        for (Cell cell : island.getAllCells()) {
            for (Animal animal : cell.getAnimals()) {
                if (animal.isAlive() && animal instanceof Herbivore) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean hasAlivePlants() {
        for (Cell cell : island.getAllCells()) {
            for (Plant plant : cell.getPlants()) {
                if (plant.isAlive()) {
                    return true;
                }
            }
        }
        return false;
    }

    private void removeDead() {
        for (Cell cell : island.getAllCells()) {
            cell.removeDead();
        }
    }
}