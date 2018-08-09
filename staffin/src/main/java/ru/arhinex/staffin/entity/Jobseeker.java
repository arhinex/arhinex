package ru.arhinex.staffin.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import ru.arhinex.baseentity.entity.DateCreatedEntity;
import ru.arhinex.baseentity.entity.NamedEntity;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Document
public class Jobseeker extends NamedEntity {
    private String fio;
    private String resume;
    private Date lastResumeUpdate;
    @DBRef
    private List<JobseekerHistory> histories = new ArrayList<>();


    public JobseekerVacancyStatus getCurrentStatus() {
        return histories.stream().sorted(Comparator.comparing(DateCreatedEntity::getCreated)).findFirst().map(el -> el.getStatus()).orElse(null);
    }
}
