package ru.arhinex.staffinapi.to;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import ru.arhinex.baseapi.to.Identifiable;
import ru.arhinex.baseapi.to.NamedTO;

import java.util.Date;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class VacancyTO extends NamedTO {
    private String description;
    private Identifiable graid;
    private Date openDate;
    private Date closeDate;
    private VacancyStatusTO status;
}
