package ru.arhinex.employee.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import ru.arhinex.baseentity.entity.NamedEntity;

@Getter
@Setter
@Document
public class Graid extends NamedEntity {
    private Integer minPaySum;
    private Integer maxPaySum;
}
