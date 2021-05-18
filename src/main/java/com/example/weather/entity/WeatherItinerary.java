package com.example.weather.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Setter
@Getter
@Entity
@Table(name = "t_weather_details")
public class WeatherItinerary {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "city_name")
    private String cityName;

    @Column(name = "country_name")
    private String country;

    @Column(name = "description")
    private String description;

    @Column(name = "icon")
    private String icon;

    @Column(name = "temperature")
    private double temperature;

    @Column(name = "date_time")
    private LocalDateTime dateTime;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "itinerary_id")
    private Itinerary itinerary;

    public WeatherItinerary() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WeatherItinerary that = (WeatherItinerary) o;
        return cityName.equals(that.cityName) &&
                country.equals(that.country) &&
                dateTime.equals(that.dateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cityName, country, dateTime);
    }
}
