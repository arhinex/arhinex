package ru.arhinex.staffin.manager;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.arhinex.baseentity.manager.BaseManager;
import ru.arhinex.baseentity.repository.BaseRepository;
import ru.arhinex.staffin.entity.JobseekerVacancyStatus;
import ru.arhinex.staffin.entity.Vacancy;
import ru.arhinex.staffin.repository.JobseekerStatusRepository;
import ru.arhinex.staffinapi.to.JobseekerVacancyStatusTO;
import ru.arhinex.staffinapi.to.VacancyTO;

import java.util.Optional;

@RestController
@RequestMapping(value = "/jobseekerstatus")
public class JobseekerStatusManager extends BaseManager<JobseekerVacancyStatus, JobseekerVacancyStatusTO> {
    @Autowired
    private JobseekerStatusRepository repository;

    @Override
    public BaseRepository<JobseekerVacancyStatus> getBaseRepository() {
        return repository;
    }

    public JobseekerVacancyStatusTO ensureDefaultStartStatus(VacancyTO vacancyTO) {
        JobseekerVacancyStatusTO statusTO = initDateCreated(new JobseekerVacancyStatusTO());
        statusTO.setName("DEFAULT");
        statusTO.setCode("DEFAULT");
        statusTO.setVacancy(vacancyTO);
        statusTO.setStart(true);
        return map(getBaseRepository().save(mapTO(statusTO)));
    }

    @Override
    public JobseekerVacancyStatusTO save(@NonNull JobseekerVacancyStatusTO value) {
        Assert.notNull(value.getVacancy(), "Value not be null");

        JobseekerVacancyStatusTO statusTO = getStartStatus(value.getVacancy());
        if (value.isStart() && !value.equals(statusTO)) {
            throw new IllegalArgumentException("Default status for vacancy " + value.getVacancy().getId() + " already exist");
        }
        return super.save(value);
    }

    public JobseekerVacancyStatusTO getStartStatus(VacancyTO vacancyTO) {
        return Optional.ofNullable(repository.findOneByIsStartAndVacancy(true, mapTO(vacancyTO, Vacancy.class))).map(this::map).orElseGet(() -> ensureDefaultStartStatus(vacancyTO));
    }

    @Override
    public Class<? extends JobseekerVacancyStatusTO> getTOClass() {
        return JobseekerVacancyStatusTO.class;
    }

    @Override
    public Class<? extends JobseekerVacancyStatus> getEntityClass() {
        return JobseekerVacancyStatus.class;
    }
}
