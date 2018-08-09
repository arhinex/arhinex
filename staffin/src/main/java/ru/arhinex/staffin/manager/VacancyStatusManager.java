package ru.arhinex.staffin.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.arhinex.baseentity.manager.BaseManager;
import ru.arhinex.baseentity.repository.BaseRepository;
import ru.arhinex.staffin.entity.VacancyStatus;
import ru.arhinex.staffin.repository.VacancyStatusRepository;
import ru.arhinex.staffinapi.to.VacancyStatusTO;

@RestController
@RequestMapping(value = "/vacancystatus")
public class VacancyStatusManager extends BaseManager<VacancyStatus, VacancyStatusTO> {
    @Autowired
    private VacancyStatusRepository repository;

    @Override
    public BaseRepository<VacancyStatus> getBaseRepository() {
        return repository;
    }

    @Override
    public Class<? extends VacancyStatusTO> getTOClass() {
        return VacancyStatusTO.class;
    }

    @Override
    public Class<? extends VacancyStatus> getEntityClass() {
        return VacancyStatus.class;
    }
}
