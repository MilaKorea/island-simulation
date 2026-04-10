package com.island.island;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Island {

    private final int width;
    private final int height;
    private final Cell[][] cells;
    private final Random random = new Random();

    public Island(int width, int height) {
        this.width = width;
        this.height = height;
        this.cells = new Cell[width][height];
        initCells();
    }

    private void initCells() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                cells[x][y] = new Cell(x, y);
            }
        }
    }

    public Cell getCell(int x, int y) {
        return cells[x][y];
    }

    public Cell getRandomCell() {
        int x = random.nextInt(width);
        int y = random.nextInt(height);
        return cells[x][y];
    }

    public int getTotalCells() {
        return width * height;
    }

    public List<Cell> getAllCells() {
        List<Cell> result = new ArrayList<>();

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                result.add(cells[x][y]);
            }
        }

        return result;
    }

    public List<Cell> getAvailableCells (Cell currentCell, int speed, boolean includeCurrentCell) {
        List<Cell> result = new ArrayList<>();

        int x = currentCell.getX();
        int y = currentCell.getY();

        if (includeCurrentCell) {
            result.add(currentCell);
        }

        for (int step = 1; step <= speed; step++) {
            addIfExists(result, x - step, y);
            addIfExists(result, x + step, y);
            addIfExists(result, x, y - step);
            addIfExists(result, x, y + step);
        }

        return result;
    }

    private void addIfExists(List<Cell> result, int x, int y) {
        if (x >= 0 && x < width && y >= 0 && y < height) {
            result.add(cells[x][y]);
        }
    }
}
