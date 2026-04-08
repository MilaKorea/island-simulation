package com.island.model.animals.herbivores;

import com.island.config.AnimalConfig;
import com.island.island.Cell;
import com.island.model.animals.AnimalType;

public class Mouse extends Herbivore {
    public Mouse(AnimalConfig config) {
        super(AnimalType.MOUSE, config);
    }

    @Override
    public void eat(Cell cell) {
        // later
    }
}
