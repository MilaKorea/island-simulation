package com.island.model.animals;

import com.island.model.Group;

public enum AnimalType {

    WOLF("wolf", Group.PREDATOR, "🐺"),
    BOA("boa", Group.PREDATOR, "🐍"),
    FOX("fox", Group.PREDATOR, "🦊"),
    BEAR("bear", Group.PREDATOR, "🐻"),
    EAGLE("eagle", Group.PREDATOR, "🦅"),

    HORSE("horse", Group.HERBIVORE, "🐎"),
    DEER("deer", Group.HERBIVORE, "🦌"),
    RABBIT("rabbit", Group.HERBIVORE, "🐇"),
    MOUSE("mouse", Group.HERBIVORE, "🐁"),
    GOAT("goat", Group.HERBIVORE, "🐐"),
    SHEEP("sheep", Group.HERBIVORE, "🐑"),
    BOAR("boar", Group.HERBIVORE, "🐗"),
    BUFFALO("buffalo", Group.HERBIVORE, "🐃"),
    DUCK("duck", Group.HERBIVORE, "🦆"),
    CATERPILLAR("caterpillar", Group.HERBIVORE, "🐛");

    private final String key;
    private final Group group;
    private final String icon;

    AnimalType(String key, Group group, String icon) {
        this.key = key;
        this.group = group;
        this.icon = icon;
    }

    public String getKey() {
        return key;
    }

    public Group getGroup() {
        return group;
    }

    public String getIcon() {
        return icon;
    }
}