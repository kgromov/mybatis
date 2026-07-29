package org.kgromov.mappers;

import org.apache.ibatis.annotations.*;
import org.kgromov.model.CountryLanguage;

import java.util.List;

@Mapper
public interface CountryLanguageMapper {

    @Select("SELECT * FROM countrylanguage ORDER BY CountryCode")
    @Results({
            @Result(id = true, property = "code", column = "CountryCode"),
            @Result(id = true, property = "language", column = "Language"),
            @Result(property = "official", column = "IsOfficial"),
            @Result(property = "usage", column = "Percentage")
    })
    List<CountryLanguage> findAll();

    @Select("SELECT * FROM countrylanguage WHERE countrycode = #{CountryCode} AND language = #{Language}")
    @Results({
            @Result(id = true, property = "code", column = "CountryCode"),
            @Result(id = true, property = "language", column = "Language"),
            @Result(property = "official", column = "IsOfficial"),
            @Result(property = "usage", column = "Percentage")
    })
    CountryLanguage findById(@Param("CountryCode") String code,
                             @Param("Language") String language);

    @Insert("INSERT INTO countrylanguage (CountryCode, Language, IsOfficial, Percentage) VALUES (#{code}, #{language}, #{official}, #{usage})")
    int insert(CountryLanguage t);

    @Update("UPDATE countrylanguage SET Percentage = #{usage}, IsOfficial = #{official} WHERE CountryCode = #{code} AND Language = #{language}")
    int update(CountryLanguage t);

    @Delete("DELETE FROM countrylanguage WHERE CountryCode = #{code} AND Language = #{language}")
    int delete(@Param("code") String code,
               @Param("language") String language);
}
