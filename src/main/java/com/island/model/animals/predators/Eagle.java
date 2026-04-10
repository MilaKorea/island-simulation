package com.island.model.animals.predators;

import com.island.config.AnimalConfig;
import com.island.island.Cell;
import com.island.model.animals.AnimalType;

public class Eagle extends Predator {
    public Eagle(AnimalConfig config) {
        super(AnimalType.EAGLE, config);
    }


}
