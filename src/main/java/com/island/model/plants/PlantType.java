package com.island.model.plants;

import com.island.model.Group;

public enum PlantType {

    GRASS("grass", Group.PLANT);

    private final String key;
    private final Group group;

    PlantType(String key, Group group) {
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

