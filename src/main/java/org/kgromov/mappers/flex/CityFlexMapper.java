package org.kgromov.mappers.flex;

import com.mybatisflex.core.BaseMapper;
import com.mybatisflex.core.row.Db;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.kgromov.model.City;
import org.kgromov.model.projections.GroupingWithCountView;

import java.util.List;

@Mapper
public interface CityFlexMapper extends BaseMapper<City> {

    default List<GroupingWithCountView> findAllByNotUniqueByName() {
        return Db.selectListBySql("""
                         SELECT Name, COUNT(*) as count FROM city
                         GROUP BY Name
                         HAVING COUNT(*) > 1
                         ORDER BY COUNT(*) DESC
                        """
                ).stream()
                .map(row -> new GroupingWithCountView(row.getString("NAME"), row.getLong("COUNT")))
                .toList();
    }

    @Select("""
            SELECT name as grouping, COUNT(*) as count
            FROM city
            GROUP BY name
            HAVING COUNT(*) > 1
            ORDER BY COUNT(*) DESC
            LIMIT #{limit}
            """)
    List<GroupingWithCountView> findAllTopNByNotUniqueByName(@Param("limit") long limit);
}
