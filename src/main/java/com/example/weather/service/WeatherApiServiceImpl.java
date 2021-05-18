package com.example.weather.service;

import com.example.weather.entity.WeatherForecast;

import static com.example.weather.util.DataCatcher.*;

import com.example.weather.entity.WeatherItinerary;
import com.example.weather.util.WeatherHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDate;
import java.util.Set;

@Service
public class WeatherApiServiceImpl implements WeatherApiService {

    private final WebClient webClient;

    @Value("${weather.city}")
    String cityDestination;

    @Value("${weather.key}")
    String weatherKey;

    @Value("${weather.unit}")
    String weatherUnit;

    @Autowired
    public WeatherApiServiceImpl(WebClient webClient) {
        this.webClient = webClient;
    }

    public Set<WeatherItinerary> getWeatherByCityAndDate(String cityName, LocalDate date) {
        WeatherForecast weatherForecast = cache.getIfPresent(cityName);
        if (weatherForecast == null) {
            weatherForecast = webClient.get()
                    .uri(weatherUnit + "&" + cityDestination + cityName + "&" + weatherKey)
                    .retrieve()
                    .bodyToMono(WeatherForecast.class)
                    .block();
            WeatherHelper.filterWeatherByTime(weatherForecast);
            cache.put(weatherForecast.getCity().getName(), weatherForecast);
        }
        Set<WeatherItinerary> weatherByDate = WeatherHelper.getWeatherByDate(weatherForecast, date);
        return weatherByDate;
    }
}
