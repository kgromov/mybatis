package org.kgromov.mappers.flex;

import com.mybatisflex.core.BaseMapper;
import com.mybatisflex.core.query.QueryColumn;
import com.mybatisflex.core.query.QueryTable;
import com.mybatisflex.core.query.QueryWrapper;
import org.apache.ibatis.annotations.Mapper;
import org.kgromov.model.Country;
import org.kgromov.model.projections.GroupingWithCountView;
import org.kgromov.model.projections.GroupingWithGroupConcat;

import java.util.List;

import static com.mybatisflex.core.query.QueryMethods.count;
import static com.mybatisflex.core.query.QueryMethods.groupConcat;
import static org.kgromov.model.table.CountryTableDef.COUNTRY;

@Mapper
public interface CountryFlexMapper extends BaseMapper<Country> {

    default List<GroupingWithCountView> groupByContinentWithCount() {
        QueryColumn countColumn = count(COUNTRY.CODE).as("count");
        QueryWrapper query = QueryWrapper.create()
                .select(COUNTRY.CONTINENT.as("grouping"), countColumn)
                .from(new QueryTable("country"))
                .groupBy(COUNTRY.CONTINENT)
                .orderBy(countColumn.desc());
        return this.selectListByQueryAs(query, GroupingWithCountView.class);
    }

    default List<GroupingWithGroupConcat> groupByContinentWithNames() {;
        QueryColumn countColumn = groupConcat(COUNTRY.NAME).as("concatenation");
        QueryWrapper query = QueryWrapper.create()
                .select(COUNTRY.CONTINENT.as("grouping"), countColumn)
                .from(new QueryTable("country"))
                .groupBy(COUNTRY.CONTINENT)
                .orderBy(COUNTRY.CONTINENT.asc());
        return this.selectListByQueryAs(query, GroupingWithGroupConcat.class);
    }
}

