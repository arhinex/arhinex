package ru.arhinex.staffin.clients;

import lombok.Getter;
import org.springframework.stereotype.Component;
import ru.arhinex.emailsender.service.IMailService;
import ru.arhinex.emailsender.service.MailService;

import javax.annotation.PostConstruct;

@Component
public class MailSenderServiceClient {
    @Getter
    private IMailService service;

    @PostConstruct
    protected void init() {
        //TODO set connection url from settings
        service = new MailService("");
    }
}
