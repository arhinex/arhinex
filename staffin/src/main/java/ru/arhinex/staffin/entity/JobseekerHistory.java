package ru.arhinex.staffin.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import ru.arhinex.baseentity.entity.DateCreatedEntity;

import java.util.List;

@Getter
@Setter
@Document
public class JobseekerHistory extends DateCreatedEntity {
    @DBRef
    private Vacancy vacancy;
    @DBRef
    private Jobseeker jobseeker;
    @DBRef
    private JobseekerVacancyStatus status;
    private List<Comment> comments;
}
