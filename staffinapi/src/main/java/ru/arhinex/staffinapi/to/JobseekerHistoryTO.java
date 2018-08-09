package ru.arhinex.staffinapi.to;

import lombok.Getter;
import lombok.Setter;
import ru.arhinex.baseapi.to.DateCreatedTO;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class JobseekerHistoryTO extends DateCreatedTO {
    private VacancyTO vacancy;
    private JobseekerVacancyStatusTO status;
    private List<CommentTO> comments = new ArrayList<>();
}
