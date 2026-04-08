package com.island.engine;

import com.island.model.animals.AnimalType;
import com.island.model.plants.PlantType;

import java.util.Map;

public class StartParameters {
    private final int width;
    private final int height;
    private final Map<AnimalType, Integer> animalCounts;
    private final Map<PlantType, Integer> plantCounts;

    public StartParameters(int width,
                           int height,
                           Map<AnimalType, Integer> animalCounts,
                           Map<PlantType, Integer> plantCounts) {
        this.width = width;
        this.height = height;
        this.animalCounts = animalCounts;
        this.plantCounts = plantCounts;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Map<AnimalType, Integer> getAnimalCounts() {
        return animalCounts;
    }

    public Map<PlantType, Integer> getPlantCounts() {
        return plantCounts;
    }

    @Override
    public String toString() {
        return "StartParameters{" +
                "width=" + width +
                ", height=" + height +
                ", animalCounts=" + animalCounts +
                ", plantCounts=" + plantCounts +
                '}';
    }
}
