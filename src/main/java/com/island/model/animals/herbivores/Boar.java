package com.island.model.animals.herbivores;

import com.island.config.AnimalConfig;
import com.island.island.Cell;
import com.island.model.animals.AnimalType;

public class Boar extends Herbivore {
    public Boar(AnimalConfig config) {
        super(AnimalType.BOAR, config);
    }

    @Override
    public void eat(Cell cell) {
        // later
    }
}