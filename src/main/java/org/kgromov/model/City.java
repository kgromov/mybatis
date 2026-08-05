package org.kgromov.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class City {
    public Long id;              // ID
    public String name;         // Name
    public String district;     // District
    public Long population;     // Population
    public Country country;
}
