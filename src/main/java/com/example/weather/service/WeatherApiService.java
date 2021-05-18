package com.example.weather.service;

import com.example.weather.entity.WeatherItinerary;

import java.time.LocalDate;
import java.util.Set;

public interface WeatherApiService {

    Set<WeatherItinerary> getWeatherByCityAndDate(String cityName, LocalDate date);
}
