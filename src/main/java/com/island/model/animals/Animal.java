package com.island.model.animals;

import com.island.island.Cell;
import com.island.model.IslandEntity;
import com.island.config.AnimalConfig;

public abstract class Animal implements IslandEntity<AnimalType> {

    private final AnimalType type;
    private final double weight;
    private boolean alive = true;

    protected final int maxPerCell;
    protected final int speed;
    protected final double maxFood;
    protected double currentFood;

    public Animal(AnimalType type, AnimalConfig config) {
        this.type = type;
        this.weight = config.getWeight();
        this.maxPerCell = config.getMaxPerCell();
        this.speed = config.getSpeed();
        this.maxFood = config.getMaxFood();
        this.currentFood = this.maxFood;
    }

    public abstract void eat(Cell cell);

    public AnimalType getType() {
        return type;
    }

    public double getWeight() {
        return weight;
    }

    public int getMaxPerCell() {
        return maxPerCell;
    }

    public int getSpeed() {
        return speed;
    }

    public double getMaxFood() {
        return maxFood;
    }

    public double getCurrentFood() {
        return currentFood;
    }

    @Override
    public boolean isAlive() {
        return alive;
    }

    public void die() {
        alive = false;
    }

    public void starve() {
        currentFood -= maxFood * 0.1;
        if (currentFood <= 0) {
            die();
        }
    }

    @Override
    public String toString() {
        return type.name();
    }
}
