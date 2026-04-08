package com.island.model.animals.herbivores;

import com.island.config.AnimalConfig;
import com.island.island.Cell;
import com.island.model.animals.AnimalType;

public class Buffalo extends Herbivore {
    public Buffalo(AnimalConfig config) {
        super(AnimalType.BUFFALO, config);
    }

    @Override
    public void eat(Cell cell) {
        // later
    }
}