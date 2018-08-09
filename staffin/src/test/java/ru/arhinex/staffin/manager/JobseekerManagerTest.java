package ru.arhinex.staffin.manager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.arhinex.staffinapi.to.JobseekerTO;
import ru.arhinex.staffinapi.to.JobseekerVacancyStatusTO;
import ru.arhinex.staffinapi.to.VacancyStatusTO;
import ru.arhinex.staffinapi.to.VacancyTO;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JobseekerManagerTest extends BaseTest {
    @Autowired
    private JobseekerManager manager;

    @Autowired
    private VacancyManager vacancyManager;

    @Autowired
    private VacancyStatusManager vacancyStatusManager;

    @Autowired
    private JobseekerStatusManager statusManager;

    @Test
    public void check_addtovacancy() {
        JobseekerTO obj = createSomeJobseeker("test");
        VacancyTO vacancy = createSomeVacancy();

        manager.addToVacancy(obj.getId(), vacancy.getId(), "Comment");

        obj = manager.getById(obj.getId());
        assertEquals(1, obj.getHistories().size());
        assertEquals(vacancy, obj.getHistories().get(0).getVacancy());

        assertEquals(1, obj.getHistories().get(0).getComments().size());
        assertEquals("Comment", obj.getHistories().get(0).getComments().get(0).getComment());
        assertTrue(obj.getCurrentStatus().isPresent());
        assertTrue(obj.getCurrentStatus().get().isStart());
    }


    @Test
    public void check_change_status() {
        JobseekerTO obj = createSomeJobseeker("test");
        VacancyTO vacancy = createSomeVacancy();

        JobseekerVacancyStatusTO statusTO = createSomeJobseekerStatus("name", "code", false, vacancy);

        manager.addToVacancy(obj.getId(), vacancy.getId(), "Comment");
        obj = manager.getById(obj.getId());
        assertEquals(1, obj.getHistories().size());
        assertEquals(vacancy, obj.getHistories().get(0).getVacancy());

        manager.changeStatus(obj.getId(), statusTO.getId());
        obj = manager.getById(obj.getId());

        assertEquals(1, obj.getHistories().size());
        assertEquals(statusTO, obj.getCurrentStatus().get());
    }

    @Test(expected = Exception.class)
    public void check_fail_change_status_without_current_vacancy() {
        JobseekerTO obj = createSomeJobseeker("test");
        VacancyTO vacancy = createSomeVacancy();
        JobseekerVacancyStatusTO statusTO = createSomeJobseekerStatus("name", "code", false, vacancy);
        manager.changeStatus(obj.getId(), statusTO.getId());
    }

    @Test
    public void check_send_offer() {

    }


    private JobseekerTO createSomeJobseeker(String name) {
        JobseekerTO obj = new JobseekerTO();
        obj.setFio(name);
        return manager.save(obj);
    }

    private VacancyTO createSomeVacancy() {
        VacancyTO vacancyTO = new VacancyTO();
        vacancyTO.setOpenDate(new Date());
        vacancyTO.setDescription("test description");
        vacancyTO.setGraid(createSomeIdentifiable());
        vacancyTO.setStatus(createSomeVacancyStatus());
        return vacancyManager.save(vacancyTO);
    }


    private JobseekerVacancyStatusTO createSomeJobseekerStatus(String name, String code, boolean isStart, VacancyTO vacancyTO) {
        JobseekerVacancyStatusTO statusTO = new JobseekerVacancyStatusTO();
        statusTO.setCode(code);
        statusTO.setName(name);
        statusTO.setVacancy(vacancyTO);
        statusTO.setStart(isStart);
        return statusManager.save(statusTO);
    }

    private VacancyStatusTO createSomeVacancyStatus() {
        VacancyStatusTO statusTO = new VacancyStatusTO();
        statusTO.setCode("code1");
        statusTO.setName("name1");
        return vacancyStatusManager.save(statusTO);
    }

}
