package com.island.model.animals;

public abstract class Predator extends Animal {
    public Predator(String name, double weight, int maxPerCell, int speed, double maxFood) {
        super(name, weight, maxPerCell, speed, maxFood);
    }
}
