package ru.arhinex.print.service.stub;

import ru.arhinex.print.service.ITemplateService;
import ru.arhinex.print.to.PrintRequestTO;
import ru.arhinex.print.to.PrintResultTO;
import ru.arhinex.print.to.TemplateTO;
import ru.arhinex.service.stub.BaseServiceStub;

import java.util.UUID;

public class TemplateServiceStub extends BaseServiceStub<TemplateTO> implements ITemplateService {

    @Override
    public PrintResultTO make(UUID templateId, PrintRequestTO resultTO) {
        return null;
    }
}
