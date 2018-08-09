package ru.arhinex.emailsender.to;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class MailTO {
    private List<String> from;
    private String to;
    private List<String> copy;
    private String messageBody;
}
