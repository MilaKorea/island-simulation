package com.island.simulation;

import com.island.island.Cell;
import com.island.model.animals.Animal;

public class AnimalMove {
    private final Animal animal;
    private final Cell from;
    private final Cell to;

    public AnimalMove(Animal animal, Cell from, Cell to) {
        this.animal = animal;
        this.from = from;
        this.to = to;
    }

    public Animal getAnimal() {
        return animal;
    }

    public Cell getFrom() {
        return from;
    }

    public Cell getTo() {
        return to;
    }
}
