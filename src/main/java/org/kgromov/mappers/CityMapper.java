package org.kgromov.mappers;

import org.apache.ibatis.annotations.Mapper;
import org.kgromov.model.City;

@Mapper
public interface CityMapper extends BaseMapper<City, Long> {
}
