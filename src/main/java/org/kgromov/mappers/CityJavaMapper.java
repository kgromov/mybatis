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

    @Select(BASE_SELECT + " WHERE c.Name = #{name}")
    @ResultMap("org.kgromov.mappers.CityMapper.CityMapperMap")
    City findByName(String name);

    @Select("""
                SELECT c.*, co.Name as country_name
                FROM city c
                JOIN country co ON c.CountryCode = co.Code
                WHERE c.CountryCode = #{countryCode}
                ORDER BY c.Name
            """)
    @ResultMap("org.kgromov.mappers.CityMapper.CityMapperMap")
    List<City> findAllByCountryCode(@Param("countryCode") String countryCode);

    // for sequence e.g. normally for Postgres, Oracle
//    @SelectKey(statement = "CALL IDENTITY()", before = false, keyColumn = "ID", keyProperty = "id", resultType = Long.class)
    @Options(useGeneratedKeys = true, keyColumn = "ID", keyProperty = "id")
    @Insert("INSERT INTO city (Name, CountryCode, District, Population) VALUES (#{name}, #{country.code}, #{district}, #{population})")
    void insert(City city);


    @Update("UPDATE city SET Name = #{name}, CountryCode = #{country.code}, District = #{district}, Population = #{population} WHERE ID = #{id}")
    void update(City city);

    @Delete("DELETE FROM city WHERE ID = #{id}")
    void delete(@Param("id") Long id);
}