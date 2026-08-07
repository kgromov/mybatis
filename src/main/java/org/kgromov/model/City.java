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
    @RelationManyToOne(
            targetTable = "country",
            selfField = "CountryCode",
            targetField = "Code"
    )
    public Country country;
}
