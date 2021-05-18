package com.example.weather.dao;

import java.util.List;

public interface CrudDAO {

    <T> void save(T id);

    <T> List<T> findAll();

    <T> T findById(Long id);

    void deleteById(Long id);
}
