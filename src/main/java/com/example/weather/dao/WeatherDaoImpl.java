package com.example.weather.dao;

import com.example.weather.entity.WeatherItinerary;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class WeatherDaoImpl implements CrudDAO {

    @Autowired
    private EntityManager entityManager;

    public <WeatherItinerary> void save(WeatherItinerary itinerary) {
        Session session = entityManager.unwrap(Session.class);
        session.saveOrUpdate(itinerary);
    }

    public List<WeatherItinerary> findAll() {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createQuery("from WeatherItinerary ");
        return query.getResultList();
    }

    public WeatherItinerary findById(Long id) {
        Session session = entityManager.unwrap(Session.class);
        return session.get(WeatherItinerary.class, id);
    }

    public void deleteById(Long id) {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createQuery("delete from WeatherItinerary where id=:weatherItineraryId");
        query.setParameter("weatherItineraryId", id);
        query.executeUpdate();
    }
}
