package com.example.weather.entity;

import com.example.weather.entity.api.City;
import com.example.weather.entity.api.WeatherDetails;
import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class WeatherForecast {

    private City city;
    private List<WeatherDetails> list;
}
