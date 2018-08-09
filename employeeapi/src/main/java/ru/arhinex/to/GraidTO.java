package ru.arhinex.to;

import lombok.Getter;
import lombok.Setter;
import ru.arhinex.baseapi.to.NamedTO;

@Getter
@Setter
public class GraidTO extends NamedTO {
    private Integer minPaySum;
    private Integer maxPaySum;
}
