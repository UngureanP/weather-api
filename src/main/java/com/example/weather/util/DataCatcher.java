package com.example.weather.util;

import com.example.weather.entity.WeatherForecast;
import com.google.common.cache.*;
import org.apache.log4j.Logger;

import java.util.concurrent.TimeUnit;

public class DataCatcher {

    private final static Logger logger = Logger.getLogger(DataCatcher.class);

    public static Cache<String, WeatherForecast> cache = CacheBuilder.newBuilder()
            .removalListener(rl -> logger.info("The record with key: " + rl.getKey() + " is removed"))
            .expireAfterWrite(1, TimeUnit.HOURS)
            .build();
}
