package org.kgromov.mappers;

import org.apache.ibatis.annotations.Mapper;
import org.kgromov.model.Country;

@Mapper
public interface CountryMapper extends BaseMapper<Country, String> {
}
