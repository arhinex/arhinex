package ru.arhinex.staffinapi.to;

import lombok.Getter;
import lombok.Setter;
import ru.arhinex.baseapi.to.DateCreatedTO;
import ru.arhinex.baseapi.to.NamedTO;

import java.util.*;

@Getter
@Setter
public class JobseekerTO extends NamedTO {
    private String fio;
    private String resume;
    private Date lastResumeUpdate;
    private List<JobseekerHistoryTO> histories = new ArrayList<>();

    public Optional<JobseekerHistoryTO> getCurrentHistory() {
        return histories.stream().sorted(Comparator.comparing(DateCreatedTO::getCreated)).findFirst();
    }

    public Optional<JobseekerVacancyStatusTO> getCurrentStatus() {
        return histories.stream().sorted(Comparator.comparing(DateCreatedTO::getCreated)).findFirst().map(el -> el.getStatus());
    }
}
