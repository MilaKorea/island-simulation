package com.island;

import com.island.config.ConfigLoader;
import com.island.config.SimulationConfig;
import com.island.engine.ConsoleInputHandler;
import com.island.engine.IslandInitializer;
import com.island.engine.StartParameters;
import com.island.factory.AnimalFactory;
import com.island.factory.PlantFactory;
import com.island.island.Island;

public class Main {

    public static void main(String[] args) {

        // 1. Загружаем конфиг
        SimulationConfig config = ConfigLoader.load("entity-config.yaml");

        System.out.println("=== CONFIG LOADED ===");
        System.out.println("Animals: " + config.getAnimals().keySet());
        System.out.println("Plants: " + config.getPlants().keySet());

        // 2. Читаем ввод пользователя
        ConsoleInputHandler inputHandler = new ConsoleInputHandler();
        StartParameters params = inputHandler.readStartParameters(config);

        // 3. Создаём остров
        Island island = new Island(params.getWidth(), params.getHeight());

        System.out.println();
        System.out.println("=== ISLAND CREATED ===");
        System.out.println("Size: " + params.getWidth() + " x " + params.getHeight());
        System.out.println("Total cells: " + island.getTotalCells());

        // 4. Создаём фабрики
        AnimalFactory animalFactory = new AnimalFactory(config);
        PlantFactory plantFactory = new PlantFactory(config);

        // 5. Заселяем остров
        IslandInitializer initializer = new IslandInitializer(animalFactory, plantFactory);
        initializer.populate(island, params);

        // 6. Вывод результата
        System.out.println();
        System.out.println("=== ISLAND POPULATED ===");

        // случайная клетка
        System.out.println("Random cell:");
        System.out.println(island.getRandomCell());

        // конкретная клетка
        System.out.println();
        System.out.println("Cell (0,0):");
        System.out.println(island.getCell(0, 0));

        // можно добавить мини-статистику
        System.out.println();
        System.out.println("=== DONE ===");
    }
}