package com.island.model.animals;

public  abstract class Herbivore extends Animal {

    public Herbivore(String name, double weight, int maxPerCell, int speed, double maxFood) {
        super(name, weight, maxPerCell, speed, maxFood);
    }
}
