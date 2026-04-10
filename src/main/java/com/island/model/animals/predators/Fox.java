package com.island.model.animals.predators;

import com.island.config.AnimalConfig;
import com.island.island.Cell;
import com.island.model.animals.AnimalType;

public class Fox extends Predator {
    public Fox(AnimalConfig config) {
        super(AnimalType.FOX, config);
    }

}
