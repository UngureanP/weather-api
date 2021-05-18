package com.example.weather.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WeatherConfiguration {

    @Value("${weather.link}")
    String weatherApiLink;

    @Bean
    public WebClient getApiWeather() {
        return WebClient.create(weatherApiLink);
    }
}
