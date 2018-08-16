package ru.arhinex.security.to;

import lombok.Getter;
import lombok.Setter;
import ru.arhinex.baseapi.to.DateCreatedTO;

@Getter
@Setter
public class UserTO extends DateCreatedTO {
    private String login;
    private String authToken;
    private AuthType authType;
}
