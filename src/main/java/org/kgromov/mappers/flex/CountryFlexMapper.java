package org.kgromov.mappers.flex;

import com.mybatisflex.core.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.kgromov.model.Country;

@Mapper
public interface CountryFlexMapper {

    @Select("SELECT * FROM country WHERE Code = #{code}")
    Country selectOneById(String code);
}
