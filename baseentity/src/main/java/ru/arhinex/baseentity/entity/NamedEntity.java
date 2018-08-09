package ru.arhinex.baseentity.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
abstract public class NamedEntity extends DateCreatedEntity {
    private String name;
}
