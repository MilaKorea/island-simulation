package com.island.model.animals;

import com.island.model.Group;

public enum AnimalType {

    // 🐺 predators
    WOLF("wolf", Group.PREDATOR),
    BOA("boa", Group.PREDATOR),
    FOX("fox", Group.PREDATOR),
    BEAR("bear", Group.PREDATOR),
    EAGLE("eagle", Group.PREDATOR),

    // 🐑 herbivores
    HORSE("horse", Group.HERBIVORE),
    DEER("deer", Group.HERBIVORE),
    RABBIT("rabbit", Group.HERBIVORE),
    MOUSE("mouse", Group.HERBIVORE),
    GOAT("goat", Group.HERBIVORE),
    SHEEP("sheep", Group.HERBIVORE),
    BOAR("boar", Group.HERBIVORE),
    BUFFALO("buffalo", Group.HERBIVORE),
    DUCK("duck", Group.HERBIVORE),
    CATERPILLAR("caterpillar", Group.HERBIVORE);

    private final String key;
    private final Group group;

    AnimalType(String key, Group group) {
        this.key = key;
        this.group = group;
    }

    public String getKey() {
        return key;
    }

    public Group getGroup() {
        return group;
    }
}