package com.island.model.animals.herbivores;

import com.island.config.AnimalConfig;
import com.island.model.animals.AnimalType;

public class Rabbit extends Herbivore {
    public Rabbit(AnimalConfig config) {
        super(AnimalType.RABBIT, config);
    }


}
