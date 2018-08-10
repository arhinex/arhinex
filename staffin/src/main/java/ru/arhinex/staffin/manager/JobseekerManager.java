package ru.arhinex.staffin.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.arhinex.baseentity.manager.BaseManager;
import ru.arhinex.baseentity.repository.BaseRepository;
import ru.arhinex.emailsender.to.MailTO;
import ru.arhinex.print.to.PrintResultTO;
import ru.arhinex.staffin.clients.MailSenderServiceClient;
import ru.arhinex.staffin.clients.TemplateServiceClient;
import ru.arhinex.staffin.entity.Jobseeker;
import ru.arhinex.staffin.repository.JobseekerRepository;
import ru.arhinex.staffinapi.to.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(value = "/jobseeker")
public class JobseekerManager extends BaseManager<Jobseeker, JobseekerTO> {
    @Autowired
    private JobseekerRepository repository;

    @Autowired
    private JobseekerHistoryManager historyManager;

    @Autowired
    private JobseekerStatusManager jobseekerStatusManager;

    @Autowired
    private VacancyManager vacancyManager;

    @Autowired
    private TemplateServiceClient templateServiceClient;

    @Autowired
    private MailSenderServiceClient mailSenderServiceClient;

    public JobseekerTO changeStatus(UUID id, UUID statusId) {
        Optional<JobseekerTO> jobseeker = Optional.ofNullable(getById(id));
        Optional<JobseekerVacancyStatusTO> statusTO = Optional.ofNullable(jobseekerStatusManager.getById(statusId));
        Optional<JobseekerHistoryTO> historyTO = jobseeker.flatMap(JobseekerTO::getCurrentHistory);
        if (jobseeker.isPresent() && statusTO.isPresent() && historyTO.isPresent()) {
            JobseekerHistoryTO history = historyTO.get();
            history.setStatus(statusTO.get());
            historyManager.save(history);
            return jobseeker.get();
        } else {
            //TODO need true Exception
            throw new RuntimeException();
        }
    }

    public JobseekerTO addToVacancy(UUID id, UUID vacancyId, String comment) {
        Optional<JobseekerTO> jobseeker = Optional.ofNullable(getById(id));
        Optional<VacancyTO> vacancyTO = Optional.ofNullable(vacancyManager.getById(vacancyId));
        if (jobseeker.isPresent() && vacancyTO.isPresent()) {
            JobseekerHistoryTO historyTO = initDateCreated(new JobseekerHistoryTO());
            CommentTO commentTO = buildComment(comment);
            historyTO.getComments().add(commentTO);
            historyTO.setVacancy(vacancyTO.get());
            historyTO.setStatus(jobseekerStatusManager.getStartStatus(vacancyTO.get()));
            historyTO = historyManager.save(historyTO);
            jobseeker.get().getHistories().add(historyTO);

            //some logic for change status
            return save(jobseeker.get());
        } else {
            //TODO need true Exception
            throw new RuntimeException();
        }
    }

    private CommentTO buildComment(String comment) {
        CommentTO commentTO = initDateCreated(new CommentTO());
        commentTO.setComment(comment);
        return commentTO;
    }

    @Override
    public BaseRepository<Jobseeker> getBaseRepository() {
        return repository;
    }

    @Override
    public Class<? extends JobseekerTO> getTOClass() {
        return JobseekerTO.class;
    }

    @Override
    public Class<? extends Jobseeker> getEntityClass() {
        return Jobseeker.class;
    }


    public void sendFormToMail(UUID jobseekerId, UUID templateId) {
        Optional<JobseekerTO> jobseeker = Optional.ofNullable(getById(jobseekerId));
        checkPresent(jobseeker, new RuntimeException()); //TODO need true Exception
        checkCondition(() -> !StringUtils.isEmpty(jobseeker.get().getEmail()), new RuntimeException()); //TODO need true Exception
        PrintResultTO printResultTO = templateServiceClient.getService().make(templateId);
        checkCondition(() -> printResultTO != null, new RuntimeException()); //TODO need true Exception
        //TODO set from, to, copy
        MailTO mailTO = MailTO.builder()
                .to(jobseeker.get().getEmail())
                .messageBody(new String(printResultTO.getBody()))
                .build();
        mailSenderServiceClient.getService().sendMail(mailTO);
    }
}
