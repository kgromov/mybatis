package org.kgromov.mappers.java;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.kgromov.model.Country;

import java.util.List;

// mybatis is smart enough to map columns to properties by decapitalize 1st letter
@Mapper
public interface CountryJavaMapper {

    @Select("SELECT * FROM country ORDER BY Code")
    List<Country> findAll();

    @Select("SELECT * FROM country WHERE Code = #{id}")
    Country findById(String id);
}
