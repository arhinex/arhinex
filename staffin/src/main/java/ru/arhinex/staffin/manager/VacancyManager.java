package ru.arhinex.staffin.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.arhinex.baseentity.manager.BaseManager;
import ru.arhinex.baseentity.repository.BaseRepository;
import ru.arhinex.staffin.entity.Vacancy;
import ru.arhinex.staffin.repository.VacancyRepository;
import ru.arhinex.staffinapi.to.VacancyStatusTO;
import ru.arhinex.staffinapi.to.VacancyTO;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(value = "/vacancy")
public class VacancyManager extends BaseManager<Vacancy, VacancyTO> {
    @Autowired
    private VacancyRepository repository;


    public VacancyTO changeStatus(UUID vacancyId, VacancyStatusTO newStatus) {
        Optional<VacancyTO> vacancy = Optional.ofNullable(getById(vacancyId));
        if (vacancy.isPresent()) {
            vacancy.get().setStatus(newStatus);
            //some logic for change status
            return save(vacancy.get());
        } else {
            throw new RuntimeException();
        }
    }

    public VacancyTO close(UUID vacancyId) {
        Optional<VacancyTO> vacancy = Optional.ofNullable(getById(vacancyId));
        if (vacancy.isPresent()) {
            vacancy.get().setCloseDate(new Date());
            return save(vacancy.get());
        } else {
            throw new RuntimeException();
        }
    }

    @Override
    public BaseRepository<Vacancy> getBaseRepository() {
        return repository;
    }

    @Override
    public Class<? extends VacancyTO> getTOClass() {
        return VacancyTO.class;
    }

    @Override
    public Class<? extends Vacancy> getEntityClass() {
        return Vacancy.class;
    }
}
