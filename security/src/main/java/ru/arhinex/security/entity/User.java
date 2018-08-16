package ru.arhinex.security.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import ru.arhinex.baseentity.entity.DateCreatedEntity;
import ru.arhinex.security.to.AuthType;

@Getter
@Setter
@Document
public class User extends DateCreatedEntity {
    private String login;
    private String authToken;
    private AuthType authType;
}
