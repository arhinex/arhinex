package ru.arhinex.print.to;

import lombok.Getter;
import lombok.Setter;
import ru.arhinex.baseapi.to.DateCreatedTO;

@Getter
@Setter
public class PrintResultTO extends DateCreatedTO {
    private String name;
    private byte[] body;
}
