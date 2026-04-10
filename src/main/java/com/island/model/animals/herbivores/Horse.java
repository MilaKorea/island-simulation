package com.island.model.animals.herbivores;

import com.island.config.AnimalConfig;
import com.island.model.animals.AnimalType;

public class Horse extends Herbivore {
    public Horse(AnimalConfig config) {
        super(AnimalType.HORSE, config);
    }


}