package ru.arhinex.service;

import ru.arhinex.service.stub.EmployeeServiceStub;
import ru.arhinex.to.EmployeeTO;

import java.util.UUID;

public class EmployeeService extends BaseService<EmployeeTO, EmployeeServiceStub> implements IEmployeeService {

    public EmployeeService(String connectionUrl) {
        super(connectionUrl);
    }

    @Override
    protected EmployeeServiceStub createStab() {
        return new EmployeeServiceStub();
    }

    @Override
    public EmployeeTO findById(UUID id) {
        if (isMock()) return getStab().findById(id);
        return getRestTemplate().getForObject(getConnectionUrl() + "/" + id, EmployeeTO.class);
    }
}
