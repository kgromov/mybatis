package org.kgromov.mappers;

import org.apache.ibatis.annotations.Mapper;
import org.kgromov.model.CountryLanguage;
import org.kgromov.model.LanguageCode;

import java.util.List;

@Mapper
public interface ICountryLanguageMapper extends BaseMapper<CountryLanguage, LanguageCode> {

    List<CountryLanguage> findAll();

    CountryLanguage findById(LanguageCode id);

    int insert(CountryLanguage t);

    int update(CountryLanguage t);

    int delete(LanguageCode id);
}
