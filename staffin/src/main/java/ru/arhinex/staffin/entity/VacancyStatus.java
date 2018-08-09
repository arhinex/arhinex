package ru.arhinex.staffin.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import ru.arhinex.baseentity.entity.CodeNamedEntity;

@Getter
@Setter
@Document
public class VacancyStatus extends CodeNamedEntity {
}
