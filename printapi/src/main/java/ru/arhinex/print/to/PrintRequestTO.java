package ru.arhinex.print.to;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class PrintRequestTO {
    private Map<String, Object> params;
}
