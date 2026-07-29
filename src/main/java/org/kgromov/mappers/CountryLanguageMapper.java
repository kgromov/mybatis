package org.kgromov.mappers;

import org.apache.ibatis.annotations.*;
import org.kgromov.model.CountryLanguage;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface CountryLanguageMapper {

    @Select("SELECT * FROM countrylanguage")
    @ConstructorArgs({
            @Arg(id = true, name = "code", javaType = String.class, column = "CountryCode"),
            @Arg(id = true, name = "language", javaType = String.class, column = "Language"),
            @Arg(name = "official", javaType = Boolean.class, column = "IsOfficial"),
            @Arg(name = "usage", javaType = BigDecimal.class, column = "Percentage")
    })
    List<CountryLanguage> findAll();

    @Select("SELECT * FROM countrylanguage WHERE countrycode = #{CountryCode} AND language = #{Language}")
    @ConstructorArgs({
            @Arg(id = true, name = "code", javaType = String.class, column = "CountryCode"),
            @Arg(id = true, name = "language", javaType = String.class, column = "Language"),
            @Arg(name = "official", javaType = Boolean.class, column = "IsOfficial"),
            @Arg(name = "usage", javaType = BigDecimal.class, column = "Percentage")
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
