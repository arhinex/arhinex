package ru.arhinex.service.stub;

import ru.arhinex.baseapi.to.DateCreatedTO;
import ru.arhinex.service.IBaseService;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class BaseServiceStub<T extends DateCreatedTO> implements IBaseService<T> {
    @Override
    public List<T> finadAll() {
        return Collections.EMPTY_LIST;
    }

    @Override
    public T findById(UUID id) {
        return null;
    }
}
