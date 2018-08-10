package ru.arhinex.print.service;

import ru.arhinex.print.to.PrintResultTO;

import java.util.UUID;

public interface ITemplateService {
    PrintResultTO make(UUID templateId);
}
