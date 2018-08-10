package ru.arhinex.emailsender.service.stub;

import ru.arhinex.emailsender.service.IMailService;
import ru.arhinex.emailsender.to.MailTO;

public class MailServiceStub implements IMailService {
    @Override
    public void sendMail(MailTO mailTO) {
        //Do nothing;
    }
}
