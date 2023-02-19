package com.solvd.aviacompany.service.impl;

import com.solvd.aviacompany.db.dao.ICountryDAO;
import com.solvd.aviacompany.db.impl.CountryDAOImpl;
import com.solvd.aviacompany.hierarchy.Country;
import com.solvd.aviacompany.service.interfaces.CountryService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class CountryServiceImpl implements CountryService {

    private static final Logger logger = LogManager.getLogger(CountryServiceImpl.class);
    private final ICountryDAO iCountryDAO;

    public CountryServiceImpl() {
        this.iCountryDAO = new CountryDAOImpl();
    }

    public List<Country> getAllCountries() {
        List<Country> countryList = iCountryDAO.read();
        countryList.sort(Comparator.comparingInt(Country::getId));
        return countryList;
    }

    public Optional<Country> getCountryById(int id) {
        return Optional.ofNullable(iCountryDAO.read(id));
    }

    public Optional<Country> getCountryByName(String name) {
        return Optional.ofNullable(iCountryDAO.getCountryByName(name));
    }

    public boolean addCountry(Country country) {
        return iCountryDAO.create(country);
    }

    public Optional<Country> updateCountry(Country country) {
        Country updatedCountry = iCountryDAO.update(country);
        if (updatedCountry == null) {
            return Optional.empty();
        } else {
            return Optional.of(updatedCountry);
        }
    }
}
