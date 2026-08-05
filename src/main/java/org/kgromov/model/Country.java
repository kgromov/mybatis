package org.kgromov.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Country {
    public String code;
    public String name;
    public BigDecimal surfaceArea;
    public Integer population;
    public String capital;
    public String governmentForm;
    public String continent;
    public String region;
    public Short indepYear;
    public BigDecimal lifeExpectancy;
    public BigDecimal gnp;
    public BigDecimal gnpOld;
    public String localName;
    public String headOfState;
    public String code2;

}
