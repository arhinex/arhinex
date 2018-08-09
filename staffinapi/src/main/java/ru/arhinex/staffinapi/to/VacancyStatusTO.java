package ru.arhinex.staffinapi.to;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import ru.arhinex.baseapi.to.CodeNamedTO;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class VacancyStatusTO extends CodeNamedTO {
}
