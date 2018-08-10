package ru.arhinex.staffin.clients;

import lombok.Getter;
import org.springframework.stereotype.Component;
import ru.arhinex.print.service.ITemplateService;
import ru.arhinex.print.service.TemplateService;

import javax.annotation.PostConstruct;

@Component
public class TemplateServiceClient {
    @Getter
    private ITemplateService service;

    @PostConstruct
    protected void init() {
        //TODO set connection url from settings
        service = new TemplateService("");
    }
}
