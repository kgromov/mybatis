package org.kgromov.model.projections;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CityCountryView {
    private String name;
    private Long population;
    private String countryName;
}
