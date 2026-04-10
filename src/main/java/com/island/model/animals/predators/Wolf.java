package com.island.model.animals.predators;

import com.island.config.AnimalConfig;
import com.island.island.Cell;
import com.island.model.animals.AnimalType;

public class Wolf extends Predator {
    public Wolf(AnimalConfig config) {
        super(AnimalType.WOLF, config);
    }


}
