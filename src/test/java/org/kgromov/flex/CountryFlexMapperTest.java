package org.kgromov.flex;

import com.mybatisflex.core.query.QueryColumn;
import com.mybatisflex.core.query.QueryTable;
import com.mybatisflex.core.query.QueryWrapper;
import org.junit.jupiter.api.Test;
import org.kgromov.mappers.flex.CountryFlexMapper;
import org.kgromov.model.Country;
import org.kgromov.model.projections.GroupingWithCountView;
import org.kgromov.model.projections.GroupingWithGroupConcat;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.mybatisflex.core.query.QueryMethods.count;
import static java.util.stream.Collectors.toMap;
import static org.assertj.core.api.Assertions.assertThat;

class CountryFlexMapperTest extends MyBatisFlexMapperTest {
    @Autowired
    private CountryFlexMapper countryFlexMapper;


    @Test
    void selectOneById_whenCountryCodeExists_thenReturnCountry() {
        // check mapping field - column
//        String codeMetadata = TableInfoFactory.ofEntityClass(Country.class).getColumnByProperty("code");

        Country ukraine = countryFlexMapper.selectOneById("UKR");

        assertThat(ukraine).isNotNull();
        assertThat(ukraine.getCode()).isEqualTo("UKR");
        assertThat(ukraine.getName()).isEqualTo("Ukraine");
    }

    @Test
    void groupBy_whenGroupByContinentAndSelectCount_thenReturnsExpectedCountriesCountByContinent() {
        QueryColumn continent = new QueryColumn("continent");
        QueryColumn countColumn = count(new QueryColumn("code")).as("count");
        QueryWrapper query = QueryWrapper.create()
                .select(continent.as("grouping"), countColumn)
                .from(new QueryTable("country"))
                .groupBy(continent)
                .orderBy(countColumn.desc());

//        List<GroupingWithCountView> grouping = countryFlexMapper.selectListByQueryAs(query, GroupingWithCountView.class);
        List<GroupingWithCountView> grouping = countryFlexMapper.groupByContinentWithCount();
        assertThat(grouping).extracting(GroupingWithCountView::getGrouping)
                .containsExactlyInAnyOrder("Africa", "Asia", "Europe", "North America", "Oceania", "Antarctica", "South America");
        assertThat(grouping.getFirst().getGrouping()).isEqualTo("Africa");
        assertThat(grouping.getLast().getGrouping()).isEqualTo("Antarctica");
    }

    @Test
    void groupBy_whenGroupByContinentAndSelectNames_thenReturnsExpectedCountriesNameByContinent() {
        List<GroupingWithGroupConcat> grouping = countryFlexMapper.groupByContinentWithNames();
        assertThat(grouping).extracting(GroupingWithGroupConcat::getGrouping)
                .containsExactlyInAnyOrder("Africa", "Asia", "Europe", "North America", "Oceania", "Antarctica", "South America");
        assertThat(grouping.getFirst().getGrouping()).isEqualTo("Africa");
        assertThat(grouping.getLast().getGrouping()).isEqualTo("South America");

        var countriesByContinent = grouping.stream().collect(toMap(
                GroupingWithGroupConcat::getGrouping,
                GroupingWithGroupConcat::splitConcatenation)
        );
        assertThat(countriesByContinent.get("Europe")).contains("Ukraine");

    }
}