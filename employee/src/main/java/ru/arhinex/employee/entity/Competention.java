package ru.arhinex.employee.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import ru.arhinex.baseentity.entity.CodeNamedEntity;

@Getter @Setter
@Document
public class Competention extends CodeNamedEntity {
}
