package com.example.weather.service;

import com.example.weather.entity.Itinerary;
import com.example.weather.entity.WeatherItinerary;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class WeatherApiServiceTest {

    private LocalDate localDate = LocalDate.now();
    private List<String> cityList = Arrays.asList("London", "Moscow", "Tokyo");

    @Autowired
    WeatherApiService weatherApiService;

    @Autowired
    @Qualifier("weatherServiceImpl")
    CrudService weatherService;

    @Test
    void validateWeatherByCityAndDateApi() {
        List<Set<WeatherItinerary>> lists = cityList.stream()
                .map(p -> weatherApiService.getWeatherByCityAndDate(p, localDate))
                .collect(Collectors.toList());

        Assert.assertEquals(lists.size(), 3);
        for (int i = 0; i < lists.size(); i++) {
            final int n = i;
            lists.get(n).forEach(p ->
                    assertEquals(cityList.get(n), p.getCityName()));
        }
    }

    @Test
    void validateSaveDatabase() {
        Itinerary itinerary = new Itinerary("test");
        getWeatherItinerary().stream().limit(1).forEach(p -> {
            p.setItinerary(itinerary);
            weatherService.save(p);});

        List<WeatherItinerary> weatherItinerary = weatherService.findAll();
        weatherItinerary.forEach(p -> {
            assertEquals("test", p.getItinerary().getName());
            assertEquals("London", p.getCityName());
            assertEquals("GB", p.getCountry());
        });
    }

    private Set<WeatherItinerary> getWeatherItinerary() {
        Set<WeatherItinerary> itinerarySet = weatherApiService.getWeatherByCityAndDate(cityList.get(0), localDate);
        return itinerarySet;
    }
}
