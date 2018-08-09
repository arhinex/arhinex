package ru.arhinex.staffin.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import ru.arhinex.baseapi.to.Identifiable;
import ru.arhinex.baseentity.entity.NamedEntity;

import java.util.Date;

@Getter
@Setter
@Document
public class Vacancy extends NamedEntity {
    private String description;
    private Identifiable graid;
    private Date openDate;
    private Date closeDate;
    @DBRef
    private VacancyStatus status;
}
