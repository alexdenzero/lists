package ru.skypro.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.skypro.exception.EmployeeNotFoundException;

import static java.util.Collections.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static ru.skypro.service.impl.EmployeeTestConstants.*;


@ExtendWith(MockitoExtension.class)

class DepartmentServiceImplTest {
    @Mock
    private EmployeeServiceImpl employeeService;
    @InjectMocks
    private DepartmentServiceImpl departmentService;

    @Test
    public void shouldTotalSalaryByDepartmentId(){
        when(employeeService.findAll()).thenReturn(EMPLOYEES);
        assertEquals(DEPARTMENT_TOTAL_SALARY, departmentService.getDepartmentSalarySum(DEPARTMENT_ID));
    }

    @Test
    public void shouldReturnEmployeeWithMaxByDepartmentId(){
        when(employeeService.findAll()).thenReturn(EMPLOYEES);
        assertEquals(MAX_SALARY_EMPLOYEE, departmentService.findEmployeeWithMaxSalary(DEPARTMENT_ID));
    }
    @Test
    public void shouldReturnEmployeeWithMinByDepartmentId(){
        when(employeeService.findAll()).thenReturn(EMPLOYEES);
        assertEquals(MIN_SALARY_EMPLOYEE, departmentService.findEmployeeWithMinSalary(DEPARTMENT_ID));
    }
    public void shouldThrowEmployeeNotFoundExceptionWhenFindEmployeeWitchMaxSalary(){
        when(employeeService.findAll()).thenReturn(emptyList());
        assertThrows(EmployeeNotFoundException.class, () -> departmentService.findEmployeeWithMaxSalary(DEPARTMENT_ID));
    }
    public void shouldThrowEmployeeNotFoundExceptionWhenFindEmployeeWitchMinSalary(){
        when(employeeService.findAll()).thenReturn(emptyList());
        assertThrows(EmployeeNotFoundException.class, () -> departmentService.findEmployeeWithMinSalary(DEPARTMENT_ID));
    }
    @Test
    public void shouldReturnEmployeesByDepartmentId(){
        when(employeeService.findAll()).thenReturn(DIFFERENT_DEPARTMENT_EMPLOYEES);

        assertEquals(EMPLOYEES, departmentService.findEmployeeByDepartment(DEPARTMENT_ID));
        assertEquals(singletonList(OTHER_DEPARTMENT_EMPLOYEE), departmentService.findEmployeeByDepartment(DEPARTMENT_ID2));
    }
    @Test
    public void shouldReturnAllEmployees(){
        when(employeeService.findAll()).thenReturn(DIFFERENT_DEPARTMENT_EMPLOYEES);
        assertEquals(EMPLOYEE_BY_DEPARTMENTS_MAP, departmentService.findEmployeeByDepartment());
    }
    @Test
    public void shouldReturnEmptyMapWhenFindAllEmployees(){
        when(employeeService.findAll()).thenReturn(emptyList());
        assertEquals(emptyMap(), departmentService.findEmployeeByDepartment());
    }

}