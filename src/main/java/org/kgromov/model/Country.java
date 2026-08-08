package org.kgromov.model;

import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table("country")
public class Country {
    @Id(keyType = KeyType.Auto)
    @Column("Code")
    public String code;
    @Column("Name")
    public String name;
    @Column("SurfaceArea")
    public BigDecimal surfaceArea;
    @Column("Population")
    public Integer population;
    @Column("Capital")
    public String capital;
    @Column("GovernmentForm")
    public String governmentForm;
    @Column("Continent")
    public String continent;
    @Column("Region")
    public String region;
    @Column("IndepYear")
    public Short indepYear;
    @Column("LifeExpectancy")
    public BigDecimal lifeExpectancy;
    @Column("GNP")
    public BigDecimal gnp;
    @Column("GNPOld")
    public BigDecimal gnpOld;
    @Column("LocalName")
    public String localName;
    @Column("HeadOfState")
    public String headOfState;
    @Column("Code2")
    public String code2;

}
