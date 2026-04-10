package com.island.service;

import com.island.config.SimulationConfig;
import com.island.factory.PlantFactory;
import com.island.island.Cell;
import com.island.island.Island;
import com.island.model.plants.Plant;
import com.island.model.plants.PlantType;

public class PlantLifeService {

    private final PlantFactory plantFactory;
    private final int plantGrowthPerStep;

    public PlantLifeService(SimulationConfig config, PlantFactory plantFactory) {
        this.plantFactory = plantFactory;
        this.plantGrowthPerStep = config.getPlantGrowthPerStep();
    }

    public void growPlants(Island island) {
        for (Cell cell : island.getAllCells()) {
            growGrassInCell(cell);
        }
    }

    private void growGrassInCell(Cell cell) {
        for (int i = 0; i < plantGrowthPerStep; i++) {
            Plant grass = plantFactory.create(PlantType.GRASS);

            if (!cell.hasSpaceFor(grass)) {
                break;
            }

            cell.addPlant(grass);
        }
    }
}
