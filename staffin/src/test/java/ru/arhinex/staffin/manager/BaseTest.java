package ru.arhinex.staffin.manager;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import ru.arhinex.baseapi.to.Identifiable;
import ru.arhinex.staffinapi.to.JobseekerTO;
import ru.arhinex.staffinapi.to.VacancyStatusTO;
import ru.arhinex.staffinapi.to.VacancyTO;

import java.util.Date;
import java.util.UUID;

public class BaseTest {
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    protected JobseekerManager jobseekerManager;

    @Autowired
    protected VacancyManager vacancyManager;

    @Autowired
    private VacancyStatusManager vacancyStatusManager;



    @Before
    public void doSetup(){
        mongoTemplate.getDb().drop();
    }

    protected Identifiable createSomeIdentifiable() {
        Identifiable identifiable = new Identifiable();
        identifiable.setId(UUID.randomUUID());
        return identifiable;
    }

    protected JobseekerTO createSomeJobseeker(String name) {
        JobseekerTO obj = new JobseekerTO();
        obj.setFio(name);
        obj.setEmail("some@mail.ru");
        return jobseekerManager.save(obj);
    }

    protected VacancyTO createSomeVacancy() {
        VacancyTO vacancyTO = new VacancyTO();
        vacancyTO.setOpenDate(new Date());
        vacancyTO.setDescription("test description");
        vacancyTO.setGraid(createSomeIdentifiable());
        vacancyTO.setStatus(createSomeVacancyStatus());
        return vacancyManager.save(vacancyTO);
    }

    protected VacancyStatusTO createSomeVacancyStatus() {
        VacancyStatusTO vacancyStatus = new VacancyStatusTO();
        vacancyStatus.setCode("code1");
        vacancyStatus.setName("name1");
        return vacancyStatusManager.save(vacancyStatus);
    }
}
