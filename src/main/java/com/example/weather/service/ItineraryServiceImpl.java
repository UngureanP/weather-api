package com.example.weather.service;

import com.example.weather.dao.CrudDAO;
import com.example.weather.entity.Itinerary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItineraryServiceImpl implements CrudService {

    @Autowired
    @Qualifier("itineraryDaoImpl")
    CrudDAO crudDAO;

    @Override
    public <Itinerary> void save(Itinerary itinerary) {
        crudDAO.save(itinerary);
    }

    @Override
    public List<Itinerary> findAll() {
        return crudDAO.findAll();
    }

    @Override
    public Itinerary findById(Long id) {
        return crudDAO.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        crudDAO.deleteById(id);
    }
}
