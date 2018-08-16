package ru.arhinex.security.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import ru.arhinex.baseentity.entity.CodeNamedEntity;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Document
public class Role extends CodeNamedEntity {
    private List<String> functions = new ArrayList<>();
}
