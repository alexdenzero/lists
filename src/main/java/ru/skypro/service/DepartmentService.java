package ru.skypro.service;

import ru.skypro.employee.model.Employee;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface DepartmentService {

    Integer getDepartmentSalarySum(int departmentId);
    Employee findEmployeeWithMaxSalary(int departmentId);
    Employee findEmployeeWithMinSalary(int departmentId);
    Collection<Employee> findEmployeeByDepartment(int departmentId);
    Map<Integer, List<Employee>> findEmployeeByDepartment();

}
