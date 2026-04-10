package com.island.model.animals.herbivores;

import com.island.config.AnimalConfig;
import com.island.model.animals.Animal;
import com.island.model.animals.AnimalType;

public abstract class Herbivore extends Animal {
    public Herbivore(AnimalType type, AnimalConfig config) {
        super(type, config);
    }
}
