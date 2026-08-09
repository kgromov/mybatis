package org.kgromov.model;

import com.mybatisflex.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table("city")
public class City {
    @Id(keyType = KeyType.Auto)
    @Column("ID")
    public Long id;
    @Column("Name")
    public String name;
    @Column("District")
    public String district;
    @Column("Population")
    public Long population;
    // This seems to be a workaround for MyBatis Flex - redundant field - should be either countryCode or country
    @Column("CountryCode")
    public String countryCode;          // <-- add this: holds the actual FK value

    @RelationManyToOne(
            targetTable = "country",
            selfField = "countryCode",  // <-- point at the FK field, not "country"
            targetField = "code"        // matches Country.code (Java field name, not "Code")
    )
    public Country country;
}
