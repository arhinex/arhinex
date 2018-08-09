package ru.arhinex.staffin.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.arhinex.baseentity.manager.BaseManager;
import ru.arhinex.baseentity.repository.BaseRepository;
import ru.arhinex.staffin.entity.JobseekerHistory;
import ru.arhinex.staffin.repository.JobseekerHistoryRepository;
import ru.arhinex.staffinapi.to.CommentTO;
import ru.arhinex.staffinapi.to.JobseekerHistoryTO;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(value = "/jobseekerhistory")
public class JobseekerHistoryManager extends BaseManager<JobseekerHistory, JobseekerHistoryTO> {
    @Autowired
    private JobseekerHistoryRepository repository;


    public JobseekerHistoryTO addComment(UUID id, String comment) {
        Optional<JobseekerHistoryTO> history = Optional.ofNullable(getById(id));
        if (history.isPresent()) {
            CommentTO commentTO = buildComment(comment);
            history.get().getComments().add(commentTO);
            return save(history.get());
        } else {
            throw new RuntimeException();
        }
    }

    private CommentTO buildComment(String comment) {
        CommentTO commentTO = initDateCreated(new CommentTO());
        commentTO.setComment(comment);
        return commentTO;
    }

    @Override
    public BaseRepository<JobseekerHistory> getBaseRepository() {
        return repository;
    }

    @Override
    public Class<? extends JobseekerHistoryTO> getTOClass() {
        return JobseekerHistoryTO.class;
    }

    @Override
    public Class<? extends JobseekerHistory> getEntityClass() {
        return JobseekerHistory.class;
    }
}
