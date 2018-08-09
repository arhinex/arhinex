package ru.arhinex.baseapi.to;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @JsonIgnoreProperties(ignoreUnknown = true)
public class CodeNamedTO extends NamedTO {
    private String code;
}
