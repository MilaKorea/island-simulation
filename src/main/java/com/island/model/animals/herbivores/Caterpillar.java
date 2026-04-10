package com.island.model.animals.herbivores;

import com.island.config.AnimalConfig;
import com.island.model.animals.AnimalType;

public class Caterpillar extends Herbivore {
    public Caterpillar(AnimalConfig config) {
        super(AnimalType.CATERPILLAR, config);
    }


}