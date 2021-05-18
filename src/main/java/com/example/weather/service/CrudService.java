package com.example.weather.service;

import java.util.List;

public interface CrudService {

    <T> void save(T id);

    <T> List<T> findAll();

    <T> T findById(Long id);

    void deleteById(Long id);
}
