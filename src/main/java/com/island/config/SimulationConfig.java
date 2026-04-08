package com.island.config;

import java.util.HashMap;
import java.util.Map;

public class SimulationConfig {

    private Map<String, PlantConfig> plants = new HashMap<>();
    private Map<String, AnimalConfig> animals = new HashMap<>();

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
}
