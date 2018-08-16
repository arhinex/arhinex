package ru.arhinex.staffin.manager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import ru.arhinex.print.service.stub.TemplateServiceStub;
import ru.arhinex.print.to.PrintRequestTO;
import ru.arhinex.print.to.PrintResultTO;
import ru.arhinex.staffin.clients.TemplateServiceClient;
import ru.arhinex.staffinapi.to.JobseekerTO;
import ru.arhinex.staffinapi.to.JobseekerVacancyStatusTO;
import ru.arhinex.staffinapi.to.VacancyTO;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JobseekerManagerTest extends BaseTest {


    @Autowired
    private JobseekerStatusManager statusManager;

    @MockBean
    private TemplateServiceClient templateServiceClient;

    @Test
    public void check_addtovacancy() {
        JobseekerTO obj = createSomeJobseeker("test");
        VacancyTO vacancy = createSomeVacancy();

        jobseekerManager.addToVacancy(obj.getId(), vacancy.getId(), "Comment");

        obj = jobseekerManager.getById(obj.getId());
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

        jobseekerManager.addToVacancy(obj.getId(), vacancy.getId(), "Comment");
        obj = jobseekerManager.getById(obj.getId());
        assertEquals(1, obj.getHistories().size());
        assertEquals(vacancy, obj.getHistories().get(0).getVacancy());

        jobseekerManager.changeStatus(obj.getId(), statusTO.getId());
        obj = jobseekerManager.getById(obj.getId());

        assertEquals(1, obj.getHistories().size());
        assertEquals(statusTO, obj.getCurrentStatus().get());
    }

    @Test(expected = Exception.class)
    public void check_fail_change_status_without_current_vacancy() {
        JobseekerTO obj = createSomeJobseeker("test");
        VacancyTO vacancy = createSomeVacancy();
        JobseekerVacancyStatusTO statusTO = createSomeJobseekerStatus("name", "code", false, vacancy);
        jobseekerManager.changeStatus(obj.getId(), statusTO.getId());
    }

    @Test
    public void check_send_mail_template() {
        JobseekerTO obj = createSomeJobseeker("test");
        given(templateServiceClient.getService()).willReturn(new TemplateServiceStub() {
            @Override
            public PrintResultTO make(UUID templateId, PrintRequestTO requestTO) {
                return new PrintResultTO();
            }
        });
        jobseekerManager.sendFormToMail(obj.getId(), UUID.randomUUID(), new PrintRequestTO());
    }

    @Test
    public void check_add_comment() {
        String comment = "Some comment";
        JobseekerTO obj = createSomeJobseeker("test");
        VacancyTO vacancy = createSomeVacancy();
        createSomeJobseekerStatus("name", "code", false, vacancy);
        jobseekerManager.addToVacancy(obj.getId(), vacancy.getId(), "Comment");
        obj = jobseekerManager.addComment(obj.getId(), comment);
        assertEquals(2, obj.getCurrentHistory().get().getComments().size());
        assertEquals(comment, obj.getCurrentHistory().get().getComments().get(1).getComment());
    }

    private JobseekerVacancyStatusTO createSomeJobseekerStatus(String name, String code, boolean isStart, VacancyTO vacancyTO) {
        JobseekerVacancyStatusTO statusTO = new JobseekerVacancyStatusTO();
        statusTO.setCode(code);
        statusTO.setName(name);
        statusTO.setVacancy(vacancyTO);
        statusTO.setStart(isStart);
        return statusManager.save(statusTO);
    }

}
