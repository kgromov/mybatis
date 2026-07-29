package org.kgromov.mappers;

import org.apache.ibatis.annotations.*;
import org.kgromov.model.CountryLanguage;

import java.util.List;

@Mapper
public interface CountryLanguageMapper {

    @Select("SELECT * FROM countrylanguage")
    @Results({
            @Result(id = true, property = "CountryCode", column = "CountryCode"),
            @Result(id = true, property = "Language", column = "Language"),
            @Result(property = "IsOfficial", column = "IsOfficial"),
            @Result(property = "Percentage", column = "Percentage")
    })
    List<CountryLanguage> findAll();

    @Select("SELECT * FROM countrylanguage WHERE countrycode = #{CountryCode} AND language = #{Language}")
    @Results({
            @Result(id = true, property = "CountryCode", column = "CountryCode"),
            @Result(id = true, property = "Language", column = "Language"),
            @Result(property = "IsOfficial", column = "IsOfficial"),
            @Result(property = "Percentage", column = "Percentage")
    })
    CountryLanguage findById(@Param("CountryCode") String code,
                             @Param("Language") String language);

    @Insert("INSERT INTO countrylanguage (countrycode, language, usage) VALUES (#{CountryCode}, #{Language}, #{Usage})")
    int insert(CountryLanguage t);

    @Update("UPDATE countrylanguage SET Percentage = #{Percentage}, IsOfficial = #{IsOfficial} WHERE countrycode = #{CountryCode} AND language = #{Language}")
    int update(CountryLanguage t);

    @Delete("DELETE FROM countrylanguage WHERE countrycode = #{CountryCode} AND language = #{Language}")
    int delete(@Param("CountryCode") String code,
               @Param("Language") String language);
}
