package com.example.weather.controller;

import com.example.weather.entity.Itinerary;
import com.example.weather.entity.WeatherItinerary;
import com.example.weather.service.CrudService;
import com.example.weather.service.WeatherApiService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;


@Controller
public class WeatherController {

    @Autowired
    private WeatherApiService weatherApiService;

    @Autowired
    @Qualifier("itineraryServiceImpl")
    private CrudService itineraryService;

    @Autowired
    @Qualifier("weatherServiceImpl")
    private CrudService weatherService;

    private final static Logger logger = Logger.getLogger(WeatherController.class);
    private static Set<WeatherItinerary> itinerarySet = new LinkedHashSet<>();
    private static boolean isSelected = false;


    @GetMapping("/")
    public String greeting(Model model) {
        List<Itinerary> itineraries = itineraryService.findAll();
        model.addAttribute("itineraries", itineraries);
        return "main";
    }

    @GetMapping("/main")
    public String getWeather(@RequestParam(required = false, defaultValue = "") String cityName,
                             @RequestParam(required = false)
                             @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                             Model model) {
        try {
            if (isSelected) {
                itinerarySet.clear();
                isSelected = false;
            }
            itinerarySet.addAll(weatherApiService.getWeatherByCityAndDate(cityName, date));
        } catch (WebClientResponseException e) {
            logger.info("no such a city " + cityName);
        }
        model.addAttribute("itinerarySet", itinerarySet);
        return "/";
    }

    @GetMapping("/getItinerary")
    public String getItinerary(@RequestParam(required = false) Long id,
                               Model model) {
        Itinerary itinerary = itineraryService.findById(id);
        model.addAttribute("itinerarySet", itinerary.getWeatherItinerary());
        logger.info("The  Itinerary is get: " + itinerary);
        isSelected = true;
        return "/";
    }

    @GetMapping("/saveWeather")
    public String saveWeatherItinerary(@RequestParam(required = false, defaultValue = "") String itineraryKey) {
        Itinerary itinerary = new Itinerary(itineraryKey);
        itinerarySet.forEach(p -> {
            p.setItinerary(itinerary);
            weatherService.save(p);
            logger.info("The  Weather Itinerary is saved: " + p);
        });
        itinerarySet.clear();
        return "/";
    }
}
