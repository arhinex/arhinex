package ru.arhinex.print.service;

import ru.arhinex.print.service.stub.TemplateServiceStub;
import ru.arhinex.print.to.PrintRequestTO;
import ru.arhinex.print.to.PrintResultTO;
import ru.arhinex.print.to.TemplateTO;
import ru.arhinex.service.BaseService;

import java.util.UUID;

public class TemplateService extends BaseService<TemplateTO, TemplateServiceStub> implements ITemplateService {

    public TemplateService(String connectionUrl) {
        super(connectionUrl);
    }

    @Override
    protected TemplateServiceStub createStab() {
        return new TemplateServiceStub();
    }

    @Override
    public TemplateTO findById(UUID id) {
        if (isMock()) return getStab().findById(id);
        return getRestTemplate().getForObject(getConnectionUrl() + "/" + id, TemplateTO.class);
    }

    @Override
    public PrintResultTO make(UUID templateId, PrintRequestTO resultTO) {
        if (isMock()) return getStab().make(templateId, resultTO);
        return getRestTemplate().postForObject(getConnectionUrl() + "/" + templateId, resultTO, PrintResultTO.class);
    }
}
