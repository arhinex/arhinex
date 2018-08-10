package ru.arhinex.staffin.clients;

import lombok.Getter;
import org.springframework.stereotype.Component;
import ru.arhinex.emailsender.service.MailService;
import ru.arhinex.print.service.TemplateService;

import javax.annotation.PostConstruct;

@Component
public class MailSenderServiceClient {
    @Getter
    private MailService service;

    @PostConstruct
    protected void init() {
        //TODO set connection url from settings
        service = (MailService) TemplateService.builder().connectionUrl("").build();
    }
}
