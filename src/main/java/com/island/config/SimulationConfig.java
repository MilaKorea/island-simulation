package com.island.config;

import java.util.HashMap;
import java.util.Map;

public class SimulationConfig {

    private Map<String, PlantConfig> plants = new HashMap<>();
    private Map<String, AnimalConfig> animals = new HashMap<>();
    private Map<String, Map<String, Integer>> food = new HashMap<>();
    private int reproductionChance;
    private double starvationRate;
    private int plantGrowthPerStep;

    public Map<String, PlantConfig> getPlants() {
        return plants;
    }

    public void setPlants(Map<String, PlantConfig> plants) {
        this.plants = plants;
    }

    public Map<String, AnimalConfig> getAnimals() {
        return animals;
    }

    public void setAnimals(Map<String, AnimalConfig> animals) {
        this.animals = animals;
    }

    public Map<String, Map<String, Integer>> getFood() {
        return food;
    }

    public void setFood(Map<String, Map<String, Integer>> food) {
        this.food = food;
    }
    public int getReproductionChance() {
        return reproductionChance;
    }

    public void setReproductionChance(int reproductionChance) {
        this.reproductionChance = reproductionChance;
    }

    public double getStarvationRate() {
        return starvationRate;
    }

    public void setStarvationRate(double starvationRate) {
        this.starvationRate = starvationRate;
    }
    public int getPlantGrowthPerStep() {
        return plantGrowthPerStep;
    }

    public void setPlantGrowthPerStep(int plantGrowthPerStep) {
        this.plantGrowthPerStep = plantGrowthPerStep;
    }
}
