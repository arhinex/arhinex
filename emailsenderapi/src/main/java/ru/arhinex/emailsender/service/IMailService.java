package ru.arhinex.emailsender.service;

import ru.arhinex.emailsender.to.MailTO;

public interface IMailService {
    void sendMail(MailTO mail);
}
