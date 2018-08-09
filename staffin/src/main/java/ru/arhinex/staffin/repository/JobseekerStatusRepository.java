package ru.arhinex.staffin.repository;

import ru.arhinex.baseentity.repository.BaseRepository;
import ru.arhinex.staffin.entity.JobseekerVacancyStatus;
import ru.arhinex.staffin.entity.Vacancy;

public interface JobseekerStatusRepository extends BaseRepository<JobseekerVacancyStatus> {
    JobseekerVacancyStatus findOneByIsStartAndVacancy(Boolean isStart, Vacancy vacancy);

}
