package ru.skypro.service.impl;

import org.junit.jupiter.api.Test;
import ru.skypro.employee.model.Employee;
import ru.skypro.exception.EmployeeNotFoundException;
import ru.skypro.exception.EmployeeAllreadyAddedException;


import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static ru.skypro.service.impl.EmployeeTestConstants.*;


class EmployeeServiceImplTest {
    private final EmployeeServiceImpl employeeService = new EmployeeServiceImpl();
    @Test
    public void shouldAddEmployee() {
        Employee employee = new Employee(FIRST_NAME, LAST_NAME, SALARY, DEPARTMENT_ID);
        assertFalse(employeeService.findAll().contains(employee));

        Employee addedEmployee = employeeService.add(FIRST_NAME, LAST_NAME, SALARY, DEPARTMENT_ID);
        assertEquals(employee, addedEmployee);
        assertTrue(employeeService.findAll().contains(employee));
        assertEquals(1,employeeService.findAll().size());
    }
    @Test
    public void shouldThrowEmployeeAlreadyException() {
        Employee addedEmployee = employeeService.add(FIRST_NAME, LAST_NAME, SALARY, DEPARTMENT_ID);
        assertTrue(employeeService.findAll().contains(addedEmployee));
        assertThrows(EmployeeAllreadyAddedException.class,
                () -> employeeService.add(FIRST_NAME, LAST_NAME, SALARY, DEPARTMENT_ID);
    }
    @Test
    public  void  shouldFindEmployee() {
        Employee addedEmployee = employeeService.add(FIRST_NAME, LAST_NAME, SALARY, DEPARTMENT_ID);
        assertEquals(addedEmployee, employeeService.find(FIRST_NAME,LAST_NAME));
    }
    @Test
    public void  shouldThrowEmployeeNotFoundExceptionWhenFind(){
        assertEquals(0,employeeService.findAll().size());
        assertThrows(EmployeeNotFoundException.class,
                () -> employeeService.find(FIRST_NAME, LAST_NAME));
    }

    @Test
    public void shouldRemoveEmployee(){
        Employee addedEmployee = employeeService.add(FIRST_NAME, LAST_NAME, SALARY, DEPARTMENT_ID);
        assertTrue(employeeService.findAll().contains(addedEmployee));
        assertEquals(1,employeeService.findAll().size());

        Employee removeEmployee = employeeService.remove(FIRST_NAME,LAST_NAME);
        assertEquals(addedEmployee, removeEmployee);
        assertFalse(employeeService.findAll().contains(addedEmployee));
        assertEquals(0,employeeService.findAll().size());
    }
    @Test
    public void  shouldThrowEmployeeNotFoundExceptionWhenRemove(){
        assertEquals(0,employeeService.findAll().size());
        assertThrows(EmployeeNotFoundException.class,
                () -> employeeService.remove(FIRST_NAME, LAST_NAME));
    }

    @Test
    public void shouldReturnAllEmployees(){
        Employee employee1 = employeeService.add(FIRST_NAME, LAST_NAME, SALARY, DEPARTMENT_ID);
        Employee employee2 = employeeService.add(FIRST_NAME2, LAST_NAME2, SALARY, DEPARTMENT_ID);
        Collection<Employee> addedEmployees = employeeService.findAll();
        assertIterableEquals(List.of(employee1, employee2), addedEmployees);
    }


}