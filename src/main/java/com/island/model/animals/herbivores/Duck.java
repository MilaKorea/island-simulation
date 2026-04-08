package com.island.model.animals.herbivores;

import com.island.config.AnimalConfig;
import com.island.island.Cell;
import com.island.model.animals.AnimalType;

public class Duck extends Herbivore {
    public Duck(AnimalConfig config) {
        super(AnimalType.DUCK, config);
    }

    @Override
    public void eat(Cell cell) {
        // later
    }
}
