package com.island.model.animals.predators;

import com.island.config.AnimalConfig;
import com.island.model.animals.Animal;
import com.island.model.animals.AnimalType;

public abstract class Predator extends Animal {
    public Predator(AnimalType type, AnimalConfig config) {
        super(type, config);
    }
}