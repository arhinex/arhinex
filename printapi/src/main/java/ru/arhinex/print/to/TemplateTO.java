package ru.arhinex.print.to;

import lombok.Getter;
import lombok.Setter;
import ru.arhinex.baseapi.to.DateCreatedTO;

@Getter
@Setter
public class TemplateTO extends DateCreatedTO {
    private String name;
    private TemplateType type;
}
