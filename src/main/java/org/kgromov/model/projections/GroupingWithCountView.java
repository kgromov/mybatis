package org.kgromov.model.projections;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GroupingWithCountView {
    private String grouping;
    private Long count;
}
