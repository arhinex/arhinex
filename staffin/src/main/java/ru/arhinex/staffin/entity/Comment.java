package ru.arhinex.staffin.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import ru.arhinex.baseentity.entity.DateCreatedEntity;

@Setter @Getter
@Document
public class Comment extends DateCreatedEntity {
    private String comment;
}
