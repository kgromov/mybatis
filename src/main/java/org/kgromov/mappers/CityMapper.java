package org.kgromov.mappers;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.kgromov.model.City;

import java.util.List;

@Mapper
public interface CityMapper extends BaseMapper<City, Long> {

    List<City> findAllByCountryCode(@Param("countryCode") String countryCode);
}
