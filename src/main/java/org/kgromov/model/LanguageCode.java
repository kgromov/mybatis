package org.kgromov.model;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class LanguageCode implements Serializable {

    private String code;
    private String language;
}
