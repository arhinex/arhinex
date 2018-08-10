package ru.arhinex.service;

import org.springframework.util.StringUtils;
import ru.arhinex.baseapi.to.DateCreatedTO;
import ru.arhinex.service.stub.BaseServiceStub;

import java.util.List;

abstract public class BaseService<T extends DateCreatedTO, M extends BaseServiceStub> extends AbstractService<M> implements IBaseService<T>{

    public BaseService(String connectionUrl) {
        super(connectionUrl);
    }

    @Override
    public List<T> finadAll() {
        if (isMock()) return getStab().finadAll();
        return getRestTemplate().getForObject(getConnectionUrl(), List.class);
    }

    public boolean isMock(){
        return StringUtils.isEmpty(getConnectionUrl());
    }
}
