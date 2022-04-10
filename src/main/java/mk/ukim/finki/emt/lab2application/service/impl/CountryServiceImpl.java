package mk.ukim.finki.emt.lab2application.service.impl;

import mk.ukim.finki.emt.lab2application.model.Country;
import mk.ukim.finki.emt.lab2application.repository.CountryRepository;
import mk.ukim.finki.emt.lab2application.service.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Country> findAll() {
        return this.countryRepository.findAll();
    }

    @Override
    public Country create(String name, String continent) {
        return this.countryRepository.save(new Country(name,continent));
    }

}
