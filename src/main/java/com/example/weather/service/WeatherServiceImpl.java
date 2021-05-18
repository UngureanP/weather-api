package com.example.weather.service;

import com.example.weather.dao.CrudDAO;
import com.example.weather.entity.Itinerary;
import com.example.weather.entity.WeatherItinerary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WeatherServiceImpl implements CrudService {

    @Autowired
    @Qualifier("weatherDaoImpl")
    CrudDAO crudDAO;

    @Override
    public <WeatherItinerary> void save(WeatherItinerary weatherItinerary) {
        crudDAO.save(weatherItinerary);
    }

    @Override
    public List<WeatherItinerary> findAll() {
        return crudDAO.findAll();
    }

    @Override
    public WeatherItinerary findById(Long id) {
        return crudDAO.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        crudDAO.deleteById(id);
    }
}
