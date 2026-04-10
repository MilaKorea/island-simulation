package com.island.app;

import com.island.config.ConfigLoader;
import com.island.config.SimulationConfig;
import com.island.factory.AnimalFactory;
import com.island.factory.PlantFactory;
import com.island.input.ConsoleInputHandler;
import com.island.island.Island;
import com.island.service.AnimalLifeService;
import com.island.service.PlantLifeService;
import com.island.simulation.IslandInitializer;
import com.island.simulation.IslandStatistics;
import com.island.simulation.SimulationEngine;
import com.island.simulation.StartParameters;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ApplicationRunner {

    private static final String CONFIG_FILE = "entity-config.yaml";
    private static final int MAX_STEPS = 50;
    private static final int STEP_PERIOD_SECONDS = 1;

    public void run() {
        SimulationConfig config = loadConfig();
        StartParameters startParameters = readStartParameters(config);
        Island island = createIsland(startParameters);

        SimulationEngine engine = createSimulationEngine(config, island, startParameters);
        startSimulation(engine);
    }

    private SimulationConfig loadConfig() {
        return ConfigLoader.load(CONFIG_FILE);
    }

    private StartParameters readStartParameters(SimulationConfig config) {
        ConsoleInputHandler inputHandler = new ConsoleInputHandler();
        return inputHandler.readStartParameters(config);
    }

    private Island createIsland(StartParameters startParameters) {
        return new Island(startParameters.getWidth(), startParameters.getHeight());
    }

    private SimulationEngine createSimulationEngine(
            SimulationConfig config,
            Island island,
            StartParameters startParameters
    ) {
        AnimalFactory animalFactory = new AnimalFactory(config);
        PlantFactory plantFactory = new PlantFactory(config);

        initializeIsland(island, startParameters, animalFactory, plantFactory);

        AnimalLifeService animalLifeService = new AnimalLifeService(config, animalFactory);
        PlantLifeService plantLifeService = new PlantLifeService(config, plantFactory);
        IslandStatistics statistics = new IslandStatistics();

        return new SimulationEngine(
                island,
                statistics,
                animalLifeService,
                plantLifeService
        );
    }

    private void initializeIsland(
            Island island,
            StartParameters startParameters,
            AnimalFactory animalFactory,
            PlantFactory plantFactory
    ) {
        IslandInitializer initializer = new IslandInitializer(animalFactory, plantFactory);
        initializer.populate(island, startParameters);
    }

    private void startSimulation(SimulationEngine engine) {
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        final int[] step = {0};

        scheduler.scheduleAtFixedRate(() -> {
            step[0]++;

            engine.runAnimalStep(step[0]);
            engine.runPlantStep();
            engine.printStatistics();

            if (engine.isFinished(step[0], MAX_STEPS)) {
                System.out.println("\n=== SIMULATION FINISHED ===");
                scheduler.shutdown();
            }
        }, 0, STEP_PERIOD_SECONDS, TimeUnit.SECONDS);
    }
}

