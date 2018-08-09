package ru.arhinex.baseapi.to;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode
public class Identifiable {
    private UUID id;
}
