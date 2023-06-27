package ru.skypro.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import ru.skypro.employee.model.Employee;
import ru.skypro.exeption.EmployeeAllreadyAddedException;
import ru.skypro.exeption.EmployeeNotFoundException;
import ru.skypro.exeption.EmployeeStoragelsFullException;
import ru.skypro.exeption.InvalidInputException;
import ru.skypro.service.EmployeeService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.apache.commons.lang3.StringUtils.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {
private final Map<String, Employee> employees;

    public EmployeeServiceImpl() {
        this.employees = new HashMap<>();
    }

    @Override
    public Employee add(String firstName, String lastName) {

        validateInput(firstName,lastName);

        Employee employee = new Employee(firstName,lastName);

        if (employees.containsKey(employee.getFullName())){
            throw new EmployeeAllreadyAddedException();
        }
        employees.put(employee.getFullName(), employee);
        return  employee;
    }

    @Override
    public Employee remove(String firstName, String lastName) {

        validateInput(firstName,lastName);

        Employee employee = new Employee(firstName,lastName);

        if (!employees.containsKey(employee.getFullName())){
            throw new EmployeeNotFoundException();
        }
        employees.remove(employee.getFullName());
        return employee;
    }

    @Override
    public Employee find(String firstName, String lastName) {

        validateInput(firstName,lastName);

        Employee employee = new Employee(firstName,lastName);

        if (!employees.containsKey(employee.getFullName())){
            throw new EmployeeNotFoundException();
        }
        return employee;
    }

    @Override
    public Collection<Employee> findAll() {
        return employees.values();
    }

    private void validateInput(String firstName, String lastName) {
        if (!(isAlpha(firstName) && isAlpha(lastName)));
        throw new InvalidInputException();
    }
}
