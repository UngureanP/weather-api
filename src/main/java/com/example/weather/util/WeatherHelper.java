package com.example.weather.util;

import com.example.weather.entity.WeatherItinerary;
import com.example.weather.entity.api.WeatherDetails;
import com.example.weather.entity.WeatherForecast;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class WeatherHelper {

    private final static LocalTime startTime = LocalTime.of(11, 59);
    private final static LocalTime endTime = LocalTime.of(18, 1);

    public static Set<WeatherItinerary> getWeatherByDate(final WeatherForecast weatherForecast, LocalDate date) {
        List<WeatherDetails> details = weatherForecast.getList().stream()
                .filter(p -> p.getDt_txt().toLocalDate().equals(date))
                .collect(Collectors.toList());
        Set<WeatherItinerary> itineraryList = getWeatherItineraryFromWeatherForecast(new WeatherForecast(weatherForecast.getCity(), details));
        return itineraryList;
    }

    public static WeatherForecast filterWeatherByTime(WeatherForecast weatherForecast) {
        List<WeatherDetails> details = weatherForecast
                .getList()
                .stream()
                .filter(p -> p.getDt_txt().toLocalTime().isAfter(startTime)
                        && p.getDt_txt().toLocalTime().isBefore(endTime) )
                .collect(Collectors.toList());

        weatherForecast.setList(details);
        return weatherForecast;
    }

    private static Set<WeatherItinerary> getWeatherItineraryFromWeatherForecast(WeatherForecast weatherForecast) {
        Set<WeatherItinerary> itineraryList = new HashSet<>();
        for (WeatherDetails details : weatherForecast.getList()) {
            WeatherItinerary weatherItinerary = new WeatherItinerary();
            weatherItinerary.setCityName(weatherForecast.getCity().getName());
            weatherItinerary.setCountry(weatherForecast.getCity().getCountry());
            weatherItinerary.setDescription(details.getWeather().get(0).getDescription());
            weatherItinerary.setIcon(details.getWeather().get(0).getIcon());
            weatherItinerary.setTemperature(details.getMain().getTemp());
            weatherItinerary.setDateTime(details.getDt_txt());

            itineraryList.add(weatherItinerary);
        }
        return itineraryList;
    }
}
