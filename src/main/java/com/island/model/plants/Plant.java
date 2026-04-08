package com.island.model.plants;

import com.island.config.PlantConfig;
import com.island.model.IslandEntity;

public abstract class Plant implements IslandEntity<PlantType> {
    private final PlantType type;
    private final double weight;
    private final int maxPerCell;
    private boolean alive = true;

    public Plant(PlantConfig config, PlantType type) {
        this.type = type;
        this.weight = config.getWeight();
        this.maxPerCell = config.getMaxPerCell();
    }

    @Override
    public PlantType getType() {
        return type;
    }

    public double getWeight() {
        return weight;
    }

    public int getMaxPerCell() {
        return maxPerCell;
    }

    @Override
    public boolean isAlive() {
        return alive;
    }

    public void die() {
        this.alive = false;
    }

    @Override
    public String toString() {
        return type.name();
    }
}
