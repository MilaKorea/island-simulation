package com.island.model.plants;

import com.island.model.Group;

public enum PlantType {

    GRASS("grass", Group.PLANT, "🌿");

    private final String key;
    private final Group group;
    private final String icon;

    PlantType(String key, Group group, String icon) {
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
