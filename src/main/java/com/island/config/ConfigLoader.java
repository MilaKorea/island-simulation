
package com.island.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.InputStream;

public class ConfigLoader {

    public static SimulationConfig load(String resourceName) {
        try (InputStream is = ConfigLoader.class
                .getClassLoader()
                .getResourceAsStream(resourceName)) {

            if (is == null) {
                throw new IllegalArgumentException("Config not found: " + resourceName);
            }

            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            return mapper.readValue(is, SimulationConfig.class);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

