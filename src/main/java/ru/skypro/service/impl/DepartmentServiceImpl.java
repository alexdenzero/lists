package ru.skypro.service.impl;

import org.springframework.stereotype.Service;
import ru.skypro.employee.model.Employee;
import ru.skypro.exeption.EmployeeStoragelsFullException;
import ru.skypro.service.DepartmentService;
import ru.skypro.service.EmployeeService;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;
import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.groupingBy;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final EmployeeService employeeService;

    public DepartmentServiceImpl(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public Employee findEmployeeWithMaxSalary(int departmentId) {
        return employeeService.findAll().stream()
                .filter(e -> e.getDepartmentId() == departmentId)
                .max(comparingInt(Employee::getSalary))
                .orElseThrow(EmployeeStoragelsFullException::new);
    }
    @Override
    public Employee findEmployeeWithMinSalary(int departmentId) {
        return employeeService.findAll().stream()
                .filter(e -> e.getDepartmentId() == departmentId)
                .min(comparingInt(Employee::getSalary))
                .orElseThrow(EmployeeStoragelsFullException::new);
    }
    @Override
        public Collection<Employee> findEmployeeByDepartment(int departmentId) {
        return employeeService.findAll().stream()
                .filter(e -> e.getDepartmentId() == departmentId)
                .sorted(comparing(Employee::getFullName).thenComparing(Employee::getFirstName))
                .collect(Collectors.toList());
    }
    @Override
    public Map<Integer, List<Employee>> findEmployeeByDepartment() {
        return employeeService.findAll().stream()
                .sorted(comparing(Employee::getFullName).thenComparing(Employee::getFirstName))
                .collect(groupingBy(Employee::getDepartmentId));
    }
}