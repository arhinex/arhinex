package ru.arhinex.emailsender.service;

import ru.arhinex.emailsender.service.stub.MailServiceStub;
import ru.arhinex.emailsender.to.MailTO;
import ru.arhinex.service.AbstractService;

public class MailService extends AbstractService<MailServiceStub> implements IMailService {

    @Override
    protected MailServiceStub createStab() {
        return new MailServiceStub();
    }

    @Override
    public void sendMail(MailTO mail) {
        if (isMock()) {
            getStab().sendMail(mail);
            return;
        }
        getRestTemplate().put(getConnectionUrl() + "/sendmail", mail);
    }
}
