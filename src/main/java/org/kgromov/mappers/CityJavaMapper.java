package org.kgromov.mappers;

import org.apache.ibatis.annotations.*;
import org.kgromov.model.City;

import java.util.List;

@Mapper
public interface CityJavaMapper {

    String BASE_SELECT =
            """
                SELECT c.*, co.Code, co.Name as country_name
                FROM city c
                JOIN country co ON c.CountryCode = co.Code
            """;

    @Select(BASE_SELECT + " ORDER BY c.Name")
    @ResultMap("org.kgromov.mappers.CityMapper.CityMapperMap")
    List<City> findAll();

    @Select(BASE_SELECT + " WHERE ID = #{id}")
    @ResultMap("org.kgromov.mappers.CityMapper.CityMapperMap")
    City findById(@Param("id") Long id);

    @Select("""
                SELECT c.*, co.Name as country_name
                FROM city c
                JOIN country co ON c.CountryCode = co.Code
                WHERE c.CountryCode = #{countryCode}
                ORDER BY c.Name
            """)
    @ResultMap("org.kgromov.mappers.CityMapper.CityMapperMap")
    List<City> findAllByCountryCode(@Param("countryCode") String countryCode);
}