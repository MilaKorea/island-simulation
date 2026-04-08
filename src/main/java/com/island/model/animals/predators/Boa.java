package com.island.model.animals.predators;

import com.island.config.AnimalConfig;
import com.island.island.Cell;
import com.island.model.animals.AnimalType;

public class Boa extends Predator {
    public Boa(AnimalConfig config) {
        super(AnimalType.BOA, config);
    }

    @Override
    public void eat(Cell cell) {
        // later
    }
}
