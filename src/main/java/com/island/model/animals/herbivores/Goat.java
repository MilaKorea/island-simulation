package com.island.model.animals.herbivores;

import com.island.config.AnimalConfig;
import com.island.island.Cell;
import com.island.model.animals.AnimalType;

public class Goat extends Herbivore {
    public Goat(AnimalConfig config) {
        super(AnimalType.GOAT, config);
    }

    @Override
    public void eat(Cell cell) {
        // later
    }
}