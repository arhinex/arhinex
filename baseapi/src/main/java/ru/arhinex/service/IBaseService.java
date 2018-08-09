package ru.arhinex.service;

import ru.arhinex.baseapi.to.DateCreatedTO;

import java.util.List;
import java.util.UUID;

public interface IBaseService<T extends DateCreatedTO> {
    List<T> finadAll();

    T findById(UUID id);
}
