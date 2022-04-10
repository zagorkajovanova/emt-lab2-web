package mk.ukim.finki.emt.lab2application.service;

import mk.ukim.finki.emt.lab2application.model.Country;

import java.util.List;

public interface CountryService {
    List<Country> findAll();
    Country create(String name, String continent);
}
