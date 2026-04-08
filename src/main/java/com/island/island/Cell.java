package com.island.island;

import com.island.model.IslandEntity;
import com.island.model.animals.Animal;
import com.island.model.animals.AnimalType;
import com.island.model.plants.Plant;
import com.island.model.plants.PlantType;

import java.util.ArrayList;
import java.util.List;

public class Cell {
    private final List<Animal> animals = new ArrayList<>();
    private final List<Plant> plants = new ArrayList<>();

    public List<Animal> getAnimals() {
        return animals;
    }

    public List<Plant> getPlants() {
        return plants;
    }

    public boolean addAnimal(Animal animal) {
        if (!hasSpaceFor(animal)) {
            return false;
        }
        animals.add(animal);
        return true;
    }

    public boolean addPlant(Plant plant) {
        if (!hasSpaceFor(plant)) {
            return false;
        }
        plants.add(plant);
        return true;
    }

    public boolean hasSpaceFor(Animal animal) {
        return countSameType(animals, animal) < animal.getMaxPerCell();
    }

    public boolean hasSpaceFor(Plant plant) {
        return countSameType(plants, plant) < plant.getMaxPerCell();
    }

    public int countAnimalsByType(AnimalType type) {
        return (int) animals.stream()
                .filter(animal -> animal.getType() == type)
                .count();
    }

    public int countPlantsByType(PlantType type) {
        return (int) plants.stream()
                .filter(plant -> plant.getType() == type)
                .count();
    }

    public void removeDead() {
        animals.removeIf(animal -> !animal.isAlive());
        plants.removeIf(plant -> !plant.isAlive());
    }

    private <T, E extends IslandEntity<T>> long countSameType(List<E> entities, E entity) {
        return entities.stream()
                .filter(e -> e.getType() == entity.getType())
                .count();
    }

    @Override
    public String toString() {
        return "Cell{" +
                "animals=" + animals +
                ", plants=" + plants +
                '}';
    }
}