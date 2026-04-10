package com.island.factory;

import com.island.config.PlantConfig;
import com.island.config.SimulationConfig;
import com.island.model.plants.Grass;
import com.island.model.plants.Plant;
import com.island.model.plants.PlantType;

public class PlantFactory {
    private final SimulationConfig config;

    public PlantFactory(SimulationConfig config) {
        this.config = config;
    }

    public Plant create(PlantType type) {
        PlantConfig c = config.getPlants().get(type.getKey());

        if (c == null) {
            throw new IllegalArgumentException("No config for plant: " + type);
        }

        return switch (type) {
            case GRASS -> new Grass(c);
        };
    }
}
