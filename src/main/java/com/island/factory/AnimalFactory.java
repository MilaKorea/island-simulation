package com.island.factory;

import com.island.config.AnimalConfig;
import com.island.config.SimulationConfig;
import com.island.model.animals.Animal;
import com.island.model.animals.AnimalType;
import com.island.model.animals.herbivores.*;
import com.island.model.animals.predators.*;

public class AnimalFactory {
    private final SimulationConfig config;

    public AnimalFactory(SimulationConfig config) {
        this.config = config;
    }

    public Animal create(AnimalType type) {
        AnimalConfig c = config.getAnimals().get(type.getKey());

        if (c == null) {
            throw new IllegalArgumentException("No config for: " + type);
        }

        return switch (type) {
            case WOLF -> new Wolf(c);
            case BOA -> new Boa(c);
            case FOX -> new Fox(c);
            case BEAR -> new Bear(c);
            case EAGLE -> new Eagle(c);

            case HORSE -> new Horse(c);
            case DEER -> new Deer(c);
            case RABBIT -> new Rabbit(c);
            case MOUSE -> new Mouse(c);
            case GOAT -> new Goat(c);
            case SHEEP -> new Sheep(c);
            case BOAR -> new Boar(c);
            case BUFFALO -> new Buffalo(c);
            case DUCK -> new Duck(c);
            case CATERPILLAR -> new Caterpillar(c);
        };
    }
}