package org.kgromov.model.projections;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class GroupingWithGroupConcat {
    private String grouping;
    private String concatenation;

    public List<String> splitConcatenation() {
        return this.splitConcatenation(",");
    }

    public List<String> splitConcatenation(String separator) {
        return List.of(concatenation.split(separator));
    }
}
