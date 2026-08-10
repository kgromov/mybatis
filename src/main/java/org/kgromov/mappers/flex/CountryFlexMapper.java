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

@Mapper
public interface CountryFlexMapper extends BaseMapper<Country> {

    default List<GroupingWithCountView> groupByContinentWithCount() {
        QueryColumn continent = new QueryColumn("continent");
        QueryColumn countColumn = count(new QueryColumn("code")).as("count");
        QueryWrapper query = QueryWrapper.create()
                .select(continent.as("grouping"), countColumn)
                .from(new QueryTable("country"))
                .groupBy(continent)
                .orderBy(countColumn.desc());
        return this.selectListByQueryAs(query, GroupingWithCountView.class);
    }

    default List<GroupingWithGroupConcat> groupByContinentWithNames() {
        QueryColumn continent = new QueryColumn("continent");
        QueryColumn countColumn = groupConcat(new QueryColumn("name")).as("concatenation");
        QueryWrapper query = QueryWrapper.create()
                .select(continent.as("grouping"), countColumn)
                .from(new QueryTable("country"))
                .groupBy(continent)
                .orderBy(continent.asc());
        return this.selectListByQueryAs(query, GroupingWithGroupConcat.class);
    }
}

