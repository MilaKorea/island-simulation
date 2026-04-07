package com.island.model;

import com.island.island.Cell;

public abstract class Animal extends IslandEntity {
    protected String name;
    protected int maxPerCell;
    protected int speed;
    protected double maxFood;
    protected double currentFood;

    public Animal(String name, double weight, int maxPerCell, int speed, double maxFood) {
        super(weight);
        this.name = name;
        this.maxPerCell = maxPerCell;
        this.speed = speed;
        this.maxFood = maxFood;
        this.currentFood = maxFood;
    }

    public abstract void eat(Cell cell);
    public abstract Animal reproduce();

    public void starve() {
        currentFood -= maxFood * 0.1;
        if (currentFood <= 0) die();
    }

    public String getName() { return name; }
    public int getMaxPerCell() { return maxPerCell; }
    public int getSpeed() { return speed; }
    public double getCurrentFood() { return currentFood; }

    @Override
    public String toString() { return name; }
}
