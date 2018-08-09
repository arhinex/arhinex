package ru.arhinex.print.service;

import ru.arhinex.print.service.stub.TemplateServiceStub;
import ru.arhinex.print.to.TemplateTO;
import ru.arhinex.service.BaseService;

import java.util.UUID;

public class TemplateService extends BaseService<TemplateTO, TemplateServiceStub> implements ITemplateService {

    @Override
    protected TemplateServiceStub createStab() {
        return new TemplateServiceStub();
    }

    @Override
    public TemplateTO findById(UUID id) {
        if (isMock()) return getStab().findById(id);
        return getRestTemplate().getForObject(getConnectionUrl() + "/" + id, TemplateTO.class);
    }
}
