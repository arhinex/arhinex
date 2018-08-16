package ru.arhinex.employee.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.arhinex.baseentity.manager.BaseManager;
import ru.arhinex.baseentity.repository.BaseRepository;
import ru.arhinex.employee.entity.Employee;
import ru.arhinex.employee.repository.EmployeeRepository;
import ru.arhinex.to.EmployeeTO;

@RestController
@RequestMapping(value = "/employee")
public class EmployeeManager extends BaseManager<Employee, EmployeeTO> {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public BaseRepository<Employee> getBaseRepository() {
        return employeeRepository;
    }

    @Override
    public Class<? extends EmployeeTO> getTOClass() {
        return EmployeeTO.class;
    }

    @Override
    public Class<? extends Employee> getEntityClass() {
        return Employee.class;
    }
}
