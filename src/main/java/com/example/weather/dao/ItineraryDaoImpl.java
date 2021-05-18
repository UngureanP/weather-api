package com.example.weather.dao;

import com.example.weather.entity.Itinerary;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class ItineraryDaoImpl implements CrudDAO {

    @Autowired
    private EntityManager entityManager;

    public <Itinerary> void save(Itinerary itinerary) {
        Session session = entityManager.unwrap(Session.class);
        session.saveOrUpdate(itinerary);
    }

    public List<Itinerary> findAll() {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createQuery("from Itinerary ");
        return query.getResultList();
    }

    public Itinerary findById(Long id) {
        Session session = entityManager.unwrap(Session.class);
        return session.get(Itinerary.class, id);
    }

    public void deleteById(Long id) {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createQuery("delete from Itinerary where id=:itineraryId");
        query.setParameter("itineraryId", id);
        query.executeUpdate();
    }
}
