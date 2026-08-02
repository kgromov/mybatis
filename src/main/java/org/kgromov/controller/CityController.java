package org.kgromov.controller;

import lombok.RequiredArgsConstructor;
import org.kgromov.mappers.CityMapper;
import org.kgromov.model.City;
import org.kgromov.service.CityService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.Objects.nonNull;

@RequiredArgsConstructor
@RequestMapping("/api/cities")
@RestController
public class CityController {
    private final CityMapper cityMapper;
    private  final CityService cityService;

    @GetMapping
    public List<City> getCities(@RequestParam(required = false) String countryCode) {
        return nonNull(countryCode) ? cityService.getCityByCountryCode(countryCode) : cityService.getCites();
    }

    @GetMapping("/{id}")
    public City getCity(@PathVariable Long id) {
        return cityService.getCity(id);
    }
}
