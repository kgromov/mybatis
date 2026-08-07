package org.kgromov.mappers.flex;

import com.mybatisflex.core.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.kgromov.model.City;

@Mapper
public interface CityFlexMapper extends BaseMapper<City> {
}
