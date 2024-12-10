package com.api;

import static com.api.utils.Logger.getLogger;

import java.util.stream.Stream;

public enum ENV {
    STAGE("stage"),
    PROD("prod");

    private final String value;

    ENV(String value){
        this.value= value;
    }

    public String getValue() {
        return value;
    }

    public static ENV getEnvByValue(String env) {
        ENV environment = Stream.of(ENV.values()).filter(s -> s.getValue().equalsIgnoreCase(env))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Unsupported env " + env));
        getLogger().info(env + " env found and now environment set as "+ environment);
        return environment;
    }
}
