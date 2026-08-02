package org.kgromov.service;

import lombok.RequiredArgsConstructor;
import org.kgromov.mappers.CityMapper;
import org.kgromov.model.City;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CityService {
    private final CityMapper cityMapper;

    @Transactional(readOnly = true)
    public List<City> getCites() {
        return cityMapper.findAll();
    }

    @Transactional(readOnly = true)
    public City getCity(Long id) {
        return cityMapper.findById(id);
    }

    @Transactional(readOnly = true)
    public List<City> getCityByCountryCode(String countryCode) {
        return cityMapper.findAllByCountryCode(countryCode);
    }

    @Transactional
    public void createCity(City city) {
        cityMapper.insert(city);
    }

    @Transactional
    public void updateCity(City city) {
        cityMapper.update(city);
    }

    @Transactional
    public void deleteCity(Long id) {
        cityMapper.delete(id);
    }
}
