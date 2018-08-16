package ru.arhinex.staffin.manager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.arhinex.staffinapi.to.VacancyStatusTO;
import ru.arhinex.staffinapi.to.VacancyTO;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VacancyManagerTest extends BaseTest {
    @Autowired
    private VacancyManager vacancyManager;
    @Autowired
    private VacancyStatusManager vacancyStatusManager;

    @Test
    public void check_changestatus() {
        VacancyTO vacancy = createSomeVacancy();
        vacancy = vacancyManager.save(vacancy);

        VacancyStatusTO status = createSomeVacancyStatus();
        vacancyManager.changeStatus(vacancy.getId(), status);

        vacancy = vacancyManager.getById(vacancy.getId());
        assertNotNull(vacancy.getStatus());
        assertEquals(status.getCode(), vacancy.getStatus().getCode());
    }

    @Test
    public void check_createddate_notnull() {
        VacancyTO vacancy = createSomeVacancy();
        vacancy = vacancyManager.save(vacancy);
        assertNotNull(vacancy.getCreated());
    }

    @Test
    public void check_close_vacancy() {
        VacancyTO vacancy = createSomeVacancy();
        vacancy = vacancyManager.save(vacancy);
        assertNull(vacancy.getCloseDate());

        vacancy = vacancyManager.close(vacancy.getId());
        assertNotNull(vacancy.getCloseDate());
    }

}
