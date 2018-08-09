package ru.arhinex.staffin.manager;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import ru.arhinex.baseapi.to.Identifiable;

import java.util.UUID;

public class BaseTest {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Before
    public void doSetup(){
        mongoTemplate.getDb().drop();
    }

    protected Identifiable createSomeIdentifiable() {
        Identifiable identifiable = new Identifiable();
        identifiable.setId(UUID.randomUUID());
        return identifiable;
    }

}
