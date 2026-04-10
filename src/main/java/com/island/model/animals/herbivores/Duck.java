package com.island.model.animals.herbivores;

import com.island.config.AnimalConfig;
import com.island.model.animals.AnimalType;

public class Duck extends Herbivore {
    public Duck(AnimalConfig config) {
        super(AnimalType.DUCK, config);
    }


}
