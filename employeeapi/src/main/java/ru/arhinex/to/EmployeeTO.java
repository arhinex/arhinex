package ru.arhinex.to;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import ru.arhinex.baseapi.to.DateCreatedTO;
import ru.arhinex.baseapi.to.RefTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class EmployeeTO extends DateCreatedTO {
    private String fio;
    private Date birthDate;
    private Date startWorkDate;
    private Date endWorkDate;
    private List<RefTO> competentions = new ArrayList<>();
    private List<RefTO> paymentStates = new ArrayList<>();
}
