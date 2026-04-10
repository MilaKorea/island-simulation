package com.island.model.animals.predators;

import com.island.config.AnimalConfig;
import com.island.island.Cell;
import com.island.model.animals.AnimalType;

public class Bear extends Predator {
    public Bear(AnimalConfig config) {
        super(AnimalType.BEAR, config);
    }


}
