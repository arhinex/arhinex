package ru.arhinex.staffin.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import ru.arhinex.baseentity.entity.CodeNamedEntity;

@Getter
@Setter
@Document
public class JobseekerVacancyStatus extends CodeNamedEntity {
    @DBRef
    private Vacancy vacancy;
    private boolean isStart;
}
