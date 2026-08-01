package org.kgromov.mappers;

import org.apache.ibatis.annotations.Mapper;
import org.kgromov.model.CountryLanguage;
import org.kgromov.model.LanguageCode;

@Mapper
public interface CountryLanguageMapper extends BaseMapper<CountryLanguage, LanguageCode> {
}
