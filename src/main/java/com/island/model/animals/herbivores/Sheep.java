package com.island.model.animals.herbivores;

import com.island.config.AnimalConfig;
import com.island.model.animals.AnimalType;

public class Sheep extends Herbivore {
    public Sheep(AnimalConfig config) {
        super(AnimalType.SHEEP, config);
    }


}