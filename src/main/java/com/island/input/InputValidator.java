package com.island.input;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class InputValidator {

    public static <T> Map<T, Integer> parseAndValidate(
            String input,
            List<T> types,
            Map<T, Integer> limits
    ) {
        String[] parts = input.trim().split("\\s+");

        if (parts.length != types.size()) {
            throw new IllegalArgumentException(
                    "Expected " + types.size() + " numbers, but got " + parts.length
            );
        }

        Map<T, Integer> result = new HashMap<>();

        for (int i = 0; i < types.size(); i++) {
            int value;

            try {
                value = Integer.parseInt(parts[i]);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Only integers are allowed");
            }

            if (value < 0) {
                throw new IllegalArgumentException("Values cannot be negative");
            }

            T type = types.get(i);
            int maxAllowed = limits.get(type);

            if (value > maxAllowed) {
                throw new IllegalArgumentException(
                        type + " exceeds max allowed: " + maxAllowed
                );
            }

            result.put(type, value);
        }

        return result;
    }

    public static class CapacityHelper {

        public static <T> Map<T, Integer> calculateLimits(
                List<T> types,
                int totalCells,
                Function<T, Integer> maxPerCellExtractor
        ) {
            Map<T, Integer> limits = new HashMap<>();

            for (T type : types) {
                int max = totalCells * maxPerCellExtractor.apply(type);
                limits.put(type, max);
            }

            return limits;
        }
    }
}
