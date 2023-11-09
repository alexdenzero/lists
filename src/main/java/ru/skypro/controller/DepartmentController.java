package ru.skypro.controller;

import org.springframework.web.bind.annotation.*;
import ru.skypro.employee.model.Employee;
import ru.skypro.service.DepartmentService;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/{departmentId}/salary/sum")
    public Integer getDepartmentSalarySum(@PathVariable int departmentId){
        return departmentService.getDepartmentSalarySum(departmentId);
    }
    @GetMapping("/{departmentId}/salary/max")
    public Employee findEmployeeWithMaxSalary(@PathVariable int departmentId){
        return departmentService.findEmployeeWithMaxSalary(departmentId);
    }
    @GetMapping("/{departmentId}/salary/min")
    public Employee findEmployeeWithMinSalary(@PathVariable int departmentId){
        return departmentService.findEmployeeWithMinSalary(departmentId);
    }
    @GetMapping("employees")
    public Map<Integer, List<Employee>> findEmployeeByDepartment(){
        return departmentService.findEmployeeByDepartment();
    }
    @GetMapping(value = "/{departmentId}/employees")
    public Collection<Employee> findEmployeeByDepartment(@PathVariable int departmentId){
        return departmentService.findEmployeeByDepartment(departmentId);
    }
}
