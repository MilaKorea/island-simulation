package com.island.model.animals.herbivores;

import com.island.config.AnimalConfig;
import com.island.model.animals.AnimalType;

public class Deer extends Herbivore {
    public Deer(AnimalConfig config) {
        super(AnimalType.DEER, config);
    }


}