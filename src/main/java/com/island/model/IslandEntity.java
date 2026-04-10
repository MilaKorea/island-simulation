package com.island.model;

public interface IslandEntity<T> {
    boolean isAlive();
    T getType();
    int getMaxPerCell();
}
